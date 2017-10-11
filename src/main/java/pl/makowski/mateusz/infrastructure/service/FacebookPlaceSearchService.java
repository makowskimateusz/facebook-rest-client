package pl.makowski.mateusz.infrastructure.service;

import org.springframework.stereotype.Service;
import pl.makowski.mateusz.domain.Place;
import pl.makowski.mateusz.domain.PlaceSearchService;
import pl.makowski.mateusz.infrastructure.metrics.BusinessMetricsReporter;

import java.util.List;

@Service
class FacebookPlaceSearchService implements PlaceSearchService {

    private static final String SEARCH_FACEBOOK_RESULT_SUCCESS_METRIC = "search.facebook.result.success";
    private static final String SEARCH_FACEBOOK_RESULT_FAIL_METRIC = "search.facebook.result.fail";

    private final FacebookHttpClient facebookHttpClient;
    private final SearchQueryBuilder queryBuilder;
    private final BusinessMetricsReporter metricsReporter;

    public FacebookPlaceSearchService(FacebookHttpClient facebookHttpClient, SearchQueryBuilder queryBuilder, BusinessMetricsReporter metricsReporter) {
        this.facebookHttpClient = facebookHttpClient;
        this.queryBuilder = queryBuilder;
        this.metricsReporter = metricsReporter;
    }

    @Override
    public List<Place> findPlaces(String country, String city, String keyword) {
        String query = queryBuilder.build(country, city, keyword);
        List<Place> places = facebookHttpClient.search(query);
        collectMetrics(places);
        return places;
    }

    private void collectMetrics(List<Place> places) {
        if (places.size() != 0) {
            metricsReporter.measure(SEARCH_FACEBOOK_RESULT_SUCCESS_METRIC);
        } else {
            metricsReporter.measure(SEARCH_FACEBOOK_RESULT_FAIL_METRIC);
        }
    }
}
