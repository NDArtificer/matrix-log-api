package com.artificer.matrixlog.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.artificer.matrixlog.api.model.input.DeliveryInputModel;
import com.artificer.matrixlog.api.model.output.DeliveryModelOutput;
import com.artificer.matrixlog.domain.model.Delivery;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class DeliveryConverter {

	private ModelMapper modelMapper;

	public DeliveryModelOutput toModel(Delivery delivery) {
		return modelMapper.map(delivery, DeliveryModelOutput.class);
	}

	public List<DeliveryModelOutput> toCollectionModel(List<Delivery> deliveries){
		return deliveries.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Delivery toEntity(DeliveryInputModel deliveryInputModel) {
		return modelMapper.map(deliveryInputModel, Delivery.class);
	}
}
