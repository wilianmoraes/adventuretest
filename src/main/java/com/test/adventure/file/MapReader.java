package com.test.adventure.file;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.adventure.map.Adventure;
import com.test.adventure.map.AdventurousPosition;
import com.test.adventure.map.enums.Movement;
import com.test.adventure.map.enums.SQMType;

public class MapReader {

	private static final Logger LOGGER = LogManager.getLogger(MapReader.class);

	private static final int INITIAL_POSITION_LINE = 0;
	private static final int DIRECTIONS_LINE = 1;
	private static final int MAP_FIRST_LINE = 2;

	public static Adventure readMapFromFile(String pathToMap) {
		LOGGER.info("Starting to read map from file: " + pathToMap);

		List<String> lines;

		try {
			lines = Files.readAllLines(Paths.get(pathToMap), StandardCharsets.UTF_8);
		} catch (IOException e) {
			LOGGER.error("Failed to read file: " + pathToMap + " ERROR: " + e);
			throw new RuntimeException("Failed to read file: " + pathToMap + " ERROR: " + e);
		}

		AdventurousPosition adventurousInitialPosition = readInitialPosition(lines.get(INITIAL_POSITION_LINE));
		List<Movement> movements = readDirections(lines.get(DIRECTIONS_LINE));
		SQMType[][] map = readMap(lines.subList(MAP_FIRST_LINE, lines.size()));

		if (LOGGER.isDebugEnabled()) {
			String stringMap = "Readed Map:";
			for (SQMType[] line : map) {
				stringMap = stringMap + "\n" + Arrays.deepToString(line).replace(",", "");
			}
			LOGGER.debug(stringMap);
		}

		LOGGER.info("Map readed with success from file: " + pathToMap);

		return new Adventure(map, adventurousInitialPosition, movements);
	}

	private static AdventurousPosition readInitialPosition(String fileLine) {
		LOGGER.debug("Reading initial position line");
		int initialLine = 0;
		int initialColumn = 0;

		String positionDelimiter = ",";

		if (fileLine.contains(".")) {
			positionDelimiter = "\\.";
		}

		String[] initialPosition = fileLine.split(positionDelimiter);

		Pattern p = Pattern.compile("\\d+");

		Matcher horizontalMatcher = p.matcher(initialPosition[0]);

		if (horizontalMatcher.find()) {
			initialColumn = Integer.valueOf(horizontalMatcher.group());
		} else {
			LOGGER.error("Failed to read initial column from file, could not find a valid number");
			throw new RuntimeException("Failed to read initial column from file, could not find a valid number");
		}

		Matcher verticalMatcher = p.matcher(initialPosition[1]);

		if (verticalMatcher.find()) {
			initialLine = Integer.valueOf(verticalMatcher.group());
		} else {
			LOGGER.error("Failed to read initial line from file, could not find a valid number");
			throw new RuntimeException("Failed to read initial line from file, could not find a valid number");
		}

		LOGGER.debug("Adventurous initial position readed, line: " + initialLine + " column:" + initialColumn);

		return new AdventurousPosition(initialColumn, initialLine);
	}

	private static List<Movement> readDirections(String line) {
		LOGGER.debug("Reading adventure movements from line: " + line);
		List<Movement> movements = new ArrayList<Movement>();
		for (char charr : line.toCharArray()) {
			LOGGER.trace("Found: " + charr);
			movements.add(Movement.valueOfMapInputCharacter(charr));
		}
		LOGGER.debug("Movements readed: " + movements);
		return movements;
	}

	private static SQMType[][] readMap(List<String> stringLines) {
		LOGGER.debug("Starting to read map");

		List<List<SQMType>> sqmTypeLines = new ArrayList<>();

		stringLines.forEach(line -> {
			sqmTypeLines.add(readMapLine(line));
		});

		Integer maxLineIndex = findLineMaxIndex(sqmTypeLines);

		LOGGER.debug("Max column index found: " + sqmTypeLines.size());
		LOGGER.debug("Max line index found: " + maxLineIndex);

		SQMType[][] map = new SQMType[sqmTypeLines.size()][maxLineIndex];

		AtomicInteger nextMapLine = new AtomicInteger(0);

		sqmTypeLines.forEach(line -> {
			LOGGER.debug("Filling map line: " + nextMapLine.get() + " with: " + line);
			AtomicInteger nextMapColumn = new AtomicInteger(0);
			line.forEach(sqm -> {
				LOGGER.trace("Filling column: " + nextMapColumn.get() + " of line: " + nextMapLine.get() + " with value: " + sqm);
				map[nextMapLine.get()][nextMapColumn.getAndIncrement()] = sqm;
			});
			LOGGER.debug("Map line: " + nextMapLine.get() + " filled");
			nextMapLine.getAndIncrement();

		});
		LOGGER.debug("Map readed");

		return map;
	}

	//@formatter:off
	private static Integer findLineMaxIndex(List<List<SQMType>> sqmTypeLines) {
		return sqmTypeLines
					.stream()
					.map(line -> line.size())
					.max((l1,l2) -> l1 - l2)
					.get();
	}
	//@formatter:on

	private static List<SQMType> readMapLine(String line) {
		LOGGER.debug("Starting to read map line from : " + line);
		List<SQMType> sqms = new ArrayList<SQMType>();
		for (char charr : line.toCharArray()) {
			sqms.add(SQMType.valueOfMapInputCharacter(charr));
		}
		LOGGER.debug("Map line readed: " + sqms);

		return sqms;
	}

}
