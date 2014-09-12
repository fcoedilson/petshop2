package br.com.petshop.base.exception;

public class PetShopSystemExceptionNotAbortive extends RuntimeException {

	private static final long serialVersionUID = 3347494556988768331L;

	public PetShopSystemExceptionNotAbortive(String message) {
		super(message);
	}

}
