package com.artificer.matrixlog.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.artificer.matrixlog.api.model.input.ClientInputModel;
import com.artificer.matrixlog.api.model.output.ClientModelOutput;
import com.artificer.matrixlog.domain.model.Client;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientConverter {

	private ModelMapper modelMapper;

	public ClientModelOutput toModel(Client client) {
		return modelMapper.map(client, ClientModelOutput.class);
	}

	public List<ClientModelOutput> toCollectionModel(List<Client> clients){
		return clients.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Client toEntity(ClientInputModel clientInputModel) {
		return modelMapper.map(clientInputModel, Client.class);
	}

	public void copyToDaminObject(ClientInputModel clientInput, Client clientAtual) {
		modelMapper.map(clientInput, clientAtual);
	}
	

}
