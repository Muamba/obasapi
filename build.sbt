import com.typesafe.sbt.packager.docker.{Cmd, ExecCmd}

name := """obasapi"""
organization := "za.ac.cput"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, DebianPlugin, JavaAppPackaging, SystemdPlugin)

scalaVersion := "2.12.9"

scalacOptions += "-Ypartial-unification"

maintainer := "IT Department at CPUT <it@cput.ac.za>"
packageSummary in Linux := "OBAS REST API"
packageDescription := "CPUT IT Dept OBAS REST API "

dockerCommands := Seq(
  Cmd("FROM", "azul/zulu-openjdk-alpine:11"),
  Cmd("RUN", "apk --no-cache add bash"),
  Cmd("MAINTAINER", maintainer.value),
  Cmd("WORKDIR", "/opt/docker"),
  Cmd("ADD", "/opt /opt"),
  ExecCmd("RUN", "chown", "-R", "daemon:daemon", "."),
  Cmd("USER", "daemon"),
  ExecCmd("ENTRYPOINT", "bin/obasapi"),
  Cmd("VOLUME", "/opt/docker"),
  Cmd("EXPOSE", "9000"),
  Cmd("EXPOSE", "9999"),
  Cmd("EXPOSE", "8888")
)

javaOptions in Universal ++= Seq(
  // JVM memory tuning
  "-J-Xms4g",
  "-J-Xmx4g",
  "-J-Xmn2g"
)

val PhantomVersion = "2.42.0"
val PlayFrameWorkVersion = "2.7.3"
val catsVersion = "1.6.1"
val circeVersion = "0.12.0-M4"
val TwitterChillVersion = "0.9.3"
val MoshiVersion ="1.8.0"
val softwaremillSttpVersion = "1.6.4"


libraryDependencies ++= Seq("io.circe" %% "circe-core", "io.circe" %% "circe-generic", "io.circe" %% "circe-parser"
).map(_ % circeVersion)

libraryDependencies += "com.outworkers" % "phantom-dsl_2.12" % PhantomVersion
libraryDependencies += "com.outworkers" % "phantom-connectors_2.12" % PhantomVersion
libraryDependencies += "com.outworkers" % "phantom-streams_2.12" % PhantomVersion
libraryDependencies += "com.outworkers" % "phantom-jdk8_2.12" % PhantomVersion

libraryDependencies += "com.softwaremill.sttp" %% "core" % softwaremillSttpVersion
libraryDependencies += "com.softwaremill.sttp" %% "circe" % softwaremillSttpVersion
libraryDependencies += "com.softwaremill.sttp" %% "async-http-client-backend-future" % softwaremillSttpVersion


libraryDependencies += "com.typesafe.play" % "play-akka-http-server_2.12" % PlayFrameWorkVersion
libraryDependencies += "com.typesafe.play" % "play-guice_2.12" % PlayFrameWorkVersion
libraryDependencies += "com.typesafe.play" % "play-ws_2.12" % PlayFrameWorkVersion

libraryDependencies += "org.typelevel" % "cats-core_2.12" % catsVersion

// https://mvnrepository.com/artifact/com.twitter/chill-akka
libraryDependencies += "com.twitter" % "chill_2.12" % TwitterChillVersion
libraryDependencies += "com.twitter" % "chill-akka_2.12" %  TwitterChillVersion

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.2" % Test
libraryDependencies += "com.github.t3hnar" %% "scala-bcrypt" % "4.0"
// https://mvnrepository.com/artifact/org.bitbucket.b_c/jose4j
libraryDependencies += "org.bitbucket.b_c" % "jose4j" % "0.6.5"
// https://mvnrepository.com/artifact/com.sendgrid/sendgrid-java
libraryDependencies += "com.sendgrid" % "sendgrid-java" % "4.4.1"

libraryDependencies += "org.jsoup" % "jsoup" % "1.11.3"

libraryDependencies += "org.apache.commons" % "commons-lang3" % "3.7"
libraryDependencies += "org.apache.commons" % "commons-email" % "1.5"
libraryDependencies += "commons-io" % "commons-io" % "2.6"
libraryDependencies += "com.squareup.okhttp3" % "okhttp" % "3.12.1"


libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.1",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.1",
  "org.postgresql" % "postgresql" % "42.2.5" //org.postgresql.ds.PGSimpleDataSource dependency
)

resolvers ++= Seq(
  "Typesafe repository snapshots" at "http://repo.typesafe.com/typesafe/snapshots/",
  "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases",
  "Typesafe repository releases" at "http://repo.typesafe.com/typesafe/releases/",
  "softprops-maven" at "http://dl.bintray.com/content/softprops/maven",
  "Brando Repository" at "http://chrisdinn.github.io/releases/",
  "Sonatype repo" at "https://oss.sonatype.org/content/groups/scala-tools/",
  "Sonatype releases" at "https://oss.sonatype.org/content/repository/releases",
  "Sonatype snapshots" at "https://oss.sonatype.org/content/repository/snapshots",
  "Sonatype staging" at "http://oss.sonatype.org/content/repository/staging",
  "Java.net Maven2 Repository" at "http://download.java.net/maven/2/",
  "Twitter Repository" at "http://maven.twttr.com",
  "Websudos releases" at "https://dl.bintray.com/websudos/oss-releases/",
  "Goose Updates " at "https://dl.bintray.com/raisercostin/maven/",
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  Resolver.sonatypeRepo("public")
)

