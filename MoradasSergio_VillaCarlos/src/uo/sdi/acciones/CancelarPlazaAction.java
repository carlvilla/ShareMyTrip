package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class CancelarPlazaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession sesion = request.getSession();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		Long idUsuario = ((User)(sesion.getAttribute("user"))).getId();
		String implicacion = request.getParameter("implicacion");
		
		Long[] ids = {idUsuario,idViaje};
		
		if(implicacion.equals("PENDIENTE")){
			
			ApplicationDao dao = PersistenceFactory.newApplicationDao();
			dao.delete(ids);
		}
		
		else{
			SeatDao dao = PersistenceFactory.newSeatDao();
			dao.delete(ids);
			
			//Añadir plaza libre al viaje
			TripDao daoViaje = PersistenceFactory.newTripDao();
			Trip viaje = daoViaje.findById(idViaje);
			viaje.setAvailablePax(viaje.getAvailablePax()+1);
			daoViaje.update(viaje);
			
		}
		
			
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
