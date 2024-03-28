package com.litografiaartesplanchas.clientservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.repository.IClientRepository;

@Service
public class ClientService {
	@Autowired
	private IClientRepository repository;
	
	public List<Client> getAll(){
		return repository.findAll();
	}
	
	public Optional<Client> getClientById(Long id) {
		return repository.findById(id);
	}
	
	/*
	public Client save(Client client) {
		return repository.save(client);
	}*/
	
	/* To do
	public boolean updateById(Long id, Client updateDataClient) {
		Optional<Client> optional = repository.findById(id);
		if(optional.isPresent()) {
			Client client = optional.get();
			client.setEmail(updateDataClient.getEmail());
			
			return true;
		}
		return false;
	}*/
}
