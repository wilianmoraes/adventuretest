package com.test.gfi.adventure;

import java.net.URL;

import org.junit.Test;

import com.gfi.adventure.App;

public class AppTest {

	@Test
	public void mustRunApp() {
		App.main(new String[] { "-f", pathToResourceFile("scenario_1.txt").substring(1) });
	}
	
	@Test(expected = RuntimeException.class)
	public void mustThrowExceptionInvalidArg() {
		App.main(new String[] { "-a", pathToResourceFile("scenario_1.txt").substring(1) });
	}
	
	@Test(expected = RuntimeException.class)
	public void mustThrowExceptionNoFileFound() {
		App.main(new String[] { "-f"});
	}

	private String pathToResourceFile(String fileName) {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		URL resource = classloader.getResource(fileName);
		return resource.getPath();
	}
}
