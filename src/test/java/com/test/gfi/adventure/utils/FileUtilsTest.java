package com.test.gfi.adventure.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URL;
import java.util.List;

import org.junit.Test;

import com.gfi.adventure.utils.FileUtils;

public class FileUtilsTest {

	@Test
	public void mustReadFile() {
		List<String> readLinesFromFile = FileUtils.readLinesFromFile(pathToResourceFile("scenario_2.txt").substring(1));

		assertNotNull(readLinesFromFile);
		assertEquals(false, readLinesFromFile.isEmpty());
	}

	@Test(expected = RuntimeException.class)
	public void mustNotReadFile() {
		FileUtils.readLinesFromFile(pathToResourceFile("nofile.txt").substring(1));
	}

	private String pathToResourceFile(String fileName) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL resource = classloader.getResource(fileName);
		return resource.getPath();
	}
}
