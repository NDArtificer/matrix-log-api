package com.artificer.matrixlog.domain.Exception;

public class BusinessException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public BusinessException(String message) {
		super(message);
	}
	
}
