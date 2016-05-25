name := """contactApp-scala"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  javaJdbc,
  cache,
  ws,
  evolutions,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test,
  "com.typesafe.play" %% "anorm" % "2.4.0",
  "com.zaxxer" % "HikariCP-java6" % "2.3.3",
  "org.postgresql" % "postgresql" % "9.4-1200-jdbc41"
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"
