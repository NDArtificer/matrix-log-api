package com.artificer.matrixlog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.model.Event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EventRegistrationService {

	private DeliverySummaryService deliveryService;

	@Transactional
	public Event register(Long deliveryId, String description) {
		Delivery delivery = deliveryService.find(deliveryId);

		return delivery.addEventDescription(description);
	}

}
