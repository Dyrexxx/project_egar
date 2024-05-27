package ru.utin.project_egar.models.documents_models;


public interface DocumentsArray {
    Document[] DOCUMENTS_ARRAY = new Document[]{
            new DocumentEXEL(),
            new DocumentPDF(),
            new DocumentPPTX(),
            new DocumentTXT()
    };
}
