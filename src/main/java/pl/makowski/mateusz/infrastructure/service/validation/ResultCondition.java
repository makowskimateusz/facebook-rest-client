package pl.makowski.mateusz.infrastructure.service.validation;

import pl.makowski.mateusz.domain.exceptions.PlacesNotFoundException;

public class ResultCondition {

    private boolean condition;

    private ResultCondition(boolean condition) {
        this.condition = condition;
    }

    public static ResultCondition when(boolean condition) {
        return new ResultCondition(condition);
    }

    public void thenThrowPlacesNotFoundException() {
        if(condition) {
            throw new PlacesNotFoundException();
        }
    }

}
