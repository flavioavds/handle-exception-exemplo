package br.com.cassol.cas_ms_exception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cassol.cas_ms_exception.interfaces.ErrorHandleResponse;
import br.com.cassol.cas_ms_exception.interfaces.ValidationMessages;
import br.com.cassol.cas_ms_exception.service.ErrorHandleResponseImpl;
import br.com.cassol.cas_ms_exception.validation.ValidationMessagesImpl;

@Configuration
public class AppConfig {

	@Bean
	ValidationMessages validationMessages() {
		return new ValidationMessagesImpl();
	}

	@Bean
	ErrorHandleResponse errorHandleResponse() {
		return new ErrorHandleResponseImpl();
	}
}