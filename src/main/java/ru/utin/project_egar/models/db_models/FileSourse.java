package ru.utin.project_egar.models.db_models;

import jakarta.persistence.*;


@Entity
@Table(name = "sourse_documents")
public class FileSourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sourse")
    private byte[] file;

    @OneToOne()
    @JoinColumn(name = "property_document_id", referencedColumnName = "id")
    private PropertyDocument propertyDocument;

    public FileSourse() {}

    public FileSourse(byte[] file) {
        this.file = file;
    }

    public FileSourse(byte[] file, PropertyDocument propertyDocument) {
        this.file = file;
        this.propertyDocument = propertyDocument;
    }

    public PropertyDocument getPropertyDocument() {
        return propertyDocument;
    }

    public void setPropertyDocument(PropertyDocument propertyDocument) {
        this.propertyDocument = propertyDocument;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
