package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Prueba
public interface Accion {
	
	public String execute(HttpServletRequest request, HttpServletResponse response);

}
