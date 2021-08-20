package com.artificer.matrixlog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.artificer.matrixlog.domain.Exception.BusinessException;
import com.artificer.matrixlog.domain.model.Client;
import com.artificer.matrixlog.domain.model.Delivery;
import com.artificer.matrixlog.domain.model.DeliveryStatus;
import com.artificer.matrixlog.domain.repository.ClientRepository;
import com.artificer.matrixlog.domain.repository.DeliveryRepository;

@Service
public class DeliveryRegistrationService {

	@Autowired
	private DeliveryRepository deliveryRepository;

	@Autowired
	private ClientRepository clientRepository;

	@Transactional
	public Delivery request(Delivery delivery) {

		Client client = clientRepository.findById(delivery.getClient().getId())
				.orElseThrow(() -> new BusinessException("Client not Found!"));

		delivery.setClient(client);
		delivery.setStatus(DeliveryStatus.PENDING);
		delivery.setRecordDate(OffsetDateTime.now());

		return deliveryRepository.save(delivery);
	}
}
