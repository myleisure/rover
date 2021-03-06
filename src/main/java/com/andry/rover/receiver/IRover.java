package com.andry.rover.receiver;

import com.andry.rover.exception.RoverPositionInvalidException;

public interface IRover {
	/**
	 * Algorithm executed when the rover wants to turn left.
	 */
	void turnLeft();
	
	/**
	 * Algorithm executed when the rover wants to turn right.
	 */
	void turnRight();
	
	/**
	 * Algorithm executed when the rover wants to move from it's position.
	 */
	void moveForward() throws RoverPositionInvalidException;
}
