package com.gfi.adventure.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.exception.InitialPositionReadException;
import com.gfi.adventure.model.AdventurousPosition;

/**
 * 
 * This class is responsible to reads the initial position of an adventurous from a String.
 * 
 * @author Wilian Moraes
 *
 */
public class InitialPositionReader {

	private static final Logger LOGGER = LogManager.getLogger(InitialPositionReader.class);

	private static final String DOT = ".";
	private static final String SCAPED_DOT = "\\.";
	private static final String COMMA = ",";
	private static final String NUMBER_PATTERN = "\\d+";

	/**
	 * Reads initial position of the adventurous from provided line.
	 * 
	 * @param fileLine
	 * @return {@link AdventurousPosition}
	 */
	public static AdventurousPosition readFromLine(String fileLine) {

		LOGGER.debug("Reading Adventurous initial position line");

		String positionDelimiter = discoverDelimiter(fileLine);

		String[] initialPosition = fileLine.split(positionDelimiter);

		if (initialPosition.length <= 1) {
			LOGGER.error("Failed to read Adventurous initial position from file, could not find a valid delimiter");
			throw new InitialPositionReadException();
		}

		int initialColumn = extractNumberFromString(initialPosition[0]);
		int initialLine = extractNumberFromString(initialPosition[1]);

		LOGGER.debug("Adventurous initial position readed, line: " + initialLine + " column:" + initialColumn);

		return new AdventurousPosition(initialColumn, initialLine);
	}

	/**
	 * Extract an int from a {@link String} discarding non numbers characters
	 * 
	 * @param string
	 * @return int
	 */
	private static int extractNumberFromString(String string) {

		Pattern numberPattern = Pattern.compile(NUMBER_PATTERN);
		Matcher columnMatcher = numberPattern.matcher(string);

		if (columnMatcher.find()) {
			return Integer.valueOf(columnMatcher.group());
		} else {
			LOGGER.error("Failed to read Adventurous initial position from file, could not find a valid number");
			throw new InitialPositionReadException();
		}
	}

	/**
	 * Discover the delimiter used to separate initial column and line.
	 * 
	 * Supported delimiters: ,(comma) and .(dot)
	 * 
	 * @param fileLine
	 * @return
	 */
	private static String discoverDelimiter(String fileLine) {

		if (fileLine.contains(DOT) && fileLine.contains(COMMA)) {
			LOGGER.error("Failed to read Adventurous initial position from file, could not find a unique delimiter, please use comma or dot.");
			throw new InitialPositionReadException();
		}

		String positionDelimiter = COMMA;

		if (fileLine.contains(COMMA) == false && fileLine.contains(DOT)) {
			positionDelimiter = SCAPED_DOT;
		}
		return positionDelimiter;
	}
}
