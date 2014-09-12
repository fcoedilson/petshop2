package br.com.petshop.base.converter;

import java.text.NumberFormat;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

import br.com.petshop.architecture.util.JSFUtil;

@FacesConverter(value="valorNumericoMoedaConverter")
public class ValorNumericoMoedaConverter implements Converter {

	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
		if ((value != null) && !value.equals("")) {
			value = value.replace(".", "").replace(",", ".");
			try {
				Double.parseDouble(value);
			} catch (NumberFormatException e) {
				JSFUtil.addGlobalMessage("converter.numero.moeda", FacesMessage.SEVERITY_WARN);
			}
		}
		return "".equals(value) ? null : Double.parseDouble(value);
	}

	public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMinimumFractionDigits(2);
		nf.setMaximumFractionDigits(2);
		
		return value != null ? nf.format(value) : null;
	}
}