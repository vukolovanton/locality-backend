package com.backend.locality.api.documents.interfaces;

import com.backend.locality.api.documents.DocumentsModel;

import java.util.List;

public interface IDocuments {
    List<DocumentsModel> findAllDocuments();
    DocumentsModel findDocumentById(Long documentId);
    DocumentsModel saveDocument(DocumentsModel document);
}
