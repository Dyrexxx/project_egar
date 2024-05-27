package ru.utin.project_egar.models.documents_models;

public class DocumentPDF extends Document {
    public static final String TYPE = ".pdf";

    @Override
    public String getType() {
        return TYPE;
    }

    public DocumentPDF(String title, long size) {
        super(title, size);
    }

    public DocumentPDF() {
    }
}
