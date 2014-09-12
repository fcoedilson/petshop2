package br.com.petshop.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CnpjConverter implements Converter {

		@Override
		public Object getAsObject(FacesContext context, UIComponent component,String cnpjInteger) throws ConverterException {
			if (cnpjInteger.isEmpty()) {
				return null;
			}
			return cnpjInteger.replace("/", "").replace(".", "").replace("-", "").replace(" ", "");
		}

		@Override
		public String getAsString(FacesContext context, UIComponent component,Object cnpjInteger) throws ConverterException {
			if (cnpjInteger == null) {
				return null;
			}
			String cnpj = String.valueOf(cnpjInteger);

			if ((cnpj != null) && (cnpj.length() == 14)){
				cnpj = cnpj.substring(0,2) + "." + cnpj.substring(2,5) + "." + cnpj.substring(5,8) + "/" + cnpj.substring(8,12) + "-" + cnpj.substring(12,14);
			}
			return cnpj;
		}

		
}
