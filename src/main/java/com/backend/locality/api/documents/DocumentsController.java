package com.backend.locality.api.documents;

import com.backend.locality.api.locality.LocalityModel;
import com.backend.locality.api.locality.LocalityService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/documents")
public class DocumentsController {
    private final DocumentsService documentsService;

    @RequestMapping(method = RequestMethod.GET)
    public List<DocumentsModel> getAllLocalities() {
        return documentsService.findAllDocuments();
    }

    @RequestMapping(method = RequestMethod.POST)
    public DocumentsModel saveLocality(@RequestBody DocumentsModel document) {
        return documentsService.saveDocument(document);
    }

    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public DocumentsModel getLocalityById(@ModelAttribute Long documentId) {
        return documentsService.findDocumentById(documentId);
    }
}
