package com.artificer.matrixlog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecipientInputModel {
	
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
