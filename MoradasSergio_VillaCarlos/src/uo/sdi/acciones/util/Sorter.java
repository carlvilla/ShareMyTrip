package uo.sdi.acciones.util;

import java.util.LinkedList;
import java.util.List;

import uo.sdi.model.Trip;

public class Sorter {
	
	

	/**
	 * Ordena los viajes según el parámetro orden utilizando un aloritmo de
	 * ordenación burbuja
	 * 
	 * @param viajes
	 * @param orden
	 */
	public static void ordenarViajes(List<Trip> viajes,String orden) {
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
	private static boolean ordenacion(Trip trip, Trip trip2,String orden) {
		
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

	public static void filtrarViajes(List<Trip> viajes, String filtrar,String cadena) {
		
		List<Trip> viajesAEliminar = new LinkedList<Trip>();
		
		for(Trip viaje:viajes){
			
			if(filtrar.equals("origen")){
				
				if(!viaje.getDeparture().getCity().toUpperCase().equals(cadena.toUpperCase())){
					viajesAEliminar.add(viaje);
				}
				
			
			}
			else if(filtrar.equals("destino")){
				
				if(!viaje.getDestination().getCity().toUpperCase().equals(cadena.toUpperCase())){
					viajesAEliminar.add(viaje);
				}
				
			}
			
			else if(filtrar.equals("plazas")){
				
				if(esNumerico(cadena) && viaje.getAvailablePax()!=(Integer.parseInt(cadena))){
					viajesAEliminar.add(viaje);
				}
				
			}
			
		}
		
		
		
		
		for(Trip viaje:viajesAEliminar){
			viajes.remove(viaje);
		}
	
		
	}
	
	/**
	 * 
	 * Comprueba si el string pasado es un número
	 * 
	 * @param cadena
	 * @return
	 */
	private static boolean esNumerico(String cadena){
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe){
			return false;
		}
	}

}
