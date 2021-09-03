package com.artificer.matrixlog.api.model.input;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientUserInputModel {

	@NotBlank
	private String username;
	
	@NotBlank
	private String password;
	
	
}
