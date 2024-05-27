package ru.utin.project_egar.models.documents_models;

public class DocumentTXT extends Document{
    public static final String TYPE = ".txt";

    @Override
    public String getType() {
        return TYPE;
    }

    public DocumentTXT(String title, long size) {
        super(title, size);
    }

    public DocumentTXT() {
    }
}
