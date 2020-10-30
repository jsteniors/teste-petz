package br.com.petz.exception;

public class BusinessException extends RuntimeException {
	
	public BusinessException() {}
	public BusinessException(String message) {super(message);}
	public BusinessException(String message, Throwable cause) {super(message, cause);}
	public BusinessException(Throwable cause) {super(cause);}
	
	private static final long serialVersionUID = 1L;
}
