package pl.makowski.mateusz.infrastructure.service.utils;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessToken {

    private String accessToken;

    @JsonCreator
    public AccessToken(@JsonProperty("access_token") String accessToken) {
        this.accessToken = accessToken;
    }

    public AccessToken() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
