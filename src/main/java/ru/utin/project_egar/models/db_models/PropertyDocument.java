package ru.utin.project_egar.models.db_models;

import jakarta.persistence.*;
import ru.utin.project_egar.models.documents_models.Document;

import java.util.Date;

@Entity
@Table(name = "property_documents")
public class PropertyDocument {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "type")
    private String type;
    @Column(name = "size")
    private long size;

    @Column(name = "date_of_creation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreation;

    @Column(name = "date_of_change")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateChange;

    @OneToOne(mappedBy = "propertyDocument", fetch = FetchType.LAZY)
    private FileSourse fileSourse;


    public PropertyDocument() {
    }

    public PropertyDocument(Document document) {
        this.title = document.getTitle();
        this.type = document.getType();
        this.size = document.getSize();
        this.dateCreation = new Date();
        this.dateChange = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        setDateCreation(new Date());
        this.title = title;
    }

    public long getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Date getDateChange() {
        return dateChange;
    }

    public void setDateChange(Date dateChange) {
        this.dateChange = dateChange;
    }

    public FileSourse getFileSourse() {
        return fileSourse;
    }

    public void setFileSourse(FileSourse fileSourse) {
        this.fileSourse = fileSourse;
    }


    @Override
    public String toString() {
        return "PropertyDocument{" +
                "dateChange=" + dateChange +
                ", dateCreation=" + dateCreation +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}