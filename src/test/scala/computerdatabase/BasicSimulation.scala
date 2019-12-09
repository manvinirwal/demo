package computerdatabase

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class BasicSimulation extends Simulation {
  
  val httpProtocol = http
    .baseUrl("http://demo.manvi.34.67.38.135.nip.io") // Here is the root for all relative URLs
    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8") // Here are the common headers
    .acceptEncodingHeader("gzip, deflate")
    .acceptLanguageHeader("en-US,en;q=0.5")
    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.8; rv:16.0) Gecko/20100101 Firefox/16.0")

  val scn1 = scenario("Scenario 001") // A scenario is a chain of requests and pauses
    .exec(http("request_101")
      .get("/"))
    .pause(3) // Note that Gatling has recorder real time pauses
    .exec(http("request_102")
      .get("/login"))
    .pause(3)
    .exec(http("request_103")
      .get("/account/register"))
    .pause(300 milliseconds)
    .exec(http("request_104")
      .get("/"))
    .pause(2)

  setUp(scn1.inject(atOnceUsers(1)).protocols(httpProtocol))

}
