package com.andry.rover.command;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.receiver.IRover;

/**
 * Command used to turn to the right.
 */
public class TurnRightCommand implements Command {

	private final IRover rover;
	
	public TurnRightCommand(IRover rover) {
		this.rover = rover;
	}

	@Override
	public void execute() throws RoverPositionInvalidException {
		this.rover.turnRight();
	}

}
