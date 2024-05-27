package ru.utin.project_egar.models.documents_models;

public class DocumentPPTX extends Document{
    public static final String TYPE = ".pptx";

    @Override
    public String getType() {
        return TYPE;
    }

    public DocumentPPTX(String title, long size) {
        super(title, size);
    }

    public DocumentPPTX() {
    }
}
