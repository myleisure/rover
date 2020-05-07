package com.andry.rover.validator;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Position;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MaxPositionValidatorTest {
    private final PositionValidator validator = new MaxPositionValidator(new Position(2, 3));

    @Test
    public void positionOutOfbound() {
        assertThrows(RoverPositionInvalidException.class, () -> validator.validate(new Position(1, 4)));
    }

    @Test
    public void validPosition() {
        assertDoesNotThrow(() -> this.validator.validate(new Position(1, 2)));
    }

    @Test public void negativePosition () {
        assertThrows(RoverPositionInvalidException.class, () -> validator.validate(new Position(-1, 1)));
        assertThrows(RoverPositionInvalidException.class, () -> validator.validate(new Position(1, -1)));
    }
}