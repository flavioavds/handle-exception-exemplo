package br.com.cassol.cas_ms_exception.interfaces;

import java.util.Locale;

public interface ValidationMessages {

	String getErrorMessage(String key, Object argument, Locale locale);

	String getFieldName(Object request, String fieldName);
}