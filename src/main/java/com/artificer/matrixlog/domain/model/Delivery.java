package com.artificer.matrixlog.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.groups.ValidationGroups;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Delivery {

	@Id

	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Valid
	@ManyToOne
	@NotNull
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClientId.class)
	private Client client;

	@OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL)
	private List<Event> events = new ArrayList<>();

	@NotNull
	@Valid
	@Embedded
	private Recipient recipient;

	@NotNull
	private BigDecimal tax;

	@JsonProperty(access = Access.READ_ONLY)
	@Enumerated(EnumType.STRING)
	private DeliveryStatus status;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime recordDate;

	@JsonProperty(access = Access.READ_ONLY)
	private OffsetDateTime endDate;

	public Event addEventDescription(String description) {

		Event event = new Event();
		event.setDescription(description);
		event.setRegistrationDate(OffsetDateTime.now());
		event.setDelivery(this);
		this.getEvents().add(event);

		return event;

	}

	public void conclude() {
		if (canNotChangeStatus()) {
			throw new BusinessException("Unable to conclude delivery!");
		}

		setStatus(DeliveryStatus.CONCLUDED);
		setEndDate(OffsetDateTime.now());
	}

	public boolean canChangeStatus() {
		return DeliveryStatus.PENDING.equals(getStatus());
	}

	public boolean canNotChangeStatus() {
		return !canChangeStatus();
	}

	public void cancel() {
		if (canNotChangeStatus()) {
			throw new BusinessException("Unable to cancel delivery!");
		}

		setStatus(DeliveryStatus.CANCELED);
		setEndDate(OffsetDateTime.now());

	}
}
