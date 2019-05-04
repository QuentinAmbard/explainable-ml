name := "writeRead"

version := "0.1"

scalaVersion := "2.11.8"

val sparkVersion = "2.4.2"
//The 'test/resources' Directory in  should match the resources directory in the `it` directory
//for the version of the Spark Cassandra Connector in use.


val scalaTestVersion = "3.0.0"
val jUnitVersion = "4.12"


// Please make sure that following dependencies have versions corresponding to the ones in your cluster.
// Note that spark-cassandra-connector should be provided with '--packages' flag to spark-submit command.
libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-sql" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-hive" % sparkVersion % "compile",
  "org.apache.spark" %% "spark-mllib" % sparkVersion % "compile",

  //Test Dependencies
  "org.scalatest" %% "scalatest" % scalaTestVersion % "test",
  "junit" % "junit" % jUnitVersion % "test"
).map(_.exclude("org.slf4j", "log4j-over-slf4j"))  // Excluded to allow for Cassandra to run embedded


//Your dependencies
//libraryDependencies += "org.apache.commons" % "commons-math3" % "3.6.1"
//libraryDependencies += "org.apache.commons" % "commons-csv" % "1.0"

assemblyOption in assembly := (assemblyOption in assembly).value.copy(includeScala = false)
//assemblyShadeRules in assembly := Seq(
//  ShadeRule.rename("org.apache.commons.csv.**" -> "shaded.org.apache.commons.csv.@1").inAll
//)
