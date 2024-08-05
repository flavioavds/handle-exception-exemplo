package br.com.cassol.cas_ms_exception.validation;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import br.com.cassol.cas_ms_exception.interfaces.ValidationMessages;

@Component
public class ValidationMessagesImpl implements ValidationMessages {

	@Override
	public String getErrorMessage(String key, Object argument, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("messages/messages", locale);
		String message = bundle.getString(key);
		return String.format(message, argument);
	}

	@Override
	public String getFieldName(Object request, String fieldName) {
		try {
			Field field = request.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.getName();
		} catch (NoSuchFieldException e) {
			return "unknown_field: " + fieldName;
		}
	}
}