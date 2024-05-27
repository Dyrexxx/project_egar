package ru.utin.project_egar.models.sort_models;


import ru.utin.project_egar.models.db_models.PropertyDocument;
import ru.utin.project_egar.repositories.PropertyDocumentRepository;

import java.util.List;

public class SortModel {
    private String sortType;
    private String sortDateChange;
    private String find;


    public SortModel() {
    }

    public String getSortDateChange() {
        return sortDateChange;
    }


    public String getSortType() {
        return sortType;
    }


    public void setSortType(String sortType) {
        this.sortType = sortType;
    }

    public void setSortDateChange(String sortDateChange) {
        this.sortDateChange = sortDateChange;
    }


    public String getFind() {
        return find;
    }

    public void setFind(String find) {
        this.find = find;
    }

    public String getUrl() {
        return  "sortType=" + getSortType() +
                "&sortDateChange=" + getSortDateChange();
    }
}
