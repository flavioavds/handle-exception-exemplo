package com.handle.exception.exception.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.cassol.cas_ms_exception.interfaces.ErrorHandleResponse;
import br.com.cassol.cas_ms_exception.service.ErrorHandleResponseImpl;

@Configuration
public class AppConfig {

	@Bean(name = "productErrorHandleResponse")
	ErrorHandleResponse errorHandleResponse() {
		return new ErrorHandleResponseImpl();
	}
}