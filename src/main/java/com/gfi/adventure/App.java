package com.gfi.adventure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gfi.adventure.model.Adventure;
import com.gfi.adventure.model.AdventurousPosition;
import com.gfi.adventure.reader.AdventureReader;

/**
 * 
 * @author Wilian Moraes
 *
 *         This app process a map with the goal to move a adventurous inside this map.
 * 
 *         The map must be provided in a file using the argument '-f', example:
 * 
 *         java -jar adventure-1.0.0.jar -f D:\path\to\file\scenario_1.txt
 * 
 *         First Line: initial position, example: 5,6 (In this example 5 is the column and 6 the
 *         line)
 * 
 *         Second line: movements that must be executed by the adventurous, example SSNNEEOS (In
 *         this example the adventurous will try to move to south 2 times, north two times, east two
 *         times, west one time and south one time)
 * 
 *         Third line: at this line the map specification is begin, use '#' for impenetrable woods
 *         and ' '(space) for path that the adventurous can walk through
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

		Adventure adventure = AdventureReader.readAdventureFromFile(filePath);
		System.out.println("Adventurous initial position: " + " line: " + adventure.getCurrentPosition().getLine() + " column: " + adventure.getCurrentPosition().getColumn());

		AdventurousPosition adventurousFinalPosition = adventure.executeMovements();

		System.out.println("Final position of the Adventurous: " + " line: " + adventurousFinalPosition.getLine() + " column: " + adventurousFinalPosition.getColumn());
	}
}
