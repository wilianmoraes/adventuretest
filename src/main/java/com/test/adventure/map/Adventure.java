package com.test.adventure.map;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.adventure.map.enums.Movement;
import com.test.adventure.map.enums.SQMType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Adventure {

	private static final Logger LOGGER = LogManager.getLogger(Adventure.class);

	private SQMType map[][];

	private AdventurousPosition currentPosition;

	private List<Movement> movements;

	public AdventurousPosition executeMovements() {
		LOGGER.info("Starting adveture, going to execute movements: " + movements);
		LOGGER.info("Start position, line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());

		movements.forEach(movement -> moveTo(movement));

		LOGGER.info("Movements executed, current possition is: line: " + currentPosition.getLine() + " column: " + currentPosition.getColumn());

		return currentPosition;
	}

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

	private boolean isPositionWalkable(AdventurousPosition position) {
		try {
			SQMType targetPositionType = map[position.getLine()][position.getColumn()];
			return targetPositionType.isWalkable();
		} catch (Exception e) {
			LOGGER.warn("Could not find element on line: " + position.getLine() + " column: " + position.getColumn() + " Exception:" + e);
			return false;
		}
	}
}
