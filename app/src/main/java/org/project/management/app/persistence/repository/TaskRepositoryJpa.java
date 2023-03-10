package org.project.management.app.persistence.repository;

import org.project.management.app.persistence.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepositoryJpa extends JpaRepository<TaskEntity, Long> {

    @Query("""
            SELECT assignee_id, COUNT(assignee_id) as frequency
            FROM tasks
            GROUP BY assignee_id
            ORDER BY COUNT(assignee_id) DESC
            NULLS LAST
            LIMIT 5
            """)
    List<Long> getDistinctTopByAssigneeId();
}