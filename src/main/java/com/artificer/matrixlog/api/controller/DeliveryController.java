package com.artificer.matrixlog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.artificer.matrixlog.api.converter.DeliveryConverter;
import com.artificer.matrixlog.api.model.input.DeliveryInputModel;
import com.artificer.matrixlog.api.model.output.DeliveryModelOutput;
import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.repository.DeliveryRepository;
import com.artificer.matrixlog.domain.service.DeliveryRegistrationService;
import com.artificer.matrixlog.domain.service.FinalizationDeliveryService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/deliveries")
public class DeliveryController {

	private DeliveryRepository deliveryRepository;

	private DeliveryRegistrationService deliveryRegistrationService;

	private DeliveryConverter deliveryConverter;

	private FinalizationDeliveryService deliveryService;

	@GetMapping
	public List<DeliveryModelOutput> ListAll() {
		return deliveryConverter.toCollectionModel(deliveryRepository.findAll());
	}

	@GetMapping("/{deliveryId}")
	public ResponseEntity<DeliveryModelOutput> find(@PathVariable Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.map(delivery -> ResponseEntity.ok(deliveryConverter.toModel(delivery)))
				.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public DeliveryModelOutput request(@RequestBody @Valid DeliveryInputModel deliveryInput) {
		Delivery newDelivery = deliveryConverter.toEntity(deliveryInput);
		return deliveryConverter.toModel(deliveryRegistrationService.request(newDelivery));
	}

	@PutMapping("/{deliveryId}/conclude")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void conclude(@PathVariable Long deliveryId) {
		deliveryService.conclude(deliveryId);
	}
	
	@PutMapping("/{deliveryId}/cancel")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void cancel(@PathVariable Long deliveryId) {
		deliveryService.cancel(deliveryId);
	}
}
