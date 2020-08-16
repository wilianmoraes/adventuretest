package com.test.gfi.adventure.reader;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.gfi.adventure.enums.Movement;
import com.gfi.adventure.exception.MovementReadException;
import com.gfi.adventure.reader.MovementsReader;

public class MovementReaderTest {

	@Test
	public void mustReadDirectionsFromFile() {
		List<Movement> movements = MovementsReader.readFromLine("SNOE");
		assertEquals(4, movements.size());
		assertEquals(Movement.SOUTH, movements.get(0));
		assertEquals(Movement.NORTH, movements.get(1));
		assertEquals(Movement.WEST, movements.get(2));
		assertEquals(Movement.EAST, movements.get(3));
	}

	@Test(expected = MovementReadException.class)
	public void mustThrownErrorWithInvalidDirectionCharacter() {
		MovementsReader.readFromLine("GSNOEGG");
	}

}