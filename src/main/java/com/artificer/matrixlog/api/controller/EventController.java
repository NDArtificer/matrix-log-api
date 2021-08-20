package com.artificer.matrixlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.matrixlog.api.converter.EventConverter;
import com.artificer.matrixlog.api.model.input.EventInputModel;
import com.artificer.matrixlog.api.model.output.EventModelOutput;
import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.model.Event;
import com.artificer.matrixlog.domain.service.DeliverySummaryService;
import com.artificer.matrixlog.domain.service.EventRegistrationService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("deliveries/{deliveryId}/events")
public class EventController {

	private EventRegistrationService eventService;

	private DeliverySummaryService deliveryService;

	private EventConverter eventConverter;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EventModelOutput register(@PathVariable Long deliveryId, @Valid @RequestBody EventInputModel eventInput) {
		Event eventRegistred = eventService.register(deliveryId, eventInput.getDescription());
		return eventConverter.toModel(eventRegistred);
	}

	@GetMapping
	public List<EventModelOutput> list(@PathVariable Long deliveryId) {
		Delivery delivery = deliveryService.find(deliveryId);

		return eventConverter.toCollectionModel(delivery.getEvents());
	}
}
