package ru.utin.project_egar.models.documents_models;

public abstract class Document implements DocumentsArray {
    private String title;
    private long size;

    abstract public String getType();

    public Document(String title, long size) {
        this.title = title;
        this.size = size;
    }

    public Document() {
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
