package com.test.adventure.map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SQMType {
	PATH(true), WOOD(false);
	
	private boolean walkable;
	
}
