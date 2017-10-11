package pl.makowski.mateusz.infrastructure.service;

import org.slf4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.makowski.mateusz.domain.Place;
import pl.makowski.mateusz.infrastructure.service.utils.AccessToken;
import pl.makowski.mateusz.infrastructure.service.utils.FacebookConfiguration;
import pl.makowski.mateusz.infrastructure.service.utils.ResponseMapper;

import javax.annotation.PostConstruct;
import java.net.SocketTimeoutException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@EnableConfigurationProperties(FacebookConfiguration.class)
class FacebookHttpClient {

    private static final Logger log = getLogger(FacebookHttpClient.class);

    private final RestTemplate restTemplate;
    private final ResponseMapper responseMapper;
    private final FacebookAccessTokenProvider facebookAccessTokenProvider;
    private final FacebookConfiguration facebookConfiguration;
    private AccessToken accessToken;

    public FacebookHttpClient(RestTemplate restTemplate, ResponseMapper responseMapper, FacebookAccessTokenProvider facebookAccessTokenProvider, FacebookConfiguration facebookConfiguration) {
        this.restTemplate = restTemplate;
        this.responseMapper = responseMapper;
        this.facebookAccessTokenProvider = facebookAccessTokenProvider;
        this.facebookConfiguration = facebookConfiguration;
    }

    @PostConstruct
    public void getAccessToken(){
        accessToken = facebookAccessTokenProvider.getAccessToken();
    }

    @Retryable(value = SocketTimeoutException.class, maxAttempts = 2, backoff = @Backoff(delay = 100))
    protected List<Place> search(String query) {
        log.info("Request with query: {} will be send to facebook graph api", query);
        ResponseEntity<String> response = restTemplate.getForEntity(buildFacebookQuery(query), String.class);
        return responseMapper.mapHttpResponseToPlaceList(response.getBody());
    }

    private String buildFacebookQuery(String query) {
        return facebookConfiguration.getGraphSearchEndpoint() + "?fields=location,name&q=" + query + "&type=place&access_token=" + accessToken.getAccessToken();
    }
}