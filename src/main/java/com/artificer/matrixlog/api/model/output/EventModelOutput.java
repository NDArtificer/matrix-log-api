package com.artificer.matrixlog.api.model.output;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventModelOutput {

	private Long id;
	private String description;
	private OffsetDateTime registrationDate;
}
