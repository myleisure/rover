package com.andry.rover.validator;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Position;

public interface PositionValidator {
    /**
     * Validate current position.
     *
     * @param position
     * @return
     */
    void validate(Position position) throws RoverPositionInvalidException;
}
