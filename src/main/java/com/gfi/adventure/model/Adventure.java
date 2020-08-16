package com.gfi.adventure.model;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.enums.Movement;
import com.gfi.adventure.enums.SQM;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * This class represents the adventure and the possible actions.
 * 
 * @author Wilian Moraes
 *
 */
@Getter
@AllArgsConstructor
public class Adventure {

	private static final Logger LOGGER = LogManager.getLogger(Adventure.class);

	private SQM map[][];

	private AdventurousPosition currentPosition;

	private List<Movement> movements;

	/**
	 * Performs the movements specified previously.
	 * 
	 * @return {@link AdventurousPosition}
	 */
	public AdventurousPosition executeMovements() {
		LOGGER.info("Starting adveture, going to execute movements: " + movements);
		LOGGER.info("Start position, line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());

		movements.forEach(movement -> moveTo(movement));

		LOGGER.info("Movements executed, current possition is: line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());

		return currentPosition;
	}

	/**
	 * Moves the adventurous on the map, only update the currentPosition if the new position is valid.
	 * 
	 * @param direction
	 */
	private void moveTo(Movement direction) {
		LOGGER.debug("Trying to move to: " + direction);
		LOGGER.debug("Start movement possition, line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());

		AdventurousPosition newPosition = direction.move(currentPosition);

		if (isPositionWalkable(newPosition)) {
			this.currentPosition = newPosition;
		} else {
			LOGGER.warn("New position, line: " + newPosition.getLine() + " column: " + newPosition.getColumn() + " is not walkable, movement not possible!");
		}

		LOGGER.debug("Current possition after movement, line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());
	}

	/**
	 * Verifies if the provided position is valid.
	 * @param position
	 * @return
	 */
	private boolean isPositionWalkable(AdventurousPosition position) {
		try {
			SQM targetPositionType = map[position.getLine()][position.getColumn()];
			return targetPositionType.isWalkable();
		} catch (Exception e) {
			LOGGER.warn("Could not find element on line: " + position.getLine() + " column: " + position.getColumn() + " Exception:" + e);
			return false;
		}
	}
}
