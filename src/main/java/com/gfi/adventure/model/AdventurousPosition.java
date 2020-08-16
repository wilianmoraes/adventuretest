package com.gfi.adventure.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * This class represents the position of an adventurous.
 * 
 * @author Wilian Moraes
 *
 */
@AllArgsConstructor
@Getter
public class AdventurousPosition {

	/**
	 * X
	 */
	private int column;
	/**
	 * Y
	 */
	private int line;
}
