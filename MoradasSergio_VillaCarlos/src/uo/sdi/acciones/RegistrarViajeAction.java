package uo.sdi.acciones;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.model.AddressPoint;
import uo.sdi.model.Trip;
import uo.sdi.model.TripStatus;
import uo.sdi.model.User;
import uo.sdi.model.Waypoint;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

public class RegistrarViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		//Datos lugar de Salida
		String calleSalida = request.getParameter("calleSalida");
		String ciudadSalida = request.getParameter("ciudadSalida");
		String provinciaSalida = request.getParameter("provinciaSalida");
		String paisSalida = request.getParameter("paisSalida");
		String postalSalida = request.getParameter("postalSalida");
		String longitudSalida = request.getParameter("longitudSalida");
		String latitudSalida = request.getParameter("latitudSalida");
		
		//Datos lugar de Destino
		String calleDestino = request.getParameter("calleDestino");
		String ciudadDestino = request.getParameter("ciudadDestino");
		String provinciaDestino = request.getParameter("provinciaDestino");
		String paisDestino = request.getParameter("paisDestino");
		String postalDestino = request.getParameter("postalDestino");
		String longitudDestino = request.getParameter("longitudDestino");
		String latitudDestino = request.getParameter("latitudDestino");
		
		//Otros Datos del Viaje
		String fechaHoraSalida = request.getParameter("fechaHoraSalida");
		String fechaHoraLLegada = request.getParameter("fechaHoraLLegada");
		String fechaLimite = request.getParameter("fechaLimite");
		String costeViaje = request.getParameter("costeViaje");
		String descripcionViaje = request.getParameter("descripcionViaje");
		String nMaxPlazas = request.getParameter("numeroMaxPlazas");
		String nDispoPlazas = request.getParameter("numeroDispPlazas");

		HttpSession session = request.getSession();
		
		//Comprobamos que no hay caso vacios a excepcion de la longitud y latitud
		if (comprobarCamposVacios(calleSalida, ciudadSalida, provinciaSalida,
				paisSalida, postalSalida, longitudSalida, latitudSalida,
				calleDestino, ciudadDestino, provinciaDestino, paisDestino,
				postalDestino, longitudDestino, latitudDestino,
				fechaHoraSalida, fechaHoraLLegada, fechaLimite, costeViaje,
				descripcionViaje, nMaxPlazas, nDispoPlazas)) {

			try {
				
				Trip newTrip = new Trip();

				//Creamos AdreesPoint de la Salida
				Waypoint wSalida = new Waypoint(
						Double.parseDouble(latitudSalida),
						Double.parseDouble(longitudSalida));
				AddressPoint addresSalida = new AddressPoint(calleSalida,
						ciudadSalida, provinciaSalida, paisSalida,
						postalSalida, wSalida);
				
				//Creamos AdreesPoint del Destino
				Waypoint wDestino = new Waypoint(
						Double.parseDouble(latitudDestino),
						Double.parseDouble(longitudDestino));
				AddressPoint addresDestino = new AddressPoint(calleDestino,
						ciudadDestino, provinciaDestino, paisDestino,
						postalDestino, wDestino);

				newTrip.setDeparture(addresSalida);
				newTrip.setDestination(addresDestino);
				
				SimpleDateFormat formatoDeFecha = new SimpleDateFormat(
						"dd/MM/yyyy-HH:mm:ss");
				newTrip.setArrivalDate(formatoDeFecha.parse(fechaHoraSalida));
				newTrip.setDepartureDate(formatoDeFecha.parse(fechaHoraLLegada));
				newTrip.setClosingDate(formatoDeFecha.parse(fechaLimite));

				newTrip.setEstimatedCost(Double.parseDouble(costeViaje));
				newTrip.setComments(descripcionViaje);
				newTrip.setMaxPax(Integer.parseInt(nMaxPlazas));
				newTrip.setAvailablePax(Integer.parseInt(nDispoPlazas));
				newTrip.setStatus(TripStatus.OPEN);
				
				//Obtenemos el id del promotor 
				User usuario = ((User) session.getAttribute("user"));
				UserDao dao = PersistenceFactory.newUserDao();
				User userByLogin = dao.findByLogin(usuario.getLogin());

				newTrip.setPromoterId(userByLogin.getId());
				
				TripDao tdao = PersistenceFactory.newTripDao();
				Trip tripMismaFecha = tdao
						.findByPromoterIdAndArrivalDate(userByLogin.getId(), 
								formatoDeFecha.parse(fechaHoraSalida));
				if(tripMismaFecha!=null)
					return "FRACASO";
				
				//Añadimos el viaje a la BD
				TripDao trip = PersistenceFactory.newTripDao();
				trip.save(newTrip);

			} catch (ParseException e) {
				Log.error("Error en el formato de un dato");
				request.setAttribute("error", "Error al registrarse: FORMATO DE DATOS INCORRECTO");
				return "FRACASO";
			}

			return "EXITO";
		} else {
			request.setAttribute("error", "Error al registrarse: CAMPOS VACIOS");
			return "FRACASO";
		}
	}

	private boolean comprobarCamposVacios(String calleSalida,
			String ciudadSalida, String provinciaSalida, String paisSalida,
			String postalSalida, String longitudSalida, String latitudSalida,
			String calleDestino, String ciudadDestino, String provinciaDestino,
			String paisDestino, String postalDestino, String longitudDestino,
			String latitudDestino, String fechaHoraSalida,
			String fechaHoraLLegada, String fechaLimite, String costeViaje,
			String descripcionViaje, String nMaxPlazas, String nDispoPlazas) {

		if (calleSalida.compareTo("") == 0 || ciudadSalida.compareTo("") == 0
				|| provinciaSalida.compareTo("") == 0
				|| paisSalida.compareTo("") == 0
				|| postalSalida.compareTo("") == 0
				//|| longitudSalida.compareTo("") == 0
				//|| latitudSalida.compareTo("") == 0
				|| calleDestino.compareTo("") == 0
				|| provinciaDestino.compareTo("") == 0
				|| paisDestino.compareTo("") == 0
				|| postalDestino.compareTo("") == 0
				//|| longitudDestino.compareTo("") == 0
				//|| latitudDestino.compareTo("") == 0
				|| fechaHoraSalida.compareTo("") == 0
				|| fechaHoraLLegada.compareTo("") == 0
				|| fechaLimite.compareTo("") == 0
				|| costeViaje.compareTo("") == 0
				|| descripcionViaje.compareTo("") == 0
				|| nMaxPlazas.compareTo("") == 0
				|| nDispoPlazas.compareTo("") == 0) {
			return false;
		}

		return true;
	}

}
