package net.bynaryscode.util;

public class DefaultExceptionParser implements ExceptionParser {

	@Override
	public String parseException(Throwable e) {
		return e.getMessage() == null ? e.getClass().getName() : e.getMessage();
	}
}
