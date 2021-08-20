package com.artificer.matrixlog.api.model.output;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RecipientModelOutput {

	

	@NotBlank
	private String name;

	@NotBlank
	private String address;

	@NotBlank
	private String number;

	@NotBlank
	private String complement;

	@NotBlank
	private String district;

	
}
