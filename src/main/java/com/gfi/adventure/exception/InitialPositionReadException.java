package com.gfi.adventure.exception;

import com.gfi.adventure.model.AdventurousPosition;

/**
 * This exception must be throw when some error occur while trying to read the initial position( {@link AdventurousPosition}) from a file.
 * 
 * @author Wilian Moraes
 *
 */
public class InitialPositionReadException extends RuntimeException {

	private static final long serialVersionUID = -8333302163346565272L;

	public InitialPositionReadException() {
		super("Failed to read Adventurous initial position from file, could not find a valid X,Y number, please use dot or comma as delimiter and inform two valid numbers.");
	}

}
