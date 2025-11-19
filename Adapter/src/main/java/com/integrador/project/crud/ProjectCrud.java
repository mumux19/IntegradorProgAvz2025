package com.integrador.project.crud;

import com.integrador.project.entity.data.ProjectData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface ProjectCrud extends JpaRepository<ProjectData, Long> {
    boolean existsByName(String name);
}
