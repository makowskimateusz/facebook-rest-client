package pl.makowski.mateusz.infrastructure.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import pl.makowski.mateusz.domain.exceptions.PlacesNotFoundException;
import static org.springframework.http.ResponseEntity.status;
@RestControllerAdvice
class ErrorHandler {

    Logger logger = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(PlacesNotFoundException.class)
    public ResponseEntity<String> handlePlacesNotFoundException(PlacesNotFoundException ex) {
        logger.warn("There was no places for user query", ex);
        return status(404).body(ex.getMessage());
    }

    @ExceptionHandler(HttpServerErrorException.class)
    public ResponseEntity<String> handleHttpServerErrorException(HttpServerErrorException ex) {
        logger.error("There was facebook error during the query execution", ex);
        return status(500).body("There was an unexpected error during the connection with facebook graph api");
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> handleHttpClientErrorException(HttpServerErrorException ex) {
        logger.error("There was http client error during the query execution", ex);
        return status(500).body("There was an unexpected error during the connection with facebook graph api");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        logger.error("There was an unexpected error", ex);
        return status(500).body("There was an unexpected error");
    }

}
