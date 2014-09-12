package br.com.petshop.architecture.modules;

public class TitleBeanNotFoundException extends RuntimeException{
	private static final long serialVersionUID = -5699659812830144226L;

	public TitleBeanNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
