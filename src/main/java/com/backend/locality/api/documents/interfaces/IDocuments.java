package com.backend.locality.api.documents.interfaces;

import com.backend.locality.api.documents.DocumentsModel;

import java.util.List;

public interface IDocuments {
    List<DocumentsModel> findAllDocuments();
    DocumentsModel findDocumentById(int documentId);
    DocumentsModel saveDocument(DocumentsModel document);
}
