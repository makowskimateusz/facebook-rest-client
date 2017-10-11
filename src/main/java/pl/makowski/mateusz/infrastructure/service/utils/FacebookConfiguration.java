package pl.makowski.mateusz.infrastructure.service.utils;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("facebook")
public class FacebookConfiguration {

    private String clientId;
    private String clientSecret;
    private String accessTokenUri;
    private String graphSearchEndpoint;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getAccessTokenUri() {
        return accessTokenUri;
    }

    public void setAccessTokenUri(String accessTokenUri) {
        this.accessTokenUri = accessTokenUri;
    }

    public String getGraphSearchEndpoint() {
        return graphSearchEndpoint;
    }

    public void setGraphSearchEndpoint(String graphSearchEndpoint) {
        this.graphSearchEndpoint = graphSearchEndpoint;
    }
}
