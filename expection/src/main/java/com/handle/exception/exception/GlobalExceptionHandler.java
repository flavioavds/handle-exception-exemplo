package com.handle.exception.exception;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.cassol.cas_ms_exception.exception.errors.ApiErrorDTO;
import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import br.com.cassol.cas_ms_exception.exception.errors.ErrorDTO;
import br.com.cassol.cas_ms_exception.interfaces.ErrorHandleResponse;

@RestControllerAdvice
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	private final ErrorHandleResponse errorHandleResponse;

	public GlobalExceptionHandler(ErrorHandleResponse errorHandleResponse) {
		this.errorHandleResponse = errorHandleResponse;
	}

	@Override
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<ErrorDTO> errorList = ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> new ErrorDTO(fieldError.getField(), fieldError.getDefaultMessage()))
				.collect(Collectors.toList());

		ApiErrorDTO apiError = new ApiErrorDTO(new Date(), HttpStatus.UNPROCESSABLE_ENTITY.value(),
				HttpStatus.UNPROCESSABLE_ENTITY.name(), new HashSet<>(errorList));

		return new ResponseEntity<>(apiError, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Object> handleCustomException(CustomException ex, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		List<Map<String, String>> errors = ex.getErrors().stream().map(error -> {
			String localizedMessage = error.getMessage();
			return this.errorHandleResponse.createError(error.getKey(), localizedMessage);
		}).collect(Collectors.toList());
		Map<String, Object> errorResponse = this.errorHandleResponse.createCustomErrorResponse(HttpStatus.BAD_REQUEST,
				errors, path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		System.out.println("CustomException: " + errors);
		return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
		CustomError customError = new CustomError("INVALID_ARGUMENT", ex.getMessage(), null);
		List<CustomError> errors = Collections.singletonList(customError);
		CustomException customException = new CustomException(errors);
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		List<Map<String, String>> errorResponses = customException.getErrors().stream().map(error -> {
			String localizedMessage = error.getMessage();
			return this.errorHandleResponse.createError(error.getKey(), localizedMessage);
		}).collect(Collectors.toList());
		Map<String, Object> errorResponse = this.errorHandleResponse.createCustomErrorResponse(HttpStatus.BAD_REQUEST,
				errorResponses, path);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
	}

	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		String path = ((ServletWebRequest) request).getRequest().getRequestURI();
		List<Map<String, String>> errors = ex.getBindingResult().getFieldErrors().stream().map(fieldError -> {
			String field = fieldError.getField();
			String message = fieldError.getDefaultMessage();
			return this.errorHandleResponse.createError(field, message);
		}).collect(Collectors.toList());
		Map<String, Object> errorResponse = this.errorHandleResponse.createCustomErrorResponse(HttpStatus.BAD_REQUEST,
				errors, path);
		headers.setContentType(MediaType.APPLICATION_JSON);
		return new ResponseEntity<>(errorResponse, headers, HttpStatus.BAD_REQUEST);
	}
}
