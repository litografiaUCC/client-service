package com.litografiaartesplanchas.clientservice.service;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.repository.IClientRepository;
import com.litografiaartesplanchas.clientservice.utils.errors.ConflictException;

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
	
	/**
	 * This Java function updates the status of a client by setting isActive to false in the repository
	 * based on the provided ID.
	 * 
	 * @param id The `id` parameter is the unique identifier of the client whose status needs to be
	 * updated.
	 * @return The `patchStatusById` method returns an integer value. The possible return values are:
	 * - 200: If the operation is successful and the client's status is updated.
	 * - 404: If the client with the specified id is not found in the repository.
	 * - 409: If the client is found but is already inactive.
	 * - 400: If an exception occurs during the execution of the
	 */
	public int patchStatusById(long id){
		try {
			Optional<Client> optional = repository.findById(id);
			if(optional.isEmpty()) return 404;
			Client client = optional.get();
			if(!client.getIsActive()) return 409;
			client.setIsActive(false);
			repository.save(client);
			return 200;
		}catch(Exception e) {
			System.out.println(e);
			return 400;
		}
	}

	/**
	 * The `create` function in Java checks if a client with the same email or document number already
	 * exists in the repository and throws a ConflictException if so, otherwise it saves the client.
	 * 
	 * @param client The `client` parameter is an object of type `Client` that represents a client entity.
	 * It contains information such as the client's email, document number, and other details. This method
	 * `create` is responsible for creating a new client in the system.
	 */
	public void create(Client client) throws ConflictException{
		if(repository.existsByEmail(client.getEmail()) || repository.existsByNumberDocument(client.getNumberDocument())){
			String fieldRegistered =  repository.existsByEmail(client.getEmail()) ? "email" : "document number";
			throw new ConflictException("A client with this "+ fieldRegistered +" is already registered");
		}
		repository.save(client);
	}
	
	
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
