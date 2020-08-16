package com.test.gfi.adventure.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.Arrays;

import org.junit.Test;

import com.gfi.adventure.enums.SQM;
import com.gfi.adventure.exception.SQMReadException;
import com.gfi.adventure.reader.MapReader;

public class MapReaderTest {

	@Test
	public void mustReadMapWithTwoLinesAndThreeColumns() {
		//@formatter:off
		SQM[][] readFromLines = MapReader.readFromLines(Arrays.asList("# #", 
																	  "#  "));
		//@formatter:on
		
		assertEquals(2, readFromLines.length);
		assertEquals(3, readFromLines[0].length);
		assertEquals(3, readFromLines[1].length);
		
		assertEquals(SQM.WOOD, readFromLines[0][0]);
		assertEquals(SQM.PATH, readFromLines[0][1]);
		assertEquals(SQM.WOOD, readFromLines[0][2]);
		
		assertEquals(SQM.WOOD, readFromLines[1][0]);
		assertEquals(SQM.PATH, readFromLines[1][1]);
		assertEquals(SQM.PATH, readFromLines[1][2]);
	}
	
	@Test
	public void mustReadMapWithTwoLinesAndFourColumns() {
		//@formatter:off
		SQM[][] readFromLines = MapReader.readFromLines(Arrays.asList("  ", 
																	  "####"));
		//@formatter:on
		assertEquals(2, readFromLines.length);
		assertEquals(4, readFromLines[0].length);
		assertEquals(4, readFromLines[1].length);
		
		assertNotNull(readFromLines[0][0]);
		assertNotNull(readFromLines[0][1]);
		assertNull(readFromLines[0][2]);
		assertNull(readFromLines[0][3]);
		
		assertNotNull(readFromLines[1][0]);
		assertNotNull(readFromLines[1][1]);
		assertNotNull(readFromLines[1][2]);
		assertNotNull(readFromLines[1][3]);
	}
	
	@Test(expected = SQMReadException.class)
	public void mustThrownErrorBecauseSQMIsNotValid() {
		//@formatter:off
		MapReader.readFromLines(Arrays.asList("   ", 
										      "A"));
		//@formatter:on
	}

}