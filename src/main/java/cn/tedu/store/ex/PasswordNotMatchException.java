package cn.tedu.store.ex;

import java.io.Serializable;

public class PasswordNotMatchException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1576619175410961342L;
	public PasswordNotMatchException() {

	}

	public PasswordNotMatchException(String msg) {
		super(msg);
	}
}
