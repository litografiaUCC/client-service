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
	 * This Java function retrieves all clients and returns a response based on whether the list is empty
	 * or not.
	 * 
	 * @return The code snippet provided is a Java method annotated with `@GetMapping` for handling HTTP
	 * GET requests to the root endpoint ("/").
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
	 * This Java function retrieves a client by ID and returns a response with the client data or an error
	 * message.
	 * 
	 * @param id The `id` parameter in the `getById` method is a path variable of type `long`. It is used
	 * to retrieve a specific client by their unique identifier from the service layer.
	 * @return The `getById` method in the code snippet is returning a `ResponseEntity` object with a
	 * generic type of `ResponseBody`. The response can be either a successful response with a list
	 * containing the client data if the client with the specified ID is found, or a not found response if
	 * the client is not found, or a bad request response if an exception occurs during the process.
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
	 * This Java function handles PATCH requests to deactivate an account by its ID and returns
	 * appropriate responses based on the status returned by the service.
	 * 
	 * @param id The code snippet you provided is a PATCH mapping method in a Spring application that
	 * deactivates an account based on the provided ID. The method calls a service to update the status of
	 * the account and returns a response based on the status code returned by the service.
	 * @return The method is returning a ResponseEntity object with a ResponseBody containing the result
	 * of the patchStatus operation. The response can be one of the following based on the status returned
	 * by the service:
	 * - 200: OK response with a null body
	 * - 409: Conflict response with a message "Account already deactivated"
	 * - 404: Not Found response
	 * If an unexpected status is returned by the service,
	 */
	@PatchMapping(value = "/{id}/desactivate")
	public ResponseEntity<ResponseBody> patchStatus(@PathVariable long id){
		try {
			int status = service.patchStatusById(id);
			switch(status) {
				case 200:
					return Response.ok(null);
				case 409:
					return Response.conflict("Account already deactivated");
				case 404:
					return Response.notFound();
				default:
					throw new Exception("Unexpected status returned by service");
			}
		}catch(Exception e) {
			System.out.println(e);
			return Response.badRequest();
		}
	}
}
