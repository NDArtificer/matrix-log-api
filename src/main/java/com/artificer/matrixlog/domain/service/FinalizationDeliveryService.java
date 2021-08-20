package com.artificer.matrixlog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.repository.DeliveryRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizationDeliveryService {

	
	private DeliverySummaryService deliveryService;
	private DeliveryRepository deliveryRepository;
	
	
	@Transactional
	public void conclude(Long deliveryId) {
		
		Delivery delivery = deliveryService.find(deliveryId);				
		delivery.conclude();		
		deliveryRepository.save(delivery);
	}
	
	
	@Transactional
	public void cancel(Long deliveryId) {
		
		Delivery delivery = deliveryService.find(deliveryId);				
		delivery.cancel();		
		deliveryRepository.save(delivery);
	}
	
}
