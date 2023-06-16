package com.certus.msteacher.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "curso_profesor")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherCursoEntity {
	
	@Id
	@Column(name = "cod_profesor")
	private Long idProfesor;
	@Column(name = "cod_curso")
	private Long idCurso;
	

}
