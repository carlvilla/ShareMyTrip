package uo.sdi.acciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.persistence.ApplicationDao;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
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
			viajesAux = PersistenceFactory.newTripDao().findAll();

			if(usuario!=null){
				//Para usuarios registrados
				for (Trip viaje : viajesAux) {
				
				if (viaje.getStatus().equals(TripStatus.OPEN)
						&& viaje.getAvailablePax() > 0
						&& !viaje.getPromoterId().equals(usuario.getId())
						&& viaje.getClosingDate().after(new Date())
						&& comprobarRelacionViajeUsuario(usuario.getId(),
								viaje.getId())) {

					viajes.add(viaje);

				}

			}
			}
			else{
				//Para usuarios públicos
				for (Trip viaje : viajesAux) {

					if (viaje.getStatus().equals(TripStatus.OPEN)
							&& viaje.getAvailablePax() > 0
							&& viaje.getClosingDate().after(new Date())) {

						viajes.add(viaje);

					}

				}
			}
			
			String orden = request.getParameter("orden");
			
			if(orden!=null){
				
				ordenarViajes(viajes,orden);
				
			}

			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
	}

	/**
	 * Ordena los viajes según el parámetro orden utilizando un aloritmo de
	 * ordenación burbuja
	 * 
	 * @param viajes
	 * @param orden
	 */
	private void ordenarViajes(List<Trip> viajes,String orden) {
		 for(int i = 0; i < viajes.size() - 1; i++)
	        {
	            for(int j = 0; j < viajes.size() - 1; j++)
	            {
	                if (ordenacion(viajes.get(j),viajes.get(j + 1),orden))
	                {
	                    Trip tmp = viajes.get(j+1);
	                    viajes.set(j+1,viajes.get(j));
	                    viajes.set(j,tmp);
	                }
	            }
	        }
		
	}

	/**
	 * 
	 * Devuelve un boolean que indica que viaje de los dos pasados debe 
	 * de ir antes en la lista
	 * 
	 * @param trip
	 * @param trip2
	 * @param orden
	 * @return
	 */
	private boolean ordenacion(Trip trip, Trip trip2,String orden) {
		
		if(orden.equals("origen")){
			
			if(trip.getDeparture().getCity().compareTo(trip2.getDeparture().getCity())>0){
				return true;
			}
			else{
				return false;
			}
			
			
		}
		
		else if(orden.equals("destino")){
			
			if(trip.getDestination().getCity().compareTo(trip2.getDestination().getCity())>0){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		else if(orden.equals("fechaSalida")){
			
			if(trip.getDepartureDate().after(trip2.getDepartureDate())){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		else if(orden.equals("fechaLlegada")){
			
			if(trip.getArrivalDate().after(trip2.getArrivalDate())){
				return true;
			}
			else{
				return false;
			}
			
		}
		
		else if(orden.equals("fechaFin")){
			
			if(trip.getClosingDate().after(trip2.getClosingDate())){
				return true;
			}
			else{
				return false;
			}
			
		}
		
	else if(orden.equals("plazas")){
			
			if(trip.getAvailablePax()<(trip2.getAvailablePax())){
				return true;
			}
			else{
				return false;
			}
			
		}
		

		return false;
	}

	private boolean comprobarRelacionViajeUsuario(Long idUsuario, Long idViaje) {

		SeatDao seatDao = PersistenceFactory.newSeatDao();
		ApplicationDao appDao = PersistenceFactory.newApplicationDao();

		Seat seat = seatDao.findByUserAndTrip(idUsuario, idViaje);

		Long[] ids = { idUsuario, idViaje };
		Application app = appDao.findById(ids);

		if (seat == null && app == null) {
			return true;
		}

		return false;

	}

	@Override
	public String toString() {
		return getClass().getName();
	}

}
