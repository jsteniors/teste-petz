package br.com.petz.exception;

public class ExistingEntityException extends RuntimeException {
	
	public ExistingEntityException() {}
	public ExistingEntityException(String message) {super(message);}
	public ExistingEntityException(String message, Throwable cause) {super(message, cause);}
	
	private static final long serialVersionUID = 1L;
}
