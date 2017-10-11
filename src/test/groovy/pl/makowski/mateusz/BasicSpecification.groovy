package pl.makowski.mateusz

import com.github.tomakehurst.wiremock.client.WireMock
import com.github.tomakehurst.wiremock.junit.WireMockRule
import org.junit.Rule
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.TestPropertySource
import spock.lang.Specification

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT

@SpringBootTest(webEnvironment = RANDOM_PORT)
abstract class BasicSpecification extends Specification {

    @Rule
    public WireMockRule localhost10100 = new WireMockRule(10100)

    @Autowired
    public TestRestTemplate testRestTemplate

    void setupSpec() {
        fixWireMock()
    }

    void cleanup() {
        WireMock.removeAllMappings()
        WireMock.reset()
    }

    private static void fixWireMock() {
        System.setProperty('http.keepAlive', 'false')
        System.setProperty('http.maxConnections', '1')
    }
}
