package com.gfi.adventure.enums;

import java.util.Arrays;

import com.gfi.adventure.exception.MovementReadException;
import com.gfi.adventure.model.AdventurousPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This {@link Enum} represents the movements that can be performed by the adventurous. 
 * 
 * @author Wilian Moraes
 *
 */
@Getter
@AllArgsConstructor
public enum Movement {
	NORTH('N') {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getColumn(),
					currentPosition.getLine() - 1);
		}
	},
	SOUTH('S') {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getColumn(),
					currentPosition.getLine() + 1);
		}
	},
	EAST('E') {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getColumn() + 1,
					currentPosition.getLine());
		}
	},
	WEST('O') {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getColumn() - 1,
					currentPosition.getLine());
		}
	};

	/**
	 * This method is responsible to move the adventurous on a map, this method
	 * receive the current position and each direction knows how to move to the
	 * target direction.
	 * 
	 * Is always returned a new instance of {@link AdventurousPosition}, is not
	 * validate if the movement is possible, only returned the result coordinates of
	 * a movement.
	 * 
	 * @param currentPosition
	 * @return AdventurousPosition
	 */
	public abstract AdventurousPosition move(AdventurousPosition currentPosition);

	/**
	 * The char used on file to represent the {@link Movement} in a file
	 */
	private char mapInputCharacter;
	
	/**
	 * Converts a char to a {@link Movement} using the mapInputCharacter.
	 * 
	 * Throws {@link MovementReadException} when it can't find a corresponding value.
	 * 
	 * @param mapInputCharacter
	 * @return {@link Movement}
	 */
	//@formatter:off
	public static Movement valueOfMapInputCharacter(char mapInputCharacter) {
		Movement result = 
				Arrays.asList(Movement.values())
					.stream()
					.filter(movement -> movement.getMapInputCharacter() == mapInputCharacter)
					.findFirst()
					.orElse(null);
		
		if(result == null) {
			throw new MovementReadException(mapInputCharacter);
		}
		
		return result;
	}
	//@formatter:on
}
