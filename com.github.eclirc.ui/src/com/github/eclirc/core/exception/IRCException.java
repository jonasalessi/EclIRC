package com.github.eclirc.core.exception;

public class IRCException extends Exception {

	private static final long serialVersionUID = -3167005881583066568L;

	public IRCException(Throwable casuse, String msg) {
		super(msg, casuse);
	}
}
