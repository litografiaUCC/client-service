package com.litografiaartesplanchas.clientservice.controller;

import java.util.List;
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

@RestController
@RequestMapping(value = "/api/v1/clients")
public class ClientController {
	@Autowired
	private ClientService service;
	
	@GetMapping(value = "/")
	public List<Client> getAll(){
		return service.getAll();
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Client> getById(@PathVariable long id){
		Optional<Client> optionalClient = service.getClientById(id);
		if(optionalClient.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		Client client = optionalClient.get();
		return new ResponseEntity<>(client, HttpStatus.OK);
	}
}
