package com.artificer.matrixlog.api.model.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.artificer.matrixlog.domain.model.DeliveryStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryModelOutput {

	private Long id;
	private ClientSummaryModel client;
	private RecipientModelOutput recipient;
	private BigDecimal tax;
	private DeliveryStatus status;
	private OffsetDateTime recordDate;
	private OffsetDateTime endDate;
}
