package com.certus.msteacher.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.certus.msteacher.client.CursoClient;
import com.certus.msteacher.dto.CursoDto;
import com.certus.msteacher.dto.ResponseDto;
import com.certus.msteacher.dto.TeacherCursoDto;
import com.certus.msteacher.entity.TeacherCursoEntity;
import com.certus.msteacher.entity.TeacherEntity;
import com.certus.msteacher.repository.TeacherCursoRepository;
import com.certus.msteacher.repository.TeacherRepository;
import com.certus.msteacher.service.TeacherCursoService;
import com.certus.msteacher.util.Constante;
import com.certus.msteacher.util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;

import feign.RetryableException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TeacherCursoServiceImpl implements TeacherCursoService{
	
	@Autowired
	private TeacherCursoRepository teacherCursoRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private CursoClient cursoClient;

	@Override
	public ResponseDto getAllTeacherCurso() {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			List<TeacherCursoEntity> listTeacherCursoEntity = teacherCursoRepository.findAll();
			List<TeacherCursoDto> listTeacherCursoDto = new ArrayList<TeacherCursoDto>();
			
			for (int i = 0; i < listTeacherCursoEntity.size(); i ++) {
				TeacherEntity teacherEntity = teacherRepository.findById(listTeacherCursoEntity.get(i).getIdProfesor()).orElse(null);
				
				ResponseDto responseDto = cursoClient.readCurso(listTeacherCursoEntity.get(i).getIdCurso());
				
				CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
				
				listTeacherCursoDto.add(TeacherCursoDto.builder()
						.idProfesor(teacherEntity.getIdProfesor())
						.nombreProfesor(teacherEntity.getNombreProfesor() + " " + teacherEntity.getApellidoProfesor())
						.estadoProfesor(teacherEntity.getEstado())
						.idCurso(cursoDto.getId())
						.nombreCurso(cursoDto.getDescripcion())
						.build());
			}
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, listTeacherCursoEntity);
		} catch (RetryableException ex) {
			log.error("Exception: "+ Constante.OPERATION_FAILED+" "+ex);
			return Util.getResponse(false, Constante.NO_SERVICE_AVAILABLE, null);
		} catch (Exception e) {
			log.error("Exception:"+ Constante.OPERATION_FAILED +" "+ e);
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

	@Override
	public ResponseDto getTeacherCurso(Long id) {
		ObjectMapper mapper = new ObjectMapper();
		
		try {
			TeacherCursoEntity teacherCursoEntity = teacherCursoRepository.findByIdProfesor(id);
			if (teacherCursoEntity == null) {
				return Util.getResponse(true, Constante.NO_RECORDS_FOUND, null);
			}
			TeacherEntity teacherEntity = teacherRepository.findById(teacherCursoEntity.getIdProfesor()).orElse(null);
			ResponseDto responseDto = cursoClient.readCurso(teacherCursoEntity.getIdCurso());
			CursoDto cursoDto = mapper.convertValue(responseDto.getData(), CursoDto.class);
			
			TeacherCursoDto teacherCursoDto = TeacherCursoDto.builder()
					.idProfesor(teacherEntity.getIdProfesor())
					.nombreProfesor(teacherEntity.getNombreProfesor() + " " + teacherEntity.getApellidoProfesor())
					.estadoProfesor(teacherEntity.getEstado())
					.idCurso(cursoDto.getId())
					.nombreCurso(cursoDto.getDescripcion())
					.build();
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, teacherCursoDto);
		} catch (RetryableException ex) {
			log.error("Exception:"+Constante.OPERATION_FAILED+" "+ex);
			return Util.getResponse(false, Constante.OPERATION_FAILED,null);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED,null);
		}
		
	}

	@Override
	public ResponseDto createTeacherCurso(TeacherCursoDto curso) {
		try {
			TeacherCursoEntity teacherCursoEntity = TeacherCursoEntity.builder()
					.idProfesor(curso.getIdProfesor())
					.idCurso(curso.getIdCurso())
					.build();
			teacherCursoRepository.save(teacherCursoEntity);
			
			return Util.getResponse(true, Constante.OPERATION_SUCCESS, null);
		} catch (Exception e) {
			return Util.getResponse(false, Constante.OPERATION_FAILED, null);
		}
		
	}

}
