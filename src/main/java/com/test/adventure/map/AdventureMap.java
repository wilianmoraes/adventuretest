package com.test.adventure.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AdventureMap {

	private SQMType map[][];
	
	private int verticalPosition;
	private int horizontalPosition;
	
}
