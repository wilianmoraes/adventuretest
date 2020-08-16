package com.test.gfi.adventure.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.gfi.adventure.exception.InitialPositionReadException;
import com.gfi.adventure.model.AdventurousPosition;
import com.gfi.adventure.reader.InitialPositionReader;

public class InitialPositionTest {

	@Test
	public void mustReadInitialPositionWithComma() {
		AdventurousPosition position = InitialPositionReader.readFromLine("1,2");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());

	}

	@Test
	public void mustReadInitialPositionWithDot() {
		AdventurousPosition position = InitialPositionReader.readFromLine("1.2");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());
	}

	@Test
	public void mustReadInitialPositionFromADirtLine() {
		AdventurousPosition position = InitialPositionReader.readFromLine("[1,2]");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());

		position = InitialPositionReader.readFromLine("X1$#,!#2%#$%");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());

	}
	
	@Test
	public void mustReadInitialPositionIgnoringExtraValuesUsingComma() {
		AdventurousPosition position = InitialPositionReader.readFromLine("1,2,5,6");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());
	}
	
	@Test
	public void mustReadInitialPositionIgnoringExtraValuesUsingDot() {
		AdventurousPosition position = InitialPositionReader.readFromLine("1.2.5.6");
		assertNotNull(position);
		assertEquals(1, position.getColumn());
		assertEquals(2, position.getLine());
	}

	@Test(expected = InitialPositionReadException.class)
	public void mustThrownErrorBecauseColumnValuIsNotANumber() {
		InitialPositionReader.readFromLine("[a,2]");
	}

	@Test(expected = InitialPositionReadException.class)
	public void mustThrownErrorBecauseLineValuIsNotANumber() {
		InitialPositionReader.readFromLine("[1,a]");
	}
	
	@Test(expected = InitialPositionReadException.class)
	public void mustThrownErrorBecauseStringDoesNotContainAValidDelimiter() {
		InitialPositionReader.readFromLine("[11]");
	}
	
	@Test(expected = InitialPositionReadException.class)
	public void mustThrownErrorBecauseLineHaveMoreThanOneDelimiter() {
		InitialPositionReader.readFromLine("[1,2.3]");
	}

}