package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrarViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String calleSalida = request.getParameter("calleSalida");
		String ciudadSalida = request.getParameter("ciudadSalida");
		String provinciaSalida = request.getParameter("provinciaSalida");
		String paisSalida = request.getParameter("paisSalida");
		String postalSalida = request.getParameter("postalSalida");
		String longitudSalida = request.getParameter("longitudSalida");
		String latitudSalida = request.getParameter("latitudSalida");
		
		String calleDestino = request.getParameter("calleDestino");
		String ciudadDestino = request.getParameter("ciudadDestino");
		String provinciaDestino = request.getParameter("provinciaDestino");
		String paisDestino = request.getParameter("paisDestino");
		String postalDestino= request.getParameter("postalDestino");
		String longitudDestino = request.getParameter("longitudDestino");
		String latitudDestino = request.getParameter("latitudDestino");
		
		String fechaSalida = request.getParameter("fechaSalida");
		String horaSalida = request.getParameter("horaSalida");
		String fechaLimite = request.getParameter("fechaLimite");
		String costeViaje = request.getParameter("costeViaje");
		String descripcionViaje= request.getParameter("descripcionViaje");
		String nMaxPlazas = request.getParameter("numeroMaxPlazas");
		String nDispoPlazas = request.getParameter("numeroDispPlazas");
		
		return "EXITO";
	}

}
