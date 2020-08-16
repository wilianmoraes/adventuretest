package com.gfi.adventure.exception;

import com.gfi.adventure.enums.SQM;

/**
 * This exception must be throw when some error occur while trying to read a {@link SQM} from a file.
 * 
 * @author Wilian Moraes
 *
 */
public class SQMReadException extends RuntimeException {

	private static final long serialVersionUID = -6100009234633756587L;

	public SQMReadException(char charr) {
		super("Char: " + charr + " is not a valid SQM Use: '#' for Wood and ' ' for path");
	}

}
