import sbt._

object Dependencies {
  def cats(artifact: String): ModuleID = "org.typelevel" %% artifact % "2.6.1"
  def ciris(artifact: String): ModuleID = "is.cir" %% artifact % "2.2.0"
  def circe(artifact: String): ModuleID = "io.circe" %% artifact % "0.14.1"
  def fs2(artifact: String): ModuleID = "co.fs2" %% artifact % "3.0.3"
  def refined(artifact: String): ModuleID = "eu.timepit" %% artifact % "0.9.25"
  def monocle(artifact: String): ModuleID = "dev.optics" %% artifact % "3.0.0-RC2"
  def derevo(artifact: String): ModuleID = "tf.tofu" %% artifact % "0.12.5"
  def log4cats(artifact: String): ModuleID =
    "org.typelevel" %% artifact % "2.1.1"
  def scalaLog(artifact: String): ModuleID =
    "com.typesafe.scala-logging" %% artifact % "3.9.2"
  def logback(artifact: String): ModuleID =
    "ch.qos.logback" % artifact % "1.2.3"
  def http4s(artifact: String): ModuleID = "org.http4s" %% artifact % "0.23.6"
  lazy val newType = "io.estatico" %% "newtype" % "0.4.4"
  lazy val squants = "org.typelevel"  %% "squants"  % "1.6.0"
  lazy val simulacrum: ModuleID = "org.typelevel" %% "simulacrum" % "1.0.1"
  lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % "3.0.8"
  lazy val scalaCheck: ModuleID = "org.scalacheck" %% "scalacheck" % "1.14.3"
  lazy val scalaTestPlus: ModuleID = "org.scalatestplus" %% "scalacheck-1-14" % "3.1.0.1"
  lazy val redis: ModuleID = "dev.profunktor" %% "redis4cats-effects" % "1.0.0"
  lazy val catsRetry: ModuleID = "com.github.cb372" %% "cats-retry" % "3.1.0"
}