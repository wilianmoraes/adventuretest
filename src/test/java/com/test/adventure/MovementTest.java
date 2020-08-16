package com.test.adventure;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.test.adventure.map.AdventurousPosition;
import com.test.adventure.map.enums.Movement;

public class MovementTest {

	@Test
	public void mustMoveToSouth() {
		AdventurousPosition currentPosition = new AdventurousPosition(2, 2);
		
		AdventurousPosition result = Movement.SOUTH.move(currentPosition);
		
		assertEquals(3, result.getLine());
		assertEquals(2, result.getColumn());
	}
	
	@Test
	public void mustMoveToNorth() {
		AdventurousPosition currentPosition = new AdventurousPosition(2, 2);
		
		AdventurousPosition result = Movement.NORTH.move(currentPosition);
		
		assertEquals(1, result.getLine());
		assertEquals(2, result.getColumn());
	}
	
	@Test
	public void mustMoveToWest() {
		AdventurousPosition currentPosition = new AdventurousPosition(2, 2);
		
		AdventurousPosition result = Movement.WEST.move(currentPosition);
		
		assertEquals(2, result.getLine());
		assertEquals(1, result.getColumn());
	}
	
	@Test
	public void mustMoveToEast() {
		AdventurousPosition currentPosition = new AdventurousPosition(2, 2);
		
		AdventurousPosition result = Movement.EAST.move(currentPosition);
		
		assertEquals(2, result.getLine());
		assertEquals(3, result.getColumn());
	}
}
