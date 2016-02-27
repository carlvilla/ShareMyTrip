package uo.sdi.acciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class ListarViajesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Trip> viajesAux;
		List<Trip> viajes = new LinkedList<Trip>();
		
		HttpSession sesion = request.getSession();
		User usuario = (User) sesion.getAttribute("user");
		

		
		try {
			viajesAux=PersistenceFactory.newTripDao().findAll();
			
			for(Trip viaje:viajesAux){

				if(viaje.getStatus().equals(TripStatus.OPEN) 
						&& viaje.getAvailablePax()>0
						&& !viaje.getPromoterId().equals(usuario.getId())
						&& viaje.getClosingDate().after(new Date())){
							
							viajes.add(viaje);
							
				}

			}
		
			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes", viajes.size());
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
