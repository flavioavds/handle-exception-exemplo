package br.com.cassol.cas_ms_exception.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import br.com.cassol.cas_ms_exception.interfaces.ErrorHandleResponse;

@Component
public class ErrorHandleResponseImpl implements ErrorHandleResponse {

	@Override
	public Map<String, Object> createCustomErrorResponse(HttpStatus status, List<Map<String, String>> errors,
			String path) {
		Map<String, Object> errorResponse = new HashMap<>();
		errorResponse.put("timestamp", Instant.now());
		errorResponse.put("status", status.value());
		errorResponse.put("error", status.getReasonPhrase());
		errorResponse.put("errors", errors);
		errorResponse.put("path", path);
		return errorResponse;
	}

	@Override
	public Map<String, String> createError(String key, String message) {
		Map<String, String> error = new HashMap<>();
		error.put("key", key);
		error.put("message", message);
		return error;
	}
}