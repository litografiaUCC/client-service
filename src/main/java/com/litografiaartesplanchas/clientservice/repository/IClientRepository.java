package com.litografiaartesplanchas.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litografiaartesplanchas.clientservice.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long>{
	/**
	 * The function existsByEmail checks if a given email exists in the system.
	 * 
	 * @param email The parameter `email` is a string that represents an email address.
	 * @return A boolean value is being returned, indicating whether a record with the specified email
	 * exists in the system.
	 */
	boolean existsByEmail(String email);
	/**
	 * This function checks if a document with a specific number exists.
	 * 
	 * @param numberDocument The parameter `numberDocument` is a string representing a document number.
	 * This method `existsByNumberDocument` is likely used to check if a document with the given number
	 * exists in a database or some other data source. It should return a boolean value indicating whether
	 * a document with the specified number exists or
	 * @return A boolean value indicating whether a document with the specified number exists.
	 */
	boolean existsByNumberDocument(String numberDocument);
}
