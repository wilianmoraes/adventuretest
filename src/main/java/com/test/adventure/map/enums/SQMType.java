package com.test.adventure.map.enums;

import java.util.Arrays;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SQMType {
	PATH(true, ' '), WOOD(false, '#');
	
	private boolean walkable;
	private char mapInputCharacter;
	
	//@formatter:off
	public static SQMType valueOfMapInputCharacter(char mapInputCharacter) {
		//TODO:LOG debug
		return Arrays.asList(SQMType.values())
					.stream()
					.filter(sqmType -> sqmType.getMapInputCharacter() == mapInputCharacter)
					.findFirst()
					.orElse(null);
	}
	//@formatter:on
	
	@Override
	public String toString() {
		return String.valueOf( mapInputCharacter);
	}
}
