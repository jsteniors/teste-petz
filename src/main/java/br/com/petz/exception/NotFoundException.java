package br.com.petz.exception;

public class NotFoundException extends RuntimeException {
	
	public NotFoundException() {}
	public NotFoundException(String message) {super(message);}
	public NotFoundException(String message, Throwable cause) {super(message, cause);}
	
	private static final long serialVersionUID = 1L;
}
