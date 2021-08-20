package com.artificer.matrixlog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryInputModel {

	@Valid
	@NotNull
	private ClientIdInput client;
	@Valid
	@NotNull
	private RecipientInputModel recipient;

	@NotNull
	private BigDecimal tax;

}
