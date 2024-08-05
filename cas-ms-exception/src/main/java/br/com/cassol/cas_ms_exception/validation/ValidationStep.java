package br.com.cassol.cas_ms_exception.validation;

import java.util.ArrayList;
import java.util.List;

import br.com.cassol.cas_ms_exception.exception.errors.CustomError;
import br.com.cassol.cas_ms_exception.interfaces.ValidationRule;
import lombok.Getter;

public class ValidationStep<T> {
	private List<ValidationRule<T>> rules;
	@Getter 
	private ArrayList<CustomError> errors;

	public ValidationStep() {
		this.rules = new ArrayList<>();
		this.errors = new ArrayList<>();
	
	}
	
	public void addRule(ValidationRule<T> rule) {
		this.rules.add(rule);
	}

	public void validate(T entity) {
		for (ValidationRule<T> rule : this.rules) {
			if (!rule.isValid(entity)) {
				this.errors.add(rule.getError());
			}
		}
	}
}