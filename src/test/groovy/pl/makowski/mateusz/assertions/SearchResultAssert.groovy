package pl.makowski.mateusz.assertions

import pl.makowski.mateusz.domain.Place

class SearchResultAssert {

    private List<Place> actual
    private ResponseAssert owner
    private int placeListIndex = 0

    protected SearchResultAssert(List<Place> actual, ResponseAssert owner) {
        this.actual = actual
        this.owner = owner
    }

    SearchResultAssert hasBodyWithExactlyOnePlace() {
        assert actual.size() == 1
        return this
    }

    SearchResultAssert hasBodyWithMoreThenOnePlace(){
        assert actual.size() > 1
        return this
    }

    SearchResultAssert withName(String name) {
        assert actual.get(placeListIndex).name == name
        return this
    }

    SearchResultAssert withLatitude(String latitude) {
        assert actual.get(placeListIndex).latitude == latitude
        return this
    }

    SearchResultAssert withLongitude(String longitude) {
        assert actual.get(placeListIndex).longitude == longitude
        return this
    }

    SearchResultAssert and() {
        placeListIndex++
        return this
    }
}
