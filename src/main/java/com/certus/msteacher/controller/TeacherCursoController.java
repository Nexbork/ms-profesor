package com.certus.msteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherCursoDto;
import com.certus.msteacher.service.TeacherCursoService;

@RestController
@RequestMapping("/v3/profesor-curso")
public class TeacherCursoController {
	
	@Autowired
	private TeacherCursoService teacherCursoService;
	
	@GetMapping
	public ResponseEntity<ResponseDto> getAllTeacherCurso(){
		return ResponseEntity.status(HttpStatus.OK).body(teacherCursoService.getAllTeacherCurso());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> getTeacherCurso(@PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(teacherCursoService.getTeacherCurso(id));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto> createTeacherCurso(@RequestBody TeacherCursoDto curso){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherCursoService.createTeacherCurso(curso));
	}
}
