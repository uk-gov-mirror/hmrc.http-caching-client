import sbt.Keys._
import sbt._

val compileDependencies = PlayCrossCompilation.dependencies(
  play26 = Seq(
    "uk.gov.hmrc"       %% "json-encryption"    % "4.10.0-play-26",
    "com.typesafe.play" %% "play-json"          % "2.6.14",
    "uk.gov.hmrc"       %% "http-verbs-play-26" % "13.3.0"
  ),
  play27 = Seq(
    "uk.gov.hmrc"       %% "json-encryption"    % "4.10.0-play-27",
    "com.typesafe.play" %% "play-json"          % "2.7.4",
    "uk.gov.hmrc"       %% "http-verbs-play-27" % "13.3.0"
  ),
  play28 = Seq(
    "uk.gov.hmrc"       %% "json-encryption"    % "4.10.0-play-28",
    "com.typesafe.play" %% "play-json"          % "2.8.1",
    "uk.gov.hmrc"       %% "http-verbs-play-28" % "13.3.0"
  )
)

val testDependencies = PlayCrossCompilation.dependencies(
  shared = Seq(
    "org.scalatest"         %% "scalatest"     % "3.1.2"   % Test,
    "org.mockito"           %% "mockito-scala" % "1.5.11"  % Test,
    "com.vladsch.flexmark"  %  "flexmark-all"  % "0.35.10" % Test
  )
)

lazy val library = Project("http-caching-client", file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning)
  .settings(
    majorVersion := 9,
    makePublicallyAvailableOnBintray := true,
    libraryDependencies ++= compileDependencies ++ testDependencies,
    scalaVersion := "2.12.13",
  )
  .settings(PlayCrossCompilation.playCrossCompilationSettings)
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
