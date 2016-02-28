package uo.sdi.acciones.util;

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

}