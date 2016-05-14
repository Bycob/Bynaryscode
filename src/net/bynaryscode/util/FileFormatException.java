package net.bynaryscode.util;

import java.io.IOException;

@SuppressWarnings("serial")
public class FileFormatException extends IOException {
	
	public FileFormatException() {
		super();
	}
	
	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public FileFormatException(String message) {
		super(message);
	}
	
	public FileFormatException(Throwable cause) {
		super(cause);
	}
}
