package com.litografiaartesplanchas.clientservice.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.service.ClientService;
import com.litografiaartesplanchas.clientservice.utils.Response;
import com.litografiaartesplanchas.clientservice.utils.ResponseBody;

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController {
	@Autowired
	private ClientService service;
	
	/**
	 * This function returns a list of all clients.
	 * 
	 * @return A list of `Client` objects is being returned by the `getAll` method.
	 */
	@GetMapping(value = "/")
	public ResponseEntity<?> getAll(){
		try {
			List<Client> clients = service.getAll();
			if(clients.isEmpty()) {
				return Response.noContent();
			}
			return Response.ok(service.getAll());
		}catch(Exception e) {
			System.out.println(e);
			return Response.badRequest();
		}
	}
	
	/**
	 * This Java function retrieves a client by ID and returns a ResponseEntity with the client object if
	 * found, or a NOT_FOUND status if not found.
	 * 
	 * @param id The `id` parameter in the `@GetMapping` annotation represents the unique identifier of
	 * the client that you want to retrieve from the server. This method is designed to handle GET
	 * requests for fetching a specific client based on their ID.
	 * @return The code snippet is a method that handles a GET request mapping to "/{id}". It retrieves a
	 * Client object by its ID from the service layer and returns a ResponseEntity containing the Client
	 * object with an HTTP status of OK (200) if the client is found. If the client is not found, it
	 * returns a ResponseEntity with an HTTP status of NOT_FOUND (404).
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseBody> getById(@PathVariable long id){
		try {
			Optional<Client> optionalClient = service.getClientById(id);
			if(optionalClient.isEmpty()) {
				return Response.notFound();
			}
			Client client = optionalClient.get();
			ArrayList<Client> data = new ArrayList<Client>();
			data.add(client);
			return Response.ok(data);
		}catch(Exception e) {
			System.out.println(e);
			return Response.badRequest();
		}
	}
}
