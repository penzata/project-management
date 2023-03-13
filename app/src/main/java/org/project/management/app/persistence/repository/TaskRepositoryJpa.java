package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByAssigneeId(Long id);

    void deleteByProjectId(Long projectId);

    @Query("select t from TaskEntity t where t.completedDate > t.dueDate")
    List<TaskEntity> findAllByCompletedDateAfterDueDate();
}