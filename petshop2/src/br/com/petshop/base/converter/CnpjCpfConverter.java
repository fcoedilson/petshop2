package br.com.petshop.base.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class CnpjCpfConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String cnpjCpfInteger) throws ConverterException{
		if(cnpjCpfInteger.isEmpty()){
			return null;
		} else if (cnpjCpfInteger.length() == 11){
			return cnpjCpfInteger.replace(".", "").replace("-", "").replace(" ", "");
		} 
			return cnpjCpfInteger.replace("/", "").replace(".", "").replace("-", "").replace(" ", "");
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cnpjCpfInteger) throws ConverterException{
		if(cnpjCpfInteger == null){
			return null;
		}		
		String cnpjCpf = String.valueOf(cnpjCpfInteger);		
		if(cnpjCpf.length() == 11){
			return cnpjCpf = cnpjCpf.substring(0,3) + "." + cnpjCpf.substring(3,6) + "." + cnpjCpf.substring(6,9) + "-"  + cnpjCpf.substring(9,11);
		} else if(cnpjCpf.length() == 14){
			return cnpjCpf = cnpjCpf.substring(0,2) + "." + cnpjCpf.substring(2,5) + "." + cnpjCpf.substring(5,8) + "/" + cnpjCpf.substring(8,12) + "-" + cnpjCpf.substring(12,14);			
		}
		return cnpjCpf;
	}
}
