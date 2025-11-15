package com.integrador.task.Crud;

import com.integrador.task.entity.TaskData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCrud extends CrudRepository<TaskData, Integer> {

    boolean existsByTitle(String title);
    boolean existsByDescription(String description);
}
