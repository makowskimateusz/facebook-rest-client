package pl.makowski.mateusz.infrastructure.service;

import org.springframework.stereotype.Service;

@Service
class SearchQueryBuilder {

    String build(String country, String city, String keyword) {
        return country + "+" + city + "+" + keyword;
    }
}
