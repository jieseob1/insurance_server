package com.insurance.document_processing_service.service;

import java.io.InputStream;
import java.time.LocalDateTime;

import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.sax.BodyContentHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.tika.parser.pdf.PDFParser;

import com.insurance.document_processing_service.entity.InsuranceDocument;
import com.insurance.document_processing_service.repository.InsuranceDocumentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InsuranceDocumentProcessingService {
  private final InsuranceDocumentRepository insuranceDocumentRepository;

  public void processDocument(MultipartFile file) {
    BodyContentHandler handler = new BodyContentHandler(-1);
      Metadata metadata = new Metadata();
        ParseContext context = new ParseContext();
        PDFParser parser = new PDFParser();

        try (InputStream stream = file.getInputStream()) {
            parser.parse(stream, handler, metadata, context);
            
            InsuranceDocument document = new InsuranceDocument();
            document.setFileName(file.getOriginalFilename());
            document.setContent(handler.toString());
            document.setDocumentType("PDF");
            document.setProcessedDate(LocalDateTime.now());
            document.setStatus("PROCESSED");

            // Transactional 처리 필요 없음: 이 메서드는 단일 데이터베이스 작업을 수행하며, 
            // MongoDB는 기본적으로 원자성을 보장하므로 별도의 트랜잭션 처리가 필요하지 않습니다.
            insuranceDocumentRepository.save(document);
    
  }
}

/*
 * 멀티파트 요청으로 수신된 업로드된 파일의 표현입니다.
 *  파일 내용은 메모리에 저장되거나 임시로 디스크에 저장됩니다. 
 * 두 경우 모두 사용자는 원하는 경우 파일 콘텐츠를 세션 수준 또는 영구 저장소에 복사할 책임이 있습니다. 
 * 임시 저장소는 요청 처리가 끝나면 지워집니다
 */