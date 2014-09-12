package br.com.petshop.base.converter;

import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@ManagedBean
@RequestScoped
@FacesConverter(forClass = String.class)
public class UpperCaseConverter implements Converter {
	
	private static final String KEY_CONVERTER_TAG = "styleClass";
	
	private static final String VALOR_EXCLUSAO = "naoUpperCase";

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}

		if (!(component instanceof HtmlInputText)) {
			return value.toString();
		}
		
		return convert(component, value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}

		if (!(component instanceof HtmlInputText)) {
			return value.toString();
		}

		return convert(component, value.toString()).toString();
	}
	
	private Object convert(UIComponent component, String value) {
        if (isInativo(component, VALOR_EXCLUSAO)) {
            return value.toString();
        }

        return value.toString().toUpperCase(Locale.getDefault());
	}
	
	private static boolean isInativo(UIComponent component, String valorExclusao) {
		Map<String, Object> attributes = component.getAttributes();

		String styleClass = (String) attributes.get(KEY_CONVERTER_TAG);

		if (styleClass == null) {
			return false;
		}

		int indexOf = styleClass.indexOf(valorExclusao);
		return indexOf != -1;
	}

}
