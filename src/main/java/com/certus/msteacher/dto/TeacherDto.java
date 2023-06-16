package com.certus.msteacher.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDto {
	private Long idProfesor;
	private String nombreProfesor;
	private String apellidoProfesor;
	private String sexo;
	private Boolean estado;
	
	
}
