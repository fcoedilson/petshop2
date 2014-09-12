package br.com.petshop.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CepConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component, String cepInteger) throws ConverterException {
			if (cepInteger.isEmpty()) {
				return null;
			}
			return cepInteger.replace(".", "").replace("-", "").replace(" ", "");
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component, Object cepInteger) throws ConverterException {
			if (cepInteger == null) {
				return null;
			}
			String cep = String.valueOf(cepInteger);

			if ((cep != null) && (cep.length() == 8)){
				cep = cep.substring(0,2) + "." + cep.substring(2,5) + "-" + cep.substring(5,8);
			}
			return cep;
		}

		
}
