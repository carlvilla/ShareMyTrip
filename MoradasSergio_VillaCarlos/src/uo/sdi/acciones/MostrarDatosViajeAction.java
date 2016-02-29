package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class MostrarDatosViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Trip viaje;		
		
		try {
			
			viaje=PersistenceFactory.newTripDao().findById(Long.valueOf(request.getParameter("id")));
			request.setAttribute("viaje", viaje);
			
			Log.debug("Obtenido viaje con id [%d]", viaje.getId());
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo los datos del viaje");
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
