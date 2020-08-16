package com.gfi.adventure.reader;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.enums.Movement;

/**
 * 
 * This class is responsible to reads the movements a {@link String}
 * 
 * @author Wilian Moraes
 *
 */
public class MovementsReader {

	private static final Logger LOGGER = LogManager.getLogger(MovementsReader.class);

	/**
	 * Reads the movements from a {@link String} using the available values on {@link Movement} on the
	 * attribute mapInputCharacter.
	 * 
	 * @param line
	 * @return
	 */
	public static List<Movement> readFromLine(String line) {

		LOGGER.debug("Reading adventure movements from line: " + line);

		List<Movement> movements = new ArrayList<Movement>();

		for (char charr : line.toCharArray()) {
			LOGGER.trace("Found: " + charr);
			movements.add(Movement.valueOfMapInputCharacter(charr));
		}

		LOGGER.debug("Movements readed: " + movements);

		return movements;
	}
}
