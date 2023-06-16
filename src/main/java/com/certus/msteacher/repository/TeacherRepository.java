package com.certus.msteacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certus.msteacher.entity.TeacherEntity;

public interface TeacherRepository extends JpaRepository<TeacherEntity, Long>{

}
