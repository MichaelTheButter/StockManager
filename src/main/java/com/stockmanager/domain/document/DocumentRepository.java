package com.stockmanager.domain.document;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{
    Optional<Document> findTopByDocumentTypeEqualsAndCreateDateAfterOrderByCreateDate(DocumentType documentType, LocalDate localDate);

    default Optional<Document> findLatestNumber(DocumentType documentType, LocalDate localDate) {
        return findTopByDocumentTypeEqualsAndCreateDateAfterOrderByCreateDate(documentType, localDate);
    }

}
