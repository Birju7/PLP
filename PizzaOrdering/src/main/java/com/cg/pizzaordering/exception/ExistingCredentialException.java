package com.cg.pizzaordering.exception;
public class ExistingCredentialException extends Exception {

	private static final long serialVersionUID = 1L;

	//Default constructor
	public ExistingCredentialException() {
	}
	
	public ExistingCredentialException(String exception) {
		super(exception);
	}
	
}
