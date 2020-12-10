package cn.tedu.store.ex;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6473945226188596387L;
	public UserNotFoundException() {

	}

	public UserNotFoundException(String msg) {
		super(msg);
	}
}
