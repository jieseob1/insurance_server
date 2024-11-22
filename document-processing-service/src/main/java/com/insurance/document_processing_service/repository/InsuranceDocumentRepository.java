package com.insurance.document_processing_service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.insurance.document_processing_service.entity.InsuranceDocument;

public interface InsuranceDocumentRepository extends MongoRepository<InsuranceDocument, String>{
  
}
