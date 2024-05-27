package ru.utin.project_egar.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.utin.project_egar.models.db_models.PropertyDocument;

import java.util.List;

@Repository
public interface PropertyDocumentRepository extends JpaRepository<PropertyDocument, Integer> {
    List<PropertyDocument> findByTitleAndType(String title, String type);
    Page<PropertyDocument> findAll(Pageable page);
    Page<PropertyDocument> findByTitleStartingWithIgnoreCase(String title, Pageable page);
    Page<PropertyDocument> findByTitleStartingWithIgnoreCaseAndType(String title, String type, Pageable page);
    List<PropertyDocument> findByTitleStartingWithIgnoreCase(String title);
    Page<PropertyDocument> findByType(String type, Pageable page);
}
