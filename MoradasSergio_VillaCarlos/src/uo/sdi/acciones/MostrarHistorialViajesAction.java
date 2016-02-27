package uo.sdi.acciones;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;

public class MostrarHistorialViajesAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session = request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		
		List<Trip> viajesPromotorDone = new LinkedList<Trip>();
		List<Trip> viajesAdmitidoDone = new LinkedList<Trip>();
		Date fechaActual = new Date();
		
		List<Trip> listaViajesPromotor = tripDao.findByPromoterId(idUsuario);
		
		for(Trip viaje:listaViajesPromotor){
			if(fechaActual.after(viaje.getArrivalDate()) || viaje.getStatus().equals(TripStatus.CANCELLED)){
				if(viaje.getStatus()!=TripStatus.DONE && !viaje.getStatus().equals(TripStatus.CANCELLED)){
					viaje.setStatus(TripStatus.DONE);
				}
				viajesPromotorDone.add(viaje);
			}		
		}
		
		List<Seat> participanteAux = PersistenceFactory.newSeatDao().findByUser(idUsuario);
		
		for(Seat seat:participanteAux){
			
			Trip viajeSeat = PersistenceFactory.newTripDao().findById(seat.getTripId());
			
			if(fechaActual.after(viajeSeat.getArrivalDate())){
				if(viajeSeat.getStatus()!=TripStatus.DONE){
					viajeSeat.setStatus(TripStatus.DONE);
				}
				viajesAdmitidoDone.add(viajeSeat);
			}
			
		}
		
		
		Map<String,List<Trip>> viajes = new HashMap<String,List<Trip>>();
		viajes.put("ADMITIDO", viajesAdmitidoDone);
		viajes.put("PROMOTOR", viajesPromotorDone);

		
		request.setAttribute("viajes", viajes);
			
		return "EXITO";
	}

}
