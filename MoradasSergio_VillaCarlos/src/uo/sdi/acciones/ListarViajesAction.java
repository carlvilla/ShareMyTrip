package uo.sdi.acciones;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.acciones.util.Sorter;
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
			String filtrar = request.getParameter("filtrar");
			String cadenaFiltrar = request.getParameter("cadenaFiltrar");
			
			if(orden!=null){
				
				//Se llama a un método estático de la clase Sorter
				//encargado de ordenar la lista de viajes dependiendo
				//de la opción elegida en el comboBox
				Sorter.ordenarViajes(viajes,orden);
				
			}
			
			if(filtrar!=null && cadenaFiltrar!=null 
					&& !filtrar.isEmpty() && !cadenaFiltrar.isEmpty()){
				
				//Se llama a un método estático de la clase Sorter
				//encargado de ordenar la lista de viajes dependiendo
				//de la opción elegida en el comboBox
				Sorter.filtrarViajes(viajes,filtrar,cadenaFiltrar);
				
			}

			request.setAttribute("listaViajes", viajes);
			Log.debug("Obtenida lista de viajes conteniendo [%d] viajes",
					viajes.size());
		} catch (Exception e) {
			Log.error("Algo ha ocurrido obteniendo lista de viajes");
		}
		return "EXITO";
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
