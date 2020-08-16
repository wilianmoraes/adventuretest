package com.test.gfi.adventure.reader;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.gfi.adventure.enums.Movement;
import com.gfi.adventure.enums.SQM;
import com.gfi.adventure.model.Adventure;
import com.gfi.adventure.model.AdventurousPosition;
import com.gfi.adventure.reader.MapReader;
import com.gfi.adventure.reader.MovementsReader;

public class AdventureTest {

	@Test
	public void mustMoveOnlyInsideMap() {

		AdventurousPosition currentPosition = new AdventurousPosition(0, 0);
		//@formatter:off
		SQM[][] map = MapReader.readFromLines(Arrays.asList("   ", 
															"# #"));
		//@formatter:on
		List<Movement> movements = MovementsReader.readFromLine("NNSSOOEEEEEOS");

		Adventure adventure = new Adventure(map, currentPosition, movements);
		AdventurousPosition finishPosition = adventure.executeMovements();

		assertEquals(1, finishPosition.getColumn());
		assertEquals(1, finishPosition.getLine());
	}

	@Test
	public void mustMoveOnlyInsideMapWithDiferentLinesLength() {

		AdventurousPosition currentPosition = new AdventurousPosition(0, 0);
		//@formatter:off
		SQM[][] map = MapReader.readFromLines(Arrays.asList("   ", 
															"# #", 
															"#   "));
		//@formatter:on

		List<Movement> movements = MovementsReader.readFromLine("ESSEEENS");

		Adventure adventure = new Adventure(map, currentPosition, movements);
		AdventurousPosition finishPosition = adventure.executeMovements();

		assertEquals(3, finishPosition.getColumn());
		assertEquals(2, finishPosition.getLine());
	}
}
