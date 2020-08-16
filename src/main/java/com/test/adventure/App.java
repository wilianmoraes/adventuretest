package com.test.adventure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.adventure.file.MapReader;
import com.test.adventure.map.Adventure;
import com.test.adventure.map.AdventurousPosition;

/**
 * Hello world!
 *
 */
public class App {

	protected static final Logger LOGGER = LogManager.getLogger(App.class);

	public static void main(String[] args) {
		String arg = args[0];

		if (arg.equals("-f") == false) {
			throw new RuntimeException("Argument '-f' must be informed, its not possible execute this programan without a file, please provide a valid file.");
		}

		String filePath = args[1];

		Adventure adventure = MapReader.readMapFromFile(filePath);
		System.out.println("Adventurous initial position: " + " line: " + adventure.getCurrentPosition().getLine() + " column: " + adventure.getCurrentPosition().getColumn());

		AdventurousPosition adventurousFinalPosition = adventure.executeMovements();

		System.out.println("Final position of the Adventurous: " + " line: " + adventurousFinalPosition.getLine() + " column: " + adventurousFinalPosition.getColumn());
	}
}
