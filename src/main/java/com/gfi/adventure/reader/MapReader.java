package com.gfi.adventure.reader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.enums.SQM;
import com.gfi.adventure.exception.SQMReadException;

/**
 * 
 * This class is responsible to reads the map specification from a {@link List} of {@link String}
 * 
 * @author Wilian Moraes
 *
 */
public class MapReader {

	private static final Logger LOGGER = LogManager.getLogger(MapReader.class);

	/**
	 * Reads the map from a {@link List} of {@link String}.
	 * 
	 * Identifies the max column and total lines of a map and fill a matrix with the found values.
	 * 
	 * @param stringLines
	 * @return {@link SQM[][]}
	 */
	public static SQM[][] readFromLines(List<String> stringLines) {
		LOGGER.debug("Starting to read map");

		List<List<SQM>> sqmTypeLines = new ArrayList<>();

		stringLines.forEach(line -> {
			sqmTypeLines.add(readMapLine(line));
		});

		Integer maxLineIndex = findLineMaxIndex(sqmTypeLines);

		LOGGER.debug("Max column index found: " + sqmTypeLines.size());
		LOGGER.debug("Max line index found: " + maxLineIndex);

		SQM[][] map = new SQM[sqmTypeLines.size()][maxLineIndex];

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

		if (LOGGER.isDebugEnabled()) {
			String stringMap = "Readed Map:";
			for (SQM[] line : map) {
				stringMap = stringMap + "\n" + Arrays.deepToString(line).replace(",", "");
			}
			LOGGER.debug(stringMap);
		}

		return map;
	}

	/**
	 * Identifies the max {@link List} length  of a {@link List} of {@link List} of {@link SQM} 
	 * 
	 * @param sqmTypeLines
	 * @return {@link Integer}
	 */
	//@formatter:off
	private static Integer findLineMaxIndex(List<List<SQM>> sqmTypeLines) {
		return sqmTypeLines
					.stream()
					.map(line -> line.size())
					.max((l1,l2) -> l1 - l2)
					.get();
	}
	//@formatter:on

	/**
	 * Converts a String to a {@link List} of {@link SQM} using the available values on SQM.
	 * 
	 * throws {@link SQMReadException} if the provided line contains invalid characters.
	 * 
	 * @param line
	 * @return {@link List} of {@link SQM}
	 */
	private static List<SQM> readMapLine(String line) {
		LOGGER.debug("Starting to read map line from : " + line);
		List<SQM> sqms = new ArrayList<SQM>();
		for (char charr : line.toCharArray()) {
			sqms.add(SQM.valueOfMapInputCharacter(charr));
		}
		LOGGER.debug("Map line readed: " + sqms);

		return sqms;
	}

}
