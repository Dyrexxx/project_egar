package ru.utin.project_egar.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.utin.project_egar.models.documents_models.Document;
import ru.utin.project_egar.models.db_models.FileSourse;
import ru.utin.project_egar.models.db_models.PropertyDocument;
import ru.utin.project_egar.models.sort_models.SortModel;
import ru.utin.project_egar.utils.CreatePagesUtil;
import ru.utin.project_egar.repositories.FileSourseRepository;
import ru.utin.project_egar.repositories.PropertyDocumentRepository;
import ru.utin.project_egar.utils.CreateDocumentsUtil;

import java.io.*;
import java.util.List;
import java.util.Optional;


@Service
@EnableTransactionManagement
@Transactional(readOnly = true)
public class PropertyDocumentService {
    private final PropertyDocumentRepository propertyDocumentRepository;
    private final FileSourseRepository fileSourseRepository;

    @Autowired
    public PropertyDocumentService(PropertyDocumentRepository propertyDocumentRepository, FileSourseRepository fileSourseRepository) {
        this.propertyDocumentRepository = propertyDocumentRepository;
        this.fileSourseRepository = fileSourseRepository;
    }

    //todo костыль рефакторинг
    public List<PropertyDocument> index(SortModel sortModel, Integer page, Integer list) {
        Sort sort = null;
        if (sortModel.getSortDateChange() != null) {
            if (sortModel.getSortDateChange().equals("rise")) {
                sort = Sort.by(Sort.Direction.ASC, "dateCreation");
            } else if (sortModel.getSortDateChange().equals("decrease")) {
                sort = Sort.by(Sort.Direction.DESC, "dateCreation");
            }
        }

        if (sortModel.getSortType() == null || sortModel.getSortType().isEmpty()) {
            if (sortModel.getFind() == null) {
                if (sort == null) {
                    return propertyDocumentRepository.findAll(PageRequest.of(page, list)).getContent();
                } else {
                    return propertyDocumentRepository.findAll(PageRequest.of(page, list, sort)).getContent();
                }
            } else {
                if (sort == null) {
                    return propertyDocumentRepository.findByTitleStartingWithIgnoreCase(sortModel.getFind(),
                            PageRequest.of(page, list)).getContent();
                } else {
                    return propertyDocumentRepository.findByTitleStartingWithIgnoreCase(sortModel.getFind(),
                            PageRequest.of(page, list, sort)).getContent();
                }
            }
        }
        else {
            if(sortModel.getFind()==null) {
                if(sort==null) {
                    return propertyDocumentRepository.findByType(sortModel.getSortType(),
                            PageRequest.of(page, list)).getContent();
                }
                else {
                    return propertyDocumentRepository.findByType(sortModel.getSortType(),
                            PageRequest.of(page, list, sort)).getContent();
                }
            }
            else {
                if(sort==null) {
                    return propertyDocumentRepository.findByTitleStartingWithIgnoreCaseAndType(
                            sortModel.getFind(),
                            sortModel.getSortType(),
                            PageRequest.of(page, list)).getContent();
                }
                else {
                    return propertyDocumentRepository.findByTitleStartingWithIgnoreCaseAndType(
                            sortModel.getFind(),
                            sortModel.getSortType(),
                            PageRequest.of(page, list, sort)).getContent();
                }
            }
        }
    }


    public PropertyDocument get(int id) {
        return propertyDocumentRepository.getReferenceById(id);
    }

    //Проблема производительности поиска существующих документов
    @Transactional
    public void save(MultipartFile file, String fullTitle) {
        try {
            PropertyDocument propertyDocument = createPropertyDocument(file, fullTitle);
            if (propertyDocument != null) {
                propertyDocumentRepository.save(propertyDocument);
                FileSourse fileSourse = new FileSourse(file.getBytes(), propertyDocument);
                fileSourseRepository.save(fileSourse);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Transactional
    public void delete(int id) {
        propertyDocumentRepository.deleteById(id);
    }


    @Transactional
    public void update(int id, PropertyDocument propertyDocument) {
        Optional<PropertyDocument> document = propertyDocumentRepository.findById(id);
        document.get().setTitle(propertyDocument.getTitle());
        propertyDocumentRepository.save(document.get());
    }


    public int[] getPages(String finded) {
        return finded == null ? getIndexPages() : getSearchPages(finded);
    }

    private int[] getIndexPages() {
        return CreatePagesUtil.getListPages(propertyDocumentRepository.findAll().size());
    }

    private int[] getSearchPages(String title) {
        return CreatePagesUtil.getListPages(propertyDocumentRepository.findByTitleStartingWithIgnoreCase(title).size());
    }

    //todo
    public List<PropertyDocument> sortingDocuments(SortModel sortModel, Integer page, Integer list) {
        switch (sortModel.getFind()) {
            case "":

        }
        // return propertyDocumentRepository.findByType(type, PageRequest.of(page, list)).getContent();
        return null;
    }

    //todo Рефакторинг
    private PropertyDocument createPropertyDocument(MultipartFile file, String fullTitle) throws IOException {
        Document document = CreateDocumentsUtil.createDocument(fullTitle, file.getSize());

        List<PropertyDocument> docsExistsList = propertyDocumentRepository
                .findByTitleAndType(document.getTitle(), document.getType());

        if (docsExistsList.isEmpty()) {
            return new PropertyDocument(document);
        }
        return null;
    }
}