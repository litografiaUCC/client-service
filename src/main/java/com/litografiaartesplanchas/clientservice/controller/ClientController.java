package com.litografiaartesplanchas.clientservice.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.litografiaartesplanchas.clientservice.model.Client;
import com.litografiaartesplanchas.clientservice.service.ClientService;
import com.litografiaartesplanchas.clientservice.utils.Response;
import com.litografiaartesplanchas.clientservice.utils.ResponseBody;
import com.litografiaartesplanchas.clientservice.utils.errors.ErrorHandlerResponse;
import com.litografiaartesplanchas.clientservice.utils.errors.NotFoundException;

/**
 * The `ClientController` class in Java defines REST endpoints for managing client data and handling
 * requests with appropriate responses.
 */
@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController {
	@Autowired
	private ClientService service;
	
	/**
	 * This Java function retrieves all clients and returns a response with the list of clients or an
	 * error response if an exception occurs.
	 * 
	 * @return The `getAll()` method is returning a `ResponseEntity` object. If the list of clients is
	 * empty, it returns a response with no content. If the list is not empty, it returns a response with
	 * the list of clients. If an exception occurs during the execution, it returns a response handled by
	 * the `handleException` method in the `ErrorHandlerResponse` class.
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
			return ErrorHandlerResponse.handleException(e);
		}
	}
	
	/**
	 * This Java function retrieves a client by ID and returns a response entity with the client data or
	 * an error message if the client is not found.
	 * 
	 * @param id The `id` parameter in the `getById` method is a path variable of type `long`. It is used
	 * to retrieve a specific client by their unique identifier.
	 * @return A ResponseEntity containing a ResponseBody object is being returned.
	 */
	@GetMapping(value = "/{id}")
	public ResponseEntity<ResponseBody> getById(@PathVariable long id){
		try {
			Optional<Client> optionalClient = service.getClientById(id);
			if(optionalClient.isEmpty()) {
				throw new NotFoundException("Client not Found");
			}
			Client client = optionalClient.get();
			ArrayList<Client> data = new ArrayList<Client>();
			data.add(client);
			return Response.ok(data);
		}catch(Exception e) {
			return ErrorHandlerResponse.handleException(e);
		}
	}
	
	/**
	 * This Java function handles a POST request to create a new client and returns a response entity with
	 * a success or error message.
	 * 
	 * @param client The `client` parameter in the `createClient` method is of type `Client`, which is a
	 * class representing a client entity. It is annotated with `@RequestBody`, indicating that the method
	 * expects the client information to be passed in the request body when making a POST request to the
	 * "/signup"
	 * @return The method is returning a ResponseEntity object with a ResponseBody containing the result
	 * of the signup operation. If the signup operation is successful, it returns a Response with a null
	 * body and an OK status. If an exception occurs during the signup operation, it returns a Response
	 * with the error handled by the ErrorHandlerResponse class.
	 */
	@PostMapping(value = "/signup")
	public ResponseEntity<ResponseBody> createClient(@RequestBody Client client){
		try{
			service.create(client);
			return Response.ok(null);

		}catch(Exception e) {
			return ErrorHandlerResponse.handleException(e);
		}
	}
	

	/**
	 * This Java function uses a PATCH request to deactivate an client by its ID and handles any
	 * exceptions that occur.
	 * 
	 * @param id The `id` parameter in the `patchStatus` method is a path variable of type `long`. It is
	 * used to identify the specific resource that needs to be deactivated.
	 * @return The method `patchStatus` is returning a `ResponseEntity` object with a generic type of
	 * `ResponseBody`. The response entity is returned based on the outcome of the `patchStatusById`
	 * method call in the `service` class. If the method call is successful, it returns a response with a
	 * `200 OK` status and a `null` body. If an exception occurs during the process,
	 */
	@PatchMapping(value = "/{id}/desactivate")
	public ResponseEntity<ResponseBody> patchStatus(@PathVariable long id){
		try {
			service.patchStatusById(id);
			return Response.ok(null);
		}catch(Exception e) {
			return ErrorHandlerResponse.handleException(e);
		}
	}
}
