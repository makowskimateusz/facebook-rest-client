package pl.makowski.mateusz.domain;

import java.util.List;

public interface PlaceSearchService {

    List<Place> findPlaces(String country, String city, String keyword);
}
