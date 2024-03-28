package com.litografiaartesplanchas.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litografiaartesplanchas.clientservice.model.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, Long>{

}
