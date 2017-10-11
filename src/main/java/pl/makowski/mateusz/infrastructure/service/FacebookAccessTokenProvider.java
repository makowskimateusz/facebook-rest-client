package pl.makowski.mateusz.infrastructure.service;

import org.slf4j.Logger;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.makowski.mateusz.infrastructure.service.utils.AccessToken;
import pl.makowski.mateusz.infrastructure.service.utils.FacebookConfiguration;

import java.net.SocketTimeoutException;

import static org.slf4j.LoggerFactory.getLogger;

@Service
@EnableConfigurationProperties(FacebookConfiguration.class)
class FacebookAccessTokenProvider {

    private static final Logger log = getLogger(FacebookAccessTokenProvider.class);

    private final RestTemplate restTemplate;
    private final FacebookConfiguration configuration;

    public FacebookAccessTokenProvider(RestTemplate restTemplate, FacebookConfiguration configuration) {
        this.restTemplate = restTemplate;
        this.configuration = configuration;
    }

    @Retryable(value = SocketTimeoutException.class, maxAttempts = 3, backoff = @Backoff(delay = 100))
    protected AccessToken getAccessToken() {
        log.info("Request for access token to Facebook will be send");
        return restTemplate.getForObject(buildAccessTokenRequest(), AccessToken.class);
    }

    private String buildAccessTokenRequest() {
        return configuration.getAccessTokenUri() + "?client_id=" + configuration.getClientId() + "&client_secret=" + configuration.getClientSecret() + "&grant_type=client_credentials";
    }
}
