package com.certus.msteacher.service;

import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherCursoDto;


public interface TeacherCursoService {

	public ResponseDto getAllTeacherCurso();
	public ResponseDto getTeacherCurso(Long id);
	public ResponseDto createTeacherCurso(TeacherCursoDto curso);
	
	
}
