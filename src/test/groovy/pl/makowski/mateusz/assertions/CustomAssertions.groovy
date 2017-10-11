package pl.makowski.mateusz.assertions

import org.springframework.http.ResponseEntity

class CustomAssertions {

    static ResponseAssert assertThat(ResponseEntity response) {
        return new ResponseAssert(response)
    }
}
