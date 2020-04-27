package com.andry.rover.model;

import java.util.stream.Stream;

public enum Direction {
	
	WEST('W'), EAST('E'), NORTH('N'), SOUTH('S');

	Direction(Character code) {
		this.code = code;
	}

	private Character code;
	
	public Character getCode() { return this.code; }
	
	public static Direction fromCharacter(Character c) {
		return Stream.of(values())
				.filter(d -> d.getCode().equals(c))
				.findFirst()
				.orElse(null);
	}

}
