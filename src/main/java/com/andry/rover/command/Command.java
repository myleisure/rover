package com.andry.rover.command;

import com.andry.rover.exception.RoverPositionInvalidException;

@FunctionalInterface
public interface Command {
	/**
	 * Execute command.
	 */
	void execute() throws RoverPositionInvalidException;
}
