package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ListarSolicitudesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Application> solicitudes;
		List<Seat> confirmados;
		List<User> usuarios = new LinkedList<User>();
		List<User> usuariosConfirmados = new LinkedList<User>();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		TripDao daoViaje = PersistenceFactory.newTripDao();
		Trip viaje = daoViaje.findById(idViaje);
		
		//El viaje es necesario en el jsp de listar solicitudes, porque si
		//ya están todas las plazas ocupadas las solicitudes que queden
		//no se podrán administrar. De modo que si alguien cancela la 
		//participación se podrán aceptar la solicitud de estos
		request.setAttribute("viaje", viaje);
		
		
		solicitudes = PersistenceFactory.newApplicationDao()
				.findByTripId(idViaje);
		
		UserDao dao = PersistenceFactory.newUserDao();
		
		for(Application app: solicitudes){
			usuarios.add(dao.findById(app.getUserId()));
				
		}
		
		
		
		Log.debug("Se han obtenido [%s] solicitudes", usuarios.size());
		
		confirmados = PersistenceFactory.newSeatDao().findByTrip(idViaje);
		
		for(Seat seat: confirmados){
			if(seat.getStatus().equals(SeatStatus.ACCEPTED))
				usuariosConfirmados.add(dao.findById(seat.getUserId()));
				
		}
		
		Log.debug("Se han encontrado [%s] pasajeros confirmados", usuariosConfirmados.size());
		
		
		request.setAttribute("solicitantes", usuarios);
		request.setAttribute("confirmados", usuariosConfirmados);

			
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
