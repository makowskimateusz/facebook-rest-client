package pl.makowski.mateusz.integration

import pl.makowski.mateusz.BasicSpecification
import spock.lang.Unroll

import static pl.makowski.mateusz.assertions.CustomAssertions.assertThat
import static pl.makowski.mateusz.stubs.FacebookGraphApiStub.stubFacebookServerErrors
import static pl.makowski.mateusz.stubs.FacebookGraphApiStub.stubSearchEndpointWithMoreThenOneResult
import static pl.makowski.mateusz.stubs.FacebookGraphApiStub.stubSearchEndpointWithNoResults
import static pl.makowski.mateusz.stubs.FacebookGraphApiStub.stubSearchEndpointWithOneCityResult

class SearchPlaceSpecification extends BasicSpecification {

    def "should return place details for given correct criteria"() {
        given:
        stubSearchEndpointWithOneCityResult()
        when:
        def response = testRestTemplate.getForEntity("/poland/poznan/egnyte", String.class)
        then:
        assertThat(response)
                .hasOkStatus()
                .hasBodyWithExactlyOnePlace()
                    .withName("Egnyte Poland")
                    .withLatitude("52.40474913")
                    .withLongitude("16.940680915")
    }

    def "should return places details for given correct criteria"() {
        given:
        stubSearchEndpointWithMoreThenOneResult()
        when:
        def response = testRestTemplate.getForEntity("/poland/poznan/express marche", String.class)
        then:
        assertThat(response)
                .hasOkStatus()
                .hasBodyWithMoreThenOnePlace()
                    .withName("Express Marché Poznań Stary Browar")
                    .withLatitude("52.40115")
                    .withLongitude("16.92817")
                .and()
                    .withName("Express Kuchnia Marché Avenida Poznań")
                    .withLatitude("52.40127723")
                    .withLongitude("16.913466515")
    }

    def "should return not found status for no places found"() {
        given:
        stubSearchEndpointWithNoResults()
        when:
        def response = testRestTemplate.getForEntity("/poland/poznan/noResultQuery", String.class)
        then:
        assertThat(response)
                .hasNotFoundStatus()
    }

    @Unroll
    def "should return internal server error for facebook response with #statusCode http status"() {
        given:
        stubFacebookServerErrors(statusCode)
        when:
        def response = testRestTemplate.getForEntity("/poland/poznan/unhappyQuery", String.class)
        then:
        assertThat(response)
                .hasInternalServerError()
                .withMessage("There was an unexpected error during the connection with facebook graph api")
        where:
        statusCode << [500, 503]
    }
}
