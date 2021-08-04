package com.backend.locality.api.documents;

import com.backend.locality.api.documents.interfaces.IDocuments;
import com.backend.locality.api.locality.LocalityModel;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@AllArgsConstructor
public class DocumentsRepository implements IDocuments {
    private final EntityManager entityManager;

    @Override
    public List<DocumentsModel> findAllDocuments() {
        Session session = entityManager.unwrap(Session.class);
        TypedQuery<DocumentsModel> findAllDocumentsQuery = session.createQuery(
                "Select d from DocumentsModel d",
                DocumentsModel.class
        );

        return findAllDocumentsQuery.getResultList();
    }

    @Override
    public DocumentsModel findDocumentById(Long documentId) {
        Session session = entityManager.unwrap(Session.class);

        TypedQuery<DocumentsModel> findDocumentByIdQuery = session.createQuery(
                "Select d from DocumentsModel d where d.id = :documentId",
                DocumentsModel.class
        );
        findDocumentByIdQuery.setParameter("documentId", documentId);

        return findDocumentByIdQuery.getSingleResult();
    }

    @Override
    public DocumentsModel saveDocument(DocumentsModel document) {
        Session session = entityManager.unwrap(Session.class);
        session.save(document);

        // Or is it going to have id?
        return document;
    }
}
