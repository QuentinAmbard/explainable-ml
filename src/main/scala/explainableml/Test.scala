package explainableml

import org.apache.spark.sql.SparkSession

object Test extends App {


  val spark = SparkSession.builder
    .appName("Datastax Scala example")
    .getOrCreate()
  import spark.implicits._

}
