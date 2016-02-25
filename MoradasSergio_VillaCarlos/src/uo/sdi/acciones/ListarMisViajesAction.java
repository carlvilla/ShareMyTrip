package uo.sdi.acciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class ListarMisViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		//Crear distintas colecciones para los viajes en los es:
		//participante, interesado o promotor

		List<Trip> viajesParticipante = new LinkedList<Trip>(); //Tabla TSeats
		List<Trip> viajesInteresado = new LinkedList<Trip>(); //Tabla TApplications
		List<Trip> viajesExcluido = new LinkedList<Trip>(); //Tabla TApplications
		List<Trip> viajesSinPlaza = new LinkedList<Trip>(); //Tabla TApplications
		List<Trip> viajesPromotor; //Tabla TTrip
		
		HttpSession session = request.getSession();
		
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
	
		List<Seat> participanteAux = PersistenceFactory.newSeatDao().findByUser(idUsuario);
		
		for(Seat seat:participanteAux){
			
			Trip viajeSeat = PersistenceFactory.newTripDao().findById(seat.getTripId());
			
			if(seat.getStatus().equals(SeatStatus.ACCEPTED))
				viajesParticipante.add(viajeSeat);
			else
				viajesExcluido.add(viajeSeat);
			
		}
		
		List<Application> interesadosAux = PersistenceFactory.newApplicationDao().findByUserId(idUsuario); 
			
		for(Application app: interesadosAux){
			Trip viajeApp = PersistenceFactory.newTripDao().findById(app.getTripId());
			
			if(viajeApp.getAvailablePax()>0)
				viajesInteresado.add(viajeApp);
			else
				viajesSinPlaza.add(viajeApp);
			
		}
		
		viajesPromotor = PersistenceFactory.newTripDao().findByPromoterId(idUsuario);
		
		Map<String,List<Trip>> viajes = new HashMap<String,List<Trip>>();
		viajes.put("ADMITIDO", viajesParticipante);
		viajes.put("PROMOTOR", viajesPromotor);
		viajes.put("PENDIENTE", viajesInteresado);
		viajes.put("SIN PLAZA", viajesSinPlaza);
		viajes.put("EXCLUIDO", viajesExcluido);
		
		
		request.setAttribute("viajes", viajes);
	
		
				
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
