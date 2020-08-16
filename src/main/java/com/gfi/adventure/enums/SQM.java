package com.gfi.adventure.enums;

import java.util.Arrays;

import com.gfi.adventure.exception.SQMReadException;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This {@link Enum} represents the SQM types that can be used on a map. 
 * 
 * @author Wilian Moraes
 *
 */
@AllArgsConstructor
@Getter
public enum SQM {
	PATH(true, ' '), WOOD(false, '#');

	/**
	 * Indicates if the {@link SQM} is walkable
	 */
	private boolean walkable;
	
	/**
	 * The char used on file to represent the {@link SQM} in a file
	 */
	private char mapInputCharacter;

	/**
	 * Converts a char to a {@link SQM} using the mapInputCharacter.
	 * 
	 * Throws {@link SQMReadException} when it can't find a corresponding value.
	 * 
	 * @param mapInputCharacter
	 * @return {@link Movement}
	 */
	//@formatter:off
	public static SQM valueOfMapInputCharacter(char mapInputCharacter) {
		//TODO:LOG debug
		SQM result = 
				Arrays.asList(SQM.values())
					.stream()
					.filter(sqmType -> sqmType.getMapInputCharacter() == mapInputCharacter)
					.findFirst()
					.orElse(null);
		
		if(result == null) {
			throw new SQMReadException(mapInputCharacter);
		}
		
		return result;
	}
	//@formatter:on

	@Override
	public String toString() {
		return String.valueOf(mapInputCharacter);
	}
}
