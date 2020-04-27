package com.andry.rover.command;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.receiver.IRover;

/**
 * Command to the left.
 */
public class TurnLeftCommand implements Command{

	private final IRover rover;
	
	public TurnLeftCommand(IRover rover) {
		this.rover = rover;
	}

	@Override
	public void execute() throws RoverPositionInvalidException {
		this.rover.turnLeft();
	}

}
