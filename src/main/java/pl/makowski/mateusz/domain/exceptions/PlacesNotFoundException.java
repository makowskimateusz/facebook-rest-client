package pl.makowski.mateusz.domain.exceptions;

public class PlacesNotFoundException extends RuntimeException {

    public PlacesNotFoundException() {
        super("No places found for given criteria");
    }
}
