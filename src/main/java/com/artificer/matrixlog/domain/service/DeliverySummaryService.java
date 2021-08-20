package com.artificer.matrixlog.domain.service;

import org.springframework.stereotype.Service;

import com.artificer.matrixlog.domain.Exception.EntityNotFoundException;
import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class DeliverySummaryService {

	private DeliveryRepository deliveryRepository;

	public Delivery find(Long deliveryId) {
		return deliveryRepository.findById(deliveryId)
				.orElseThrow(() -> new EntityNotFoundException("Delivery not found!"));

	}

}
