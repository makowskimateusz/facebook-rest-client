package pl.makowski.mateusz.assertions

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.ResponseEntity
import pl.makowski.mateusz.domain.Place

class ResponseAssert {

    private ResponseEntity actual
    private ObjectMapper mapper = new ObjectMapper()

    protected ResponseAssert(ResponseEntity actual) {
        assert actual != null
        this.actual = actual
    }

    SearchResultAssert hasOkStatus() {
        assert actual.statusCodeValue == 200
        List<Place> places = mapper.readValue(actual.body, new TypeReference<List<Place>>() {})
        return new SearchResultAssert(places, this)
    }

    ResponseAssert hasNotFoundStatus() {
        assert actual.statusCodeValue == 404
        return this
    }

    ResponseAssert hasInternalServerError() {
        assert actual.statusCodeValue >= 500
        return this
    }

    ResponseAssert withMessage(String message) {
        assert actual.body == message
        return this
    }

    ResponseAssert hasBadRequestStatus() {
        assert actual.statusCodeValue == 400
        return this
    }
}
