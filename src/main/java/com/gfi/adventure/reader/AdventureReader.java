package com.gfi.adventure.reader;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.enums.Movement;
import com.gfi.adventure.enums.SQM;
import com.gfi.adventure.model.Adventure;
import com.gfi.adventure.model.AdventurousPosition;
import com.gfi.adventure.utils.FileUtils;

/**
 * This class is responsible to read the adventure specification from a file of a provided path.
 * 
 * @author Wilian Moraes
 *
 */
public class AdventureReader {

	private static final Logger LOGGER = LogManager.getLogger(AdventureReader.class);

	private static final int INITIAL_POSITION_LINE = 0;
	private static final int DIRECTIONS_LINE = 1;
	private static final int MAP_FIRST_LINE = 2;

	/**
	 * Reads the adventure specification from a file using the provided path to locate the file.
	 * 
	 * @param pathToMap
	 * @return {@link Adventure}
	 */
	public static Adventure readAdventureFromFile(String pathToMap) {

		LOGGER.info("Starting to read Adventure from file: " + pathToMap);

		List<String> lines = FileUtils.readLinesFromFile(pathToMap);

		AdventurousPosition adventurousInitialPosition = InitialPositionReader.readFromLine(lines.get(INITIAL_POSITION_LINE));
		List<Movement> movements = MovementsReader.readFromLine(lines.get(DIRECTIONS_LINE));
		SQM[][] map = MapReader.readFromLines(lines.subList(MAP_FIRST_LINE, lines.size()));

		LOGGER.info("Adventure readed with success from file: " + pathToMap);

		return new Adventure(map, adventurousInitialPosition, movements);
	}

}
