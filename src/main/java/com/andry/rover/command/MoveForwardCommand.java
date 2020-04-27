package com.andry.rover.command;

import com.andry.rover.exception.RoverPositionInvalidException;
import com.andry.rover.receiver.IRover;

/**
 * Command used to move the rover.
 */
public class MoveForwardCommand implements Command {

	private final IRover rover;
	
	public MoveForwardCommand(IRover rover) {
		this.rover = rover;
	}


	@Override
	public void execute() throws RoverPositionInvalidException {
		this.rover.moveForward();
	}

}
