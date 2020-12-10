package cn.tedu.store.ex;

import java.io.Serializable;

public class UsernameAlreadyExistException extends RuntimeException implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8409569124396629345L;

	public UsernameAlreadyExistException() {

	}

	public UsernameAlreadyExistException(String msg) {
		super(msg);
	}

}
