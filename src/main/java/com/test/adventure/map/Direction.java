package com.test.adventure.map;

public enum Direction {
	N {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getVerticalPosition() - 1,
					currentPosition.getHorizontalPosition());
		}
	},
	S {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getVerticalPosition() + 1,
					currentPosition.getHorizontalPosition());
		}
	},
	E {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getVerticalPosition(),
					currentPosition.getHorizontalPosition() + 1);
		}
	},
	O {
		@Override
		public AdventurousPosition move(AdventurousPosition currentPosition) {
			return new AdventurousPosition(currentPosition.getVerticalPosition(),
					currentPosition.getHorizontalPosition() - 1);
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
}
