package com.certus.msteacher.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certus.msteacher.client.CursoClient;
import com.certus.msteacher.dto.CursoResponseDto;
import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherDto;
import com.certus.msteacher.entity.TeacherEntity;
import com.certus.msteacher.repository.TeacherRepository;
import com.certus.msteacher.service.TeacherService;
import com.certus.msteacher.util.Constante;
import com.certus.msteacher.util.Util;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeacherServiceImpl implements TeacherService{

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CursoClient cursoClient;
	
	@Override
	public ResponseDto getAllTeacher() {
		try {
			List<TeacherEntity> listTeacherEntity = teacherRepository.findAll();
			if(listTeacherEntity.isEmpty()) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, listTeacherEntity);
			}
			List<TeacherDto> list = new ArrayList<TeacherDto>();
			for (TeacherEntity teacherEntity : listTeacherEntity) {
				list.add(TeacherDto.builder()
						.idProfesor(teacherEntity.getIdProfesor())
						.nombreProfesor(teacherEntity.getNombreProfesor())
						.apellidoProfesor(teacherEntity.getApellidoProfesor())
						.sexo(teacherEntity.getSexo())
						.estado(teacherEntity.getEstado())
						.build());
			}
			return Util.getResponse(true, Constante.CODE_SUCCES, list);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.CODE_FAILED, null);
		}
	}

	@Override
	public ResponseDto getTeacher(Long id) {
		try {
			TeacherEntity teacherEntity = teacherRepository.findById(id).orElse(null);
			if (null == teacherEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			
			TeacherDto teacherDto = TeacherDto.builder()
					.idProfesor(teacherEntity.getIdProfesor())
					.nombreProfesor(teacherEntity.getNombreProfesor())
					.apellidoProfesor(teacherEntity.getApellidoProfesor())
					.sexo(teacherEntity.getSexo())
					.estado(teacherEntity.getEstado())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, teacherDto);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
	}

	@Override
	public ResponseDto createTeacher(TeacherDto teacher) {
		try {
			TeacherEntity teacherEntity = TeacherEntity.builder()
					.nombreProfesor(teacher.getNombreProfesor())
					.apellidoProfesor(teacher.getApellidoProfesor())
					.sexo(teacher.getSexo())
					.estado(true)
					.build();
			teacherRepository.save(teacherEntity);
			teacher.setIdProfesor(teacherEntity.getIdProfesor());
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, teacher);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto updateTeacher(TeacherDto teacher) {
		try {
			TeacherEntity teacherEntity = teacherRepository.findById(teacher.getIdProfesor()).orElse(null);
			if (null == teacherEntity) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			teacherEntity.setNombreProfesor(teacher.getNombreProfesor());
			teacherEntity.setApellidoProfesor(teacher.getApellidoProfesor());
			teacherEntity.setSexo(teacher.getSexo());
			teacherEntity.setEstado(teacher.getEstado());
			teacherRepository.save(teacherEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, teacher);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}	
	}

	@Override
	public ResponseDto deleteTeacher(Long id) {
		try {
			TeacherEntity teacherEntity = teacherRepository.findById(id).orElse(null);
			teacherEntity.setEstado(false);
			teacherRepository.save(teacherEntity);
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			log.error(Constante.OPERATION_FAILED, e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto getAllCursoTeacher() {
		
		try {
			List<TeacherEntity> listTeacherEntity = teacherRepository.findAll();
			ResponseDto responseDto = cursoClient.readAllCurso();
			
			List<TeacherDto> listTeacher = new ArrayList<TeacherDto>();
			for (TeacherEntity teacherEntity : listTeacherEntity) {
				TeacherDto.builder()
				.idProfesor(teacherEntity.getIdProfesor())
				.nombreProfesor(teacherEntity.getNombreProfesor())
				.apellidoProfesor(teacherEntity.getApellidoProfesor())
				.sexo(teacherEntity.getSexo())
				.estado(teacherEntity.getEstado())
				.build();

			}
			CursoResponseDto cursoDto = CursoResponseDto.builder()
					.teacher(listTeacher)
					.curso(responseDto.getData())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, cursoDto);
		} catch (RetryableException ex) {
			log.error("Exception:"+ Constante.OPERATION_FAILED+" "+ex);
			return Util.getResponse(false, Constante.NO_SERVICE_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception:"+ Constante.OPERATION_FAILED+" "+e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}
	
}
