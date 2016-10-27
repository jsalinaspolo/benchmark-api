enablePlugins(GatlingPlugin)

name := "perfomance-gatling"

version := "1.0"

scalaVersion := "2.11.8"

libraryDependencies += "io.gatling"            % "gatling-test-framework"    % "2.2.2" % "test"
libraryDependencies += "io.gatling.highcharts" % "gatling-charts-highcharts" % "2.2.2" % "test"
