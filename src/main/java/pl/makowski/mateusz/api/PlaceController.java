package pl.makowski.mateusz.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.makowski.mateusz.domain.Place;
import pl.makowski.mateusz.domain.PlaceSearchService;

import java.util.List;

@RestController
public class PlaceController {

    private final PlaceSearchService placeSearchService;

    public PlaceController(PlaceSearchService placeSearchService) {
        this.placeSearchService = placeSearchService;
    }

    @GetMapping("/{country}/{city}/{keyword}")
    public List<Place> getPlaces(@PathVariable("country") String country, @PathVariable("city") String city, @PathVariable("keyword") String keyword) {
        return placeSearchService.findPlaces(country, city, keyword);
    }
}
