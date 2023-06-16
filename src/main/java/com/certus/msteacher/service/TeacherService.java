package com.certus.msteacher.service;

import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherDto;

public interface TeacherService {

	public ResponseDto getAllTeacher();
	public ResponseDto getTeacher(Long id);
	public ResponseDto createTeacher(TeacherDto teacher);
	public ResponseDto updateTeacher(TeacherDto teacher);
	public ResponseDto deleteTeacher(Long id);
	
	public ResponseDto getAllCursoTeacher();
}
