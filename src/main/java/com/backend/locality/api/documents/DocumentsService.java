package com.backend.locality.api.documents;

import com.backend.locality.api.documents.interfaces.IDocuments;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DocumentsService implements IDocuments {
    private final DocumentsRepository documentsRepository;

    @Override
    public List<DocumentsModel> findAllDocuments() {
        return documentsRepository.findAllDocuments();
    }

    @Override
    public DocumentsModel findDocumentById(int documentId) {
        return documentsRepository.findDocumentById(documentId);
    }

    @Override
    public DocumentsModel saveDocument(DocumentsModel document) {
        return documentsRepository.saveDocument(document);
    }
}
