package com.artificer.matrixlog.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.artificer.matrixlog.api.model.output.EventModelOutput;
import com.artificer.matrixlog.domain.model.Event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class EventConverter {

	private ModelMapper modelMapper;
	
	
	public EventModelOutput toModel(Event event) {
		return modelMapper.map(event, EventModelOutput.class);
	}
	
	public List<EventModelOutput> toCollectionModel(List<Event> events){
		return events.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
