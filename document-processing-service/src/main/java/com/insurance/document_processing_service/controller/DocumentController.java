package com.insurance.document_processing_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/documents")
@RequiredArgsConstructor
public class DocumentController {
    private final InsuranceDocumentProcessingService documentProcessingService;

    @PostMapping("/process")
    public ResponseEntity<Void> processDocument(@RequestParam("file") MultipartFile file) {
        try {
            documentProcessingService.processDocument(file);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
} 