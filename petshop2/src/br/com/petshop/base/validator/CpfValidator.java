package br.com.petshop.base.validator;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

public class CpfValidator implements Validator{

	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		if (!validaCpf(String.valueOf(value))){
			throw new ValidatorException(MensagemValidator.MensagensValidator.mensagemErro("CPF incorreto", "cpf"));
		}		
	}	
	@SuppressWarnings("cast")
	public static boolean validaCpf(String cpfInteger){
		if(conferirCpf(cpfInteger)){
			return false;
		}		
		char dig10, dig11;
		int sm, i, r, num, peso;
		
		try {
			//Primeiro digito
			sm = 0;
			peso = 10;
			for(i = 0; i < 9; i++){
				num = (int) cpfInteger.charAt(i) - 48;
				sm += (num * peso);
				peso -= 1;
			}			
			r = 11 - (sm % 11);			
			dig10 = r == 10 || r == 11 ? '0' : (char) (r + 48);
			
			//Segundo digito
			sm = 0;
			peso = 11;
			for (i = 0; i < 10; i++){
				num = (int) cpfInteger.charAt(i) - 48;
				sm += (num * peso);
				peso -= 1;
			}			
			r = 11 - (sm % 11);			
			dig11 = r == 10 || r == 11 ? '0' : (char) (r + 48); 
			
			if(dig10 == cpfInteger.charAt(9) && dig11 == cpfInteger.charAt(10)){
				return true;
			}
			return false;			
		} catch (Exception e) {
			return false;
		}
	}
	
	private static boolean conferirCpf(String cpfInteger){
		return cpfInteger == null || cpfInteger.length() != 11 || cpfInteger.equals("00000000000")
			   ||cpfInteger.equals("11111111111")  || cpfInteger.equals("22222222222") || cpfInteger.equals("33333333333") 
			   || cpfInteger.equals("44444444444") || cpfInteger.equals("55555555555") || cpfInteger.equals("66666666666") 
			   || cpfInteger.equals("77777777777") || cpfInteger.equals("88888888888") || cpfInteger.equals("99999999999");
	}
}
