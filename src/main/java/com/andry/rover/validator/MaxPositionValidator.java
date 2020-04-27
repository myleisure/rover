package com.andry.rover.validator;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.model.Position;

public class MaxPositionValidator implements PositionValidator {

	private Position maxPosition;

	public MaxPositionValidator(Position maxPosition) {
		this.maxPosition = maxPosition;
	}

	@Override
	public void validate(Position position) throws RoverPositionInvalidException {
		final boolean isPositionInThePlateau = position.getX() >= 0 && position.getX() <= this.maxPosition.getX()
				&& position.getY() >= 0 && position.getY() <= maxPosition.getY();
		if (!isPositionInThePlateau) {
			throw new RoverPositionInvalidException("Rover is out of bound");
		}
	}

}
