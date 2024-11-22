package com.insurance.document_processing_service.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data // TODO. 추후에 분리하기 => getter, setter 따로 생성
//모든 필드에 대한 getter, 유용한 toString 메서드 및 hashCode를 생성
@Document(collection = "documents")
public class InsuranceDocument {
  @Id
    private String id;
    private String fileName;
    private String content;
    private String documentType;
    private LocalDateTime processedDate;
    private String status;
}
