package com.certus.msteacher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherDto;
import com.certus.msteacher.service.TeacherService;


@RestController
@RequestMapping("/v3/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@GetMapping
	public ResponseEntity<ResponseDto> readAllTeacher(){
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllTeacher());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDto> readTeacher(@PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.getTeacher(id));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto> createTeacher(@RequestBody TeacherDto teacher){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.createTeacher(teacher));
	}
	
	@PutMapping
	public ResponseEntity<ResponseDto> updateTeacher(@RequestBody TeacherDto teacher){
		return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.updateTeacher(teacher));
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseDto> deleteTeacher(@PathVariable("id") Long id){
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.deleteTeacher(id));
	}
	
	@GetMapping("/all-curso")
	public ResponseEntity<ResponseDto> getAllCursoTeacher(){
		return ResponseEntity.status(HttpStatus.OK).body(teacherService.getAllCursoTeacher());
	}
}
