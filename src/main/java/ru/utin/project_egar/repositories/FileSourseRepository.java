package ru.utin.project_egar.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.utin.project_egar.models.db_models.FileSourse;

@Repository
public interface FileSourseRepository extends JpaRepository<FileSourse, Integer> {
}
