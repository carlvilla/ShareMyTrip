package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class MostrarDatosModificarViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		TripDao dao = PersistenceFactory.newTripDao();
		Trip viaje = dao.findById(idViaje);
		
		if(viaje.getStatus().equals(TripStatus.CANCELLED))
				return "FRACASO";
		request.setAttribute("viajeModificar", viaje);
		
		return "EXITO";
	}

}
