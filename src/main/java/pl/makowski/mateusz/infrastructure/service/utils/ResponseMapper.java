package pl.makowski.mateusz.infrastructure.service.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import pl.makowski.mateusz.application.SearchPlaceResult;
import pl.makowski.mateusz.domain.Place;
import pl.makowski.mateusz.domain.exceptions.PlacesNotFoundException;

import java.io.IOException;
import java.util.List;

import static java.util.Arrays.asList;
import static org.slf4j.LoggerFactory.getLogger;
import static pl.makowski.mateusz.infrastructure.service.validation.ResultCondition.when;

@Service
public class ResponseMapper {

    private static final Logger log = getLogger(ResponseMapper.class);

    private ObjectMapper objectMapper = new ObjectMapper();
    private ModelMapper modelMapper = new ModelMapper();

    public ResponseMapper() {
        modelMapper.addMappings(new PropertyMap<SearchPlaceResult, Place>() {

            @Override
            protected void configure() {
                map().setLatitude(source.getLocation().getLatitude());
                map().setLongitude(source.getLocation().getLongitude());
            }
        });
    }

    public List<Place> mapHttpResponseToPlaceList(String httpResponse) {
        try {
            JsonNode foundedPlacesAsJsonNode = objectMapper.readTree(httpResponse).get("data");
            SearchPlaceResult[] searchPlaceResult = objectMapper.treeToValue(foundedPlacesAsJsonNode, SearchPlaceResult[].class);
            List<Place> places = asList(modelMapper.map(searchPlaceResult, Place[].class));
            when(places.size() == 0).thenThrowPlacesNotFoundException();
            return places;
        } catch (IOException ex) {
            log.error("Error during deserialization from facebook", ex);
            throw new PlacesNotFoundException();
        }
    }

}
