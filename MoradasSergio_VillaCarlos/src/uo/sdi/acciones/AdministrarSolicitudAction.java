package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class AdministrarSolicitudAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		ApplicationDao daoApp = PersistenceFactory.newApplicationDao();
		SeatDao daoSeat = PersistenceFactory.newSeatDao();
		
		Long idUsuario = Long.valueOf(request.getParameter("idUsuario"));
		Long idViaje = Long.valueOf(request.getParameter("idviaje"));
		
		Long[] ids = {idUsuario,idViaje};
		daoApp.delete(ids);
		
		String decision = request.getParameter("decision");
		
		TripDao daoViaje = PersistenceFactory.newTripDao();
		Trip viaje = daoViaje.findById(idViaje);
		
		Seat seat = new Seat();
		seat.setUserId(idUsuario);
		seat.setTripId(idViaje);
		
		if(decision.equals("aceptada")){
		
		//Quitar plaza al viaje
		
		viaje.setAvailablePax(viaje.getAvailablePax()-1);
		daoViaje.update(viaje);
		
		//Añadir usuario a tabla TSEATS (Usuarios aceptados)
		seat.setStatus(SeatStatus.ACCEPTED);
		
		}
		
		else{
			seat.setStatus(SeatStatus.EXCLUDED);
		}
		
		daoSeat.save(seat);
	
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
