package com.jspcore

import io.gatling.core.Predef._
import io.gatling.core.scenario.Simulation
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {

  val httpConf = http
    .baseURL("http://localhost:5050")
  //    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
  //    .acceptEncodingHeader("gzip, deflate")
  //    .acceptLanguageHeader("en-US,en;q=0.5")
  //    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn = scenario("Scenario Example")
    .exec(http("request_1")
      .get("/slow"))


  //  setUp(scn.inject(constantUsersPerSec(100) during(1 minutes))).throttle(
  //    reachRps(100) in (10 seconds),
  //    holdFor(1 minutes)
  //    jumpToRps(50),
  //    holdFor(10 minute)
  //  )
  setUp(scn.inject(
    atOnceUsers(10),
    constantUsersPerSec(10) during (1 minutes)
  )).protocols(httpConf)
}
