package com.certus.msteacher.util;

import com.certus.msteacher.dto.ResponseDto;

public class Util {

	public static ResponseDto getResponse(boolean success, String mensaje,Object data) {
		ResponseDto response = new ResponseDto();
		String cod = (!success) ? Constante.CODE_FAILED : Constante.CODE_SUCCES;
		response.setCodigo(cod);
		response.setMensaje(mensaje);
		response.setData(data);
		return response;
	}
}
