package uo.sdi.acciones;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.SeatStatus;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class InfoPromotorParticipantesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		User promotor;
		List<Rating> comentariosPromotor;
		List<User> usuariosAceptados = new LinkedList<User>();
		Map<User,List<Rating>> comentariosAceptados = new HashMap<User,List<Rating>>();
		
		Trip viaje = PersistenceFactory.newTripDao()
				.findById(Long.valueOf(request.getParameter("idViaje")));
		
		Long idPromotor = viaje.getPromoterId();
		
		
		try {
			
			comentariosPromotor = PersistenceFactory.newRatingDao()
					.findByAbout(idPromotor);
			
			List<Seat> usuarios = PersistenceFactory.newSeatDao()
					.findByTrip(viaje.getId());
			
			for(Seat usuario:usuarios){
				if(usuario.getStatus().equals(SeatStatus.ACCEPTED) && !viaje.getPromoterId().equals(usuario.getUserId())){
					User usuarioAceptado = PersistenceFactory.newUserDao()
							.findById(usuario.getUserId());
					
					usuariosAceptados.add(usuarioAceptado);
							
				}
			
			}
			
			//Obtener comentarios participantes aceptados
			for(User usuario:usuariosAceptados){
				
				List<Rating> comentarios = PersistenceFactory.newRatingDao()
						.findByAbout(usuario.getId());
				
				comentariosAceptados.put(usuario, comentarios);
			
			}

			request.setAttribute("comentariosPromotor", comentariosPromotor);
			request.setAttribute("comentariosUsuarios", comentariosAceptados);
			
			promotor = PersistenceFactory.newUserDao().findById(idPromotor);
			request.setAttribute("promotor", promotor);
			
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
