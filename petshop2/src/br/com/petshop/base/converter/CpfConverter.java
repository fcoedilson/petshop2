package br.com.petshop.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CpfConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component,String cpfInteger) throws ConverterException {
			if (cpfInteger.isEmpty()) {
				return null;
			}
			return cpfInteger.replace(".", "").replace("-", "").replace(" ", "");
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,Object cpfInteger) throws ConverterException {
			if (cpfInteger == null) {
				return null;
			}
			String cpf = String.valueOf(cpfInteger);

			if ((cpf != null) && (cpf.length() == 11)){
				cpf = cpf.substring(0,3) + "." + cpf.substring(3,6) + "." + cpf.substring(6,9) + "-"  + cpf.substring(9,11);
			}
			return cpf;
		}

		
}
