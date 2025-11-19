package com.integrador.task.Crud;

import com.integrador.task.entity.TaskData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCrud extends CrudRepository<TaskData,Long> {
    boolean existsByTitle(String title);
    boolean deleteTask(Long id);
    boolean saveTask(TaskData taskData);

}
