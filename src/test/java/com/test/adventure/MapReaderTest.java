package com.test.adventure;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;

import com.test.adventure.file.MapReader;
import com.test.adventure.map.Adventure;
import com.test.adventure.map.AdventurousPosition;
import com.test.adventure.map.enums.Movement;

public class MapReaderTest {

	@Test
	public void firstScneario() {

		Adventure adventure = MapReader.readMapFromFile(pathToResourceFile("scenario_1.txt").substring(1));

		AdventurousPosition initialPosition = adventure.getCurrentPosition();

		assertEquals(3, initialPosition.getColumn());
		assertEquals(0, initialPosition.getLine());

		assertEquals(12, adventure.getMovements().size());
		assertEquals(Movement.SOUTH, adventure.getMovements().get(0));
		assertEquals(Movement.SOUTH, adventure.getMovements().get(1));
		assertEquals(Movement.SOUTH, adventure.getMovements().get(2));
		assertEquals(Movement.SOUTH, adventure.getMovements().get(3));
		assertEquals(Movement.EAST, adventure.getMovements().get(4));
		assertEquals(Movement.EAST, adventure.getMovements().get(5));
		assertEquals(Movement.EAST, adventure.getMovements().get(6));
		assertEquals(Movement.EAST, adventure.getMovements().get(7));
		assertEquals(Movement.EAST, adventure.getMovements().get(8));
		assertEquals(Movement.EAST, adventure.getMovements().get(9));
		assertEquals(Movement.NORTH, adventure.getMovements().get(10));
		assertEquals(Movement.NORTH, adventure.getMovements().get(11));

		AdventurousPosition adventureFinalPosition = adventure.executeMovements();

		assertEquals(9, adventureFinalPosition.getColumn());
		assertEquals(2, adventureFinalPosition.getLine());
	}

	@Test
	public void secondScneario() {

		Adventure adventure = MapReader.readMapFromFile(pathToResourceFile("scenario_2.txt").substring(1));

		AdventurousPosition initialPosition = adventure.getCurrentPosition();

		assertEquals(9, initialPosition.getColumn());
		assertEquals(6, initialPosition.getLine());

		assertEquals(9, adventure.getMovements().size());
		assertEquals(Movement.WEST, adventure.getMovements().get(0));
		assertEquals(Movement.WEST, adventure.getMovements().get(1));
		assertEquals(Movement.NORTH, adventure.getMovements().get(2));
		assertEquals(Movement.WEST, adventure.getMovements().get(3));
		assertEquals(Movement.WEST, adventure.getMovements().get(4));
		assertEquals(Movement.WEST, adventure.getMovements().get(5));
		assertEquals(Movement.SOUTH, adventure.getMovements().get(6));
		assertEquals(Movement.SOUTH, adventure.getMovements().get(7));
		assertEquals(Movement.WEST, adventure.getMovements().get(8));

		AdventurousPosition adventureFinalPosition = adventure.executeMovements();

		assertEquals(5, adventureFinalPosition.getColumn());
		assertEquals(7, adventureFinalPosition.getLine());
	}

	private String pathToResourceFile(String fileName) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL resource = classloader.getResource(fileName);
		return resource.getPath();
	}
}
