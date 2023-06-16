package com.certus.msteacher.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.certus.msteacher.entity.TeacherCursoEntity;


public interface TeacherCursoRepository extends JpaRepository<TeacherCursoEntity, Long>{
	TeacherCursoEntity findByIdProfesor(Long id);
}
