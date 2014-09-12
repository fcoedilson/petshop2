package br.com.petshop.rest.validacao;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;

public abstract class BaseValidator implements IValidator {

	private MessagesValidators messagesValidators 	= new MessagesValidators();
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> validatedFields = new HashedMap();

	@Override
	public IValidator addValidatedField(String key, Object obj) {
		
		key = StringUtils.trimToEmpty(key);

		if(obj instanceof String){
			obj = StringUtils.trimToEmpty((String) obj);
		}
		
		if (setValidParametersName().contains(key)) {
			validatedFields.put(key, obj);
			return this;
		}
		throw new InvalidParameterException("Esse validador aceita apenas os seguintes parametros: " + setValidParametersName().toString());
	}

	protected Object getValidatedParameter(String key) {
		try {
			return validatedFields.get(key);
		} catch (NullPointerException e) {
			throw new NullPointerException("Voce precisa adicionar os seguintes parametros: " + setValidParametersName() + ". Utilize o metodo addValidatedField(String, Object) para defini-los");
		}
	}
	
	protected abstract List<String> setValidParametersName();

	protected List<String> setValidParametersName(String... names) {
		return Arrays.asList(names);
	}

	protected MessagesValidators getMessagesValidators() {
		return messagesValidators;
	}
}
