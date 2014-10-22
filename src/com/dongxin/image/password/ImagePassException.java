package com.dongxin.image.password;

/**
 * @author Wang Donghua Oct 21, 2014
 * 
 */
public class ImagePassException extends Exception {

	private static final long serialVersionUID = -8002585540815858886L;

	public ImagePassException() {
		super();
	}

	public ImagePassException(String message) {
		super(message);
	}

	public ImagePassException(String message, Throwable cause) {
		super(message, cause);
	}

	public ImagePassException(Throwable cause) {
		super(cause);
	}
}
