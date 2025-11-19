package com.integrador.project.crud;

import com.integrador.project.entity.data.ProjectData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectCrud extends CrudRepository<ProjectData, Long> {
    boolean existsByName(String name);

}
