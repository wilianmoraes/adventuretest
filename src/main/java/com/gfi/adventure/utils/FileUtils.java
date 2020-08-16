package com.gfi.adventure.utils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utils class with common methods used to read files.
 * 
 * @author Wilian Moraes
 *
 */
public class FileUtils {

	private static final Logger LOGGER = LogManager.getLogger(FileUtils.class);

	/**
	 * Reads all lines from a UTF8 file using the path provided.
	 * @param pathToFile
	 * @return {@link List} of {@link String}
	 */
	public static List<String> readLinesFromFile(String pathToFile) {
		try {
			return Files.readAllLines(Paths.get(pathToFile), StandardCharsets.UTF_8);
		} catch (Exception e) {
			LOGGER.error("Failed to read file: " + pathToFile + " ERROR: " + e);
			throw new RuntimeException("Failed to read file: " + pathToFile + " ERROR: " + e);
		}
	}
}
