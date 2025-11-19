package com.integrador.task.crud;

import com.integrador.task.entity.data.TaskData;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskCrud extends CrudRepository<TaskData, Long> {

    boolean existsByTitle(String title);

    @Query("SELECT t FROM TaskData t WHERE t.project.id = :projectId " +
            "AND (:minEstimate IS NULL OR t.estimateHours >= :minEstimate) " +
            "AND (:assignee IS NULL OR t.assignee = :assignee)")
    List<TaskData> searchTasks(@Param("projectId") Long projectId,
                               @Param("minEstimate") Integer minEstimate,
                               @Param("assignee") String assignee);

    int countByProjectId(Long projectId);

}