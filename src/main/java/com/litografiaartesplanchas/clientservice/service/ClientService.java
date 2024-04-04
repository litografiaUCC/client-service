package com.litografiaartesplanchas.clientservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.model.TypeDocument;
import com.litografiaartesplanchas.clientservice.repository.IClientRepository;
import com.litografiaartesplanchas.clientservice.repository.ITypeDocumentRepository;
import com.litografiaartesplanchas.clientservice.utils.errors.ConflictException;
import com.litografiaartesplanchas.clientservice.utils.errors.NotFoundException;

@Service
public class ClientService {
	@Autowired
	private IClientRepository repository;
	@Autowired
	private ITypeDocumentRepository typeDocumentRepository;
	
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
	 * This Java function updates the status of a client by setting their isActive attribute to false,
	 * throwing exceptions if the client is not found or already deactivated.
	 * 
	 * @param id The `id` parameter is the unique identifier of the client whose status needs to be
	 * updated.
	 */
	public void patchStatusById(long id) throws ConflictException, NotFoundException{
		Optional<Client> optional = repository.findById(id);
		if(optional.isEmpty()) throw new NotFoundException("Client not found");
		Client client = optional.get();
		if(!client.getIsActive()) throw new ConflictException("Account already deactivated");
		client.setIsActive(false);
		repository.save(client);
	}

	/**
	 * The `create` method in Java checks for existing clients by email or document number, validates the
	 * type of document, and saves a new client to the repository.
	 * 
	 * @param client The `client` parameter in the `create` method represents an object of the `Client`
	 * class. This object contains information about a client, such as their email, document number, and
	 * type of document. The method checks if a client with the same email or document number already
	 * exists in the repository
	 */
	public void create(Client client) throws ConflictException{
		if(repository.existsByEmail(client.getEmail()) || repository.existsByNumberDocument(client.getNumberDocument())){
			String fieldRegistered =  repository.existsByEmail(client.getEmail()) ? "email" : "document number";
			throw new ConflictException("A client with this "+ fieldRegistered +" is already registered");
		}
		if(client.getTypeDocument() == null) {
			throw new ConflictException("Type Document can't be null");
		}else {
			Optional<TypeDocument> type = typeDocumentRepository.findById((long) client.getTypeDocument().getId());
			if(type.isEmpty()) {
				throw new ConflictException("Type Document invalid");
			}else {
				client.setTypeDocument(type.get());
			}
		}
		repository.save(client);
	}
	
	/**
	 * The `updateById` method updates a client entity with the provided data, handling exceptions for
	 * client not found and conflicts with type document.
	 * 
	 * @param dataUpdateClient The `updateById` method you provided updates a `Client` entity in the
	 * repository based on the data provided in the `dataUpdateClient` object. Here's a breakdown of how
	 * the method works:
	 */
	public void updateById( Client dataUpdateClient) throws NotFoundException, ConflictException{
		Optional<Client> optional = repository.findById((long) dataUpdateClient.getId());
		if(optional.isEmpty()) throw new NotFoundException("Client not found");
		Client client = optional.get();
		if(dataUpdateClient.getName() != null) client.setName(dataUpdateClient.getName());
		if(dataUpdateClient.getLastName() != null) client.setLastName(dataUpdateClient.getLastName());
		if(dataUpdateClient.getTypePerson() != null) client.setTypePerson(dataUpdateClient.getTypePerson());
		if(dataUpdateClient.getEmail() != null) client.setEmail(dataUpdateClient.getEmail());
		if(dataUpdateClient.getPassword() != null) client.setPassword(dataUpdateClient.getPassword());
		if(dataUpdateClient.getPhone() != null) client.setPhone(dataUpdateClient.getPhone());
		if(dataUpdateClient.getPhoto() != null) client.setPhoto(dataUpdateClient.getPhoto());
		if(dataUpdateClient.getNumberDocument() != null) client.setNumberDocument(dataUpdateClient.getNumberDocument());
		if(dataUpdateClient.getTypeDocument() != null) {
			Optional<TypeDocument> typeToUpdate = typeDocumentRepository.findById((long) dataUpdateClient.getTypeDocument().getId());
			if(typeToUpdate.isEmpty()) {
				throw new ConflictException("Type Document invalid");
			}else {
				client.setTypeDocument(typeToUpdate.get());
			}
		}
		repository.save(client);
	}
}
