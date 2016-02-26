package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class MostarDatosViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		TripDao dao = PersistenceFactory.newTripDao();
		Trip viaje = dao.findById(idViaje);
		request.setAttribute("viajeModificar", viaje);
		
		return "EXITO";
	}

}
