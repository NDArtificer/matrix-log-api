package com.artificer.matrixlog.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.artificer.matrixlog.api.model.input.ClientUserInputModel;
import com.artificer.matrixlog.api.model.output.ClientUserModelOutput;
import com.artificer.matrixlog.domain.model.ClientUser;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class ClientUserConverter {

	private ModelMapper modelMapper;

	public ClientUserModelOutput toModel(ClientUser client) {
		return modelMapper.map(client, ClientUserModelOutput.class);
	}

	public List<ClientUserModelOutput> toCollectionModel(List<ClientUser> clients){
		return clients.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public ClientUser toEntity(ClientUserInputModel clientInputModel) {
		return modelMapper.map(clientInputModel, ClientUser.class);
	}

	public void copyToDaminObject(ClientUserInputModel clientUserInput, ClientUser clientAtual) {
		modelMapper.map(clientUserInput, clientAtual);
	}
	

}
