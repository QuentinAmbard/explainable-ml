package explainableml

import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.{DecisionTreeClassificationModel, DecisionTreeClassifier}
import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}
import org.apache.spark.sql.SparkSession

object Test extends App {


  val spark = SparkSession.builder
    .appName("Datastax Scala example")
    .master("local[5]")
    .getOrCreate()
  import spark.implicits._

  val df = spark.read.format("csv").option("header", "true").option("inferSchema", true).load("export.csv").cache()
  df.show()
  println(df.schema)

  val categoricals = List("withdrawl_or_deposit", "checking_savings", "pos_capability", "offsite_or_onsite", "bank")
  val indexers = categoricals.map(c => {new StringIndexer().setInputCol(c).setOutputCol(c+"_idx")})
  val featureCols = categoricals.map(_+"_idx") ++ List("amount", "day", "month", "year", "hour", "min")

  val vectorAssembler = new VectorAssembler().setInputCols(featureCols.toArray).setOutputCol("features")
  val labelIndexer = new StringIndexer().setInputCol("fraud_report").setOutputCol("label")

  val dt = new DecisionTreeClassifier()
    .setLabelCol("label")
    .setFeaturesCol("features")

  private val stages = (indexers ++ List(vectorAssembler, labelIndexer, dt)).toArray
  val pipeline = new Pipeline().setStages(stages)

  val Array(trainingData, testData) = df.randomSplit(Array(0.7, 0.3))
  trainingData.show()
  val model = pipeline.fit(trainingData)

  val test = model.stages.last.asInstanceOf[DecisionTreeClassificationModel]
  test.save("decisionTreeClassificationModel.mllib")

  val predictions = model.transform(testData)
  predictions.show(5)

  model.stages

}
