package com.gfi.adventure.exception;

import com.gfi.adventure.enums.Movement;

/**
 * This exception must be throw when some error occur while trying to read a {@link Movement} from a file.
 * 
 * @author Wilian Moraes
 *
 */
public class MovementReadException extends RuntimeException {

	private static final long serialVersionUID = -4457393520856940654L;

	public MovementReadException(char charr) {
		super("Char: " + charr + " is not a valid Movement Direction! Use: S for South, N for North, E for East and O for West");
	}

}
