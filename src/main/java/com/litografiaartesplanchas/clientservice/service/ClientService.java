package com.litografiaartesplanchas.clientservice.service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.repository.IClientRepository;

@Service
public class ClientService {
	@Autowired
	private IClientRepository repository;
	
	/**
	 * The function getAll() returns a list of all clients from the repository.
	 * 
	 * @return A List of Client objects is being returned.
	 */
	public List<Client> getAll(){
		return repository.findAll();
	}
	
	/**
	 * The function returns an Optional containing a Client object found by its ID in the repository.
	 * 
	 * @param id The `id` parameter is the unique identifier of the client whose information you want to
	 * retrieve from the repository.
	 * @return An Optional object containing a Client entity is being returned.
	 */
	public Optional<Client> getClientById(long id) {
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
