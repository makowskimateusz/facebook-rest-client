package pl.makowski.mateusz.stubs

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse
import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo
import static org.springframework.http.HttpHeaders.CONTENT_TYPE
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

class FacebookGraphApiStub {

    static void stubSearchEndpointWithOneCityResult() {
        def responseBody = '{ "data": [ { "location": { "latitude": 52.40474913, "longitude": 16.940680915 }, "name": "Egnyte Poland", "id": "1458558184372813" } ] }'

        stubFor(get(urlEqualTo("/search?fields=location,name&q=poland%2Bpoznan%2Begnyte&type=place&access_token=122543105120903%7CuLJ5Fj5zk9xR3iq8L2X7shKtJSQ"))
                .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
        ))
    }

    static void stubSearchEndpointWithMoreThenOneResult() {
        def responseBody = "{ \"data\": [ { \"location\": { \"latitude\": 52.40115, \"longitude\": 16.92817 }, \"name\": \"Express March\\u00e9 Pozna\\u0144 Stary Browar\", \"id\": \"1587169854902988\" }, { \"location\": { \"latitude\": 52.40127723, \"longitude\": 16.913466515 }, \"name\": \"Express Kuchnia March\\u00e9 Avenida Pozna\\u0144\", \"id\": \"602496149847869\" }, { \"location\": { \"latitude\": 51.67733, \"longitude\": 22.30243 }, \"name\": \"Express Marche - Atrium Starego Browaru\", \"id\": \"971721539515403\" } ] }"
        stubFor(get(urlEqualTo("/search?fields=location,name&q=poland%2Bpoznan%2Bexpress%20marche&type=place&access_token=122543105120903%7CuLJ5Fj5zk9xR3iq8L2X7shKtJSQ"))
                .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
        ))
    }

    static void stubSearchEndpointWithNoResults() {
        def responseBody = "{ \"data\": [ ] }"
        stubFor(get(urlEqualTo("/search?fields=location,name&q=poland%2Bpoznan%2BnoResultQuery&type=place&access_token=122543105120903%7CuLJ5Fj5zk9xR3iq8L2X7shKtJSQ"))
                .willReturn(
                aResponse()
                        .withStatus(200)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                        .withBody(responseBody)
        ))
    }

    static void stubFacebookServerErrors(int statusCode) {
        def responseBody = "{ \"data\": [ ] }"
        stubFor(get(urlEqualTo("/search?fields=location,name&q=poland%2Bpoznan%2BunhappyQuery&type=place&access_token=122543105120903%7CuLJ5Fj5zk9xR3iq8L2X7shKtJSQ"))
                .willReturn(
                aResponse()
                        .withStatus(statusCode)
                        .withHeader(CONTENT_TYPE, APPLICATION_JSON_VALUE)
        ))
    }

}
