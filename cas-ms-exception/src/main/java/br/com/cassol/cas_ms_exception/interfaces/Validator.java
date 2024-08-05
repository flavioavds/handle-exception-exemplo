package br.com.cassol.cas_ms_exception.interfaces;

import java.util.ArrayList;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.exception.errors.CustomException;
import br.com.cassol.cas_ms_exception.validation.ValidationStep;
import lombok.Getter;
import lombok.Setter;

public class Validator<T> {
	@Setter
	private ArrayList<CustomError> errors = new ArrayList<>();
	@Getter
	private final ValidationStep<T> validationStep = new ValidationStep<>();
	
	public void validate() {
		var errors = this.validationStep.getErrors();
		errors.stream().findFirst().ifPresent(error -> {
			throw new CustomException(errors);
		});	
	}

	public Validator withExtraValidator(Validator next) {
		next.setErrors(this.validationStep.getErrors());
		return next;
	}
}
