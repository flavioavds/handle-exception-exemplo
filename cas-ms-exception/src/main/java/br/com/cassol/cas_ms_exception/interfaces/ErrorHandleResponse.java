package br.com.cassol.cas_ms_exception.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public interface ErrorHandleResponse {

	Map<String, Object> createCustomErrorResponse(HttpStatus status, List<Map<String, String>> errors, String path);

	Map<String, String> createError(String key, String message);
}