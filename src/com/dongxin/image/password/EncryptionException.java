package com.dongxin.image.password;

/**
 * @author Wang Donghua Oct 21, 2014
 * 
 */
public class EncryptionException extends ImagePassException {

	private static final long serialVersionUID = 8409092332641985616L;

	public EncryptionException() {
		super();
	}

	public EncryptionException(String message) {
		super(message);
	}

	public EncryptionException(String message, Throwable cause) {
		super(message, cause);
	}

	public EncryptionException(Throwable cause) {
		super(cause);
	}
}
