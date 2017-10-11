package pl.makowski.mateusz.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchPlaceResult {

    private String name;
    private Location location;

    public SearchPlaceResult(String name, Location location) {
        this.name = name;
        this.location = location;
    }

    public SearchPlaceResult() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
