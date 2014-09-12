package br.com.petshop.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class TelefoneConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,String telInteger) throws ConverterException {
		if (telInteger.isEmpty()) {
			return null;
		}
		return telInteger.replace("(", "").replace(")", "").replace("-", "").replace(" ", "");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,Object telInteger) throws ConverterException {
		if (telInteger == null) {
			return null;
		}
		String tel = String.valueOf(telInteger);

		tel = "(" + tel.substring(0, 2) + ")" + tel.substring(2, 6) + "-" + tel.substring(6);
		return tel;
	}

}
