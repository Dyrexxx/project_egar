package ru.utin.project_egar.models.documents_models;

public class DocumentEXEL extends Document{
    public static final String TYPE = ".xlsx";

    @Override
    public String getType() {
        return TYPE;
    }

    public DocumentEXEL(String title, long size) {
        super(title, size);
    }

    public DocumentEXEL() {
    }
}
