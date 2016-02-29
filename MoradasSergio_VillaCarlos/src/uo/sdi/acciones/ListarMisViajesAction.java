package uo.sdi.acciones;

import java.util.Date;
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
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;

public class ListarMisViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		// Crear distintas colecciones para los viajes en los es:
		// participante, interesado o promotor

		List<Trip> viajesParticipante = new LinkedList<Trip>(); // Tabla TSeats
		List<Trip> viajesInteresado = new LinkedList<Trip>(); // Tabla
																// TApplications
		List<Trip> viajesExcluido = new LinkedList<Trip>(); // Tabla
															// TApplications
		List<Trip> viajesSinPlaza = new LinkedList<Trip>(); // Tabla
															// TApplications
		List<Trip> viajesPromotor; // Tabla TTrip
		List<Trip> viajesPromotorAux = new LinkedList<Trip>();

		HttpSession session = request.getSession();

		Long idUsuario = (Long) ((User) session.getAttribute("user")).getId();

		List<Seat> participanteAux = PersistenceFactory.newSeatDao()
				.findByUser(idUsuario);

		for (Seat seat : participanteAux) {

			Trip viajeSeat = PersistenceFactory.newTripDao().findById(
					seat.getTripId());

			comprobarFechaViaje(viajeSeat);

			if (!viajeSeat.getPromoterId().equals(idUsuario) 
					&& !viajeSeat.getStatus().equals(TripStatus.DONE)
					&& !viajeSeat.getStatus().equals(TripStatus.CANCELLED)) {
				
				if (seat.getStatus().equals(SeatStatus.ACCEPTED))
					viajesParticipante.add(viajeSeat);
				
				else
					viajesExcluido.add(viajeSeat);
			}
		}

		List<Application> interesadosAux = PersistenceFactory
				.newApplicationDao().findByUserId(idUsuario);

		for (Application app : interesadosAux) {
			Trip viajeApp = PersistenceFactory.newTripDao().findById(
					app.getTripId());

			comprobarFechaViaje(viajeApp);

			if (viajeApp.getStatus().equals(TripStatus.OPEN)) {
				if (viajeApp.getAvailablePax() > 0)
					viajesInteresado.add(viajeApp);
				else
					viajesSinPlaza.add(viajeApp);
			}
			
			else if(viajeApp.getStatus().equals(TripStatus.CLOSED)){
				viajesExcluido.add(viajeApp);
			}
		}

		viajesPromotor = PersistenceFactory.newTripDao().findByPromoterId(
				idUsuario);

		for (Trip viaje : viajesPromotor) {
			if (!viaje.getStatus().equals(TripStatus.CANCELLED)) {
				comprobarFechaViaje(viaje);
				if (!viaje.getStatus().equals(TripStatus.DONE)) {
					viajesPromotorAux.add(viaje);
				}
			}
		}

		Map<String, List<Trip>> viajes = new HashMap<String, List<Trip>>();
		viajes.put("ADMITIDO", viajesParticipante);
		viajes.put("PROMOTOR", viajesPromotorAux);
		viajes.put("PENDIENTE", viajesInteresado);
		viajes.put("SIN PLAZA", viajesSinPlaza);
		viajes.put("EXCLUIDO", viajesExcluido);

		

		request.setAttribute("viajes", viajes);


		return "EXITO";
	}

	private void comprobarFechaViaje(Trip viajeSeat) {
		
		if (new Date().after(viajeSeat.getArrivalDate()))
			viajeSeat.setStatus(TripStatus.DONE);
		
		else if (new Date().after(viajeSeat.getClosingDate()))
			viajeSeat.setStatus(TripStatus.CLOSED);
	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
