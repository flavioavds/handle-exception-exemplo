package br.com.cassol.cas_ms_exception.exception.errors;

import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomError extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private String key;
	private String message;
	private Locale locale;

	public CustomError(String message) {
		this.message = message;
	}

	public CustomError(String key, String message) {
		this.key = key;
		this.message = message;
	}

}
