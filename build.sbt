import Dependencies._

Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalaVersion := "2.13.5"

  lazy val options = Seq(
    "-Wconf:cat=unused:info",
    "-Ymacro-annotations" // for newtype and simulacrum
  )

  lazy val commonSettings = Seq(
    name := "pfp-scala",
    version := "0.1.0",
    organization := "com.qohat",
    scalaVersion := "2.13.5",
    scalacOptions := options
  )

  lazy val root = (project in file("."))
  .settings(commonSettings: _*)
  .configs(Test)
  .settings(inConfig(Test)(Defaults.testSettings))
  .settings(
    libraryDependencies ++= Seq(
      compilerPlugin(
        "org.typelevel" %% "kind-projector" % "0.11.3"
          cross CrossVersion.full
      ),
      cats("cats-core"),
      "org.typelevel" %% "cats-effect"     % "3.1.1",
      "org.typelevel" %% "cats-mtl"        % "1.2.1",
      fs2("fs2-core"),
      monocle("monocle-core"),
      monocle("monocle-macro"),
      refined("refined-cats"),
      refined("refined-cats"),
      derevo("derevo-cats"),
      derevo("derevo-cats-tagless"),
      derevo("derevo-circe-magnolia"),
      "tf.tofu" %% "tofu-core-higher-kind" % "0.10.2",
      log4cats("log4cats-core"),
      log4cats("log4cats-slf4j"),
      circe("circe-core"),
      circe("circe-generic"),
      circe("circe-parser"),
      ciris("ciris"),
      scalaTest % Test
    )
  ).aggregate(core, test)
  .dependsOn(
    core,
    test
  )

  lazy val core = (project in file("core"))
  .settings(commonSettings: _*)
  .settings(
    name := "core",
    scalacOptions ++= options,
    libraryDependencies ++= Seq(
      newType,
      squants
    )
  ).dependsOn(test)


  lazy val test = (project in file("test"))
  .settings(commonSettings: _*)
  .settings(
    name := "test",
    scalacOptions ++= options,
    libraryDependencies ++= Seq(
      cats("cats-core"),
      "org.typelevel" %% "cats-effect"     % "3.1.1",
      fs2("fs2-core"),
      log4cats("log4cats-core"),
      log4cats("log4cats-slf4j"),
      scalaTest
    )
  )