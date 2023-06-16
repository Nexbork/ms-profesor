package com.certus.msteacher.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Table(name = "profesor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_profesor")
	private Long idProfesor;
	@Column(name = "nombre")
	private String nombreProfesor;
	@Column(name = "apellidos")
	private String apellidoProfesor;
	@Column(name = "sexo")
	private String sexo;
	@Column(name = "estado")
	private Boolean estado;
	
	@JoinColumn(name = "id_profesor", referencedColumnName = "cod_profesor", insertable = false, updatable = false)
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
	private TeacherCursoEntity teacherCursoEntity;
	
}
