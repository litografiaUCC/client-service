package com.litografiaartesplanchas.clientservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.litografiaartesplanchas.clientservice.model.TypeDocument;

@Repository
public interface ITypeDocumentRepository extends JpaRepository<TypeDocument, Long>{

}
