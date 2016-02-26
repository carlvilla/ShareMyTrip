package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class CancelarViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		String implicacion = request.getParameter("implicacion");
		
		if(implicacion.compareTo("PROMOTOR")==0){
			TripDao dao = PersistenceFactory.newTripDao();
			Trip trip = dao.findById(idViaje);
			trip.setStatus(TripStatus.CANCELLED);
			dao.update(trip);
			return "EXITO";
		}
		return "FRACASO";
	}

}
