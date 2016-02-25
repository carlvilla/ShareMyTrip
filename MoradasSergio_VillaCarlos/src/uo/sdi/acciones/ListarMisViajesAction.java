package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
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
		List<Trip> viajesPromotor; //Tabla TTrip
		
		HttpSession session = request.getSession();
		
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
	
		
		List<Seat> participanteAux = PersistenceFactory.newSeatDao().findByUser(idUsuario);
		
		for(Seat seat:participanteAux){
			viajesParticipante.add(PersistenceFactory.newTripDao().findById(seat.getTripId()));
		}
		
		List<Application> interesadosAux = PersistenceFactory.newApplicationDao().findByUserId(idUsuario); 
			
		for(Application app: interesadosAux){
			viajesInteresado.add(PersistenceFactory.newTripDao().findById(app.getTripId()));
		}
		
		
		
		viajesPromotor = PersistenceFactory.newTripDao().findByPromoterId(idUsuario);
		
		request.setAttribute("viajesParticipante", viajesParticipante);
		request.setAttribute("viajesInteresado", interesadosAux);
		request.setAttribute("viajesPromotor", viajesPromotor);
		
				
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
