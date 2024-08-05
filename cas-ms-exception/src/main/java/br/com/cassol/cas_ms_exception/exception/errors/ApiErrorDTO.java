package br.com.cassol.cas_ms_exception.exception.errors;

import java.util.Date;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorDTO {

	private Date timestamp;
	private Integer status;
	private String code;
	private Set<ErrorDTO> errors;

}