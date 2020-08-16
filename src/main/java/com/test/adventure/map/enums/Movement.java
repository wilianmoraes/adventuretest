package com.test.adventure.map.enums;

import java.util.Arrays;

import com.test.adventure.map.AdventurousPosition;

import lombok.AllArgsConstructor;
import lombok.Getter;

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

	private char mapInputCharacter;
	
	//@formatter:off
	public static Movement valueOfMapInputCharacter(char mapInputCharacter) {
		//TODO:LOG debug
		return Arrays.asList(Movement.values())
					.stream()
					.filter(movement -> movement.getMapInputCharacter() == mapInputCharacter)
					.findFirst()
					.orElse(null);
	}
	//@formatter:on
}
