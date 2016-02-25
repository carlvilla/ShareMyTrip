package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Application;
import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceException;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.TripDao;
import alb.util.log.Log;

public class SolicitarPlazaAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		//Comprobar que queden plazas en ese viaje.
		//Aunque solo se muestren los viajes con plazas
		//puede que entraran dos personas al mismo tiempo para
		//ver ese viaje y que solo quedara una plaza. Por lo que hay que 
		//comprobar que sigan quedando plazas
		
		Long idViaje = Long.parseLong(request.getParameter("idViaje"));
		
		Trip viaje = PersistenceFactory.newTripDao().
				findById(idViaje);
		
		HttpSession session = request.getSession();
		
		if(viaje.getAvailablePax()>0){
			
			
			
			Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
			
			try{
			PersistenceFactory.newApplicationDao().save(new Application(idUsuario,idViaje));
			}
			
			catch(PersistenceException e){
				
				//Ya está apuntado a ese viaje
				
				return "FRACASO";
				
			}
			
			
			/*TripDao dao = PersistenceFactory.newTripDao();
			
			viaje.setAvailablePax(viaje.getAvailablePax()-1);
			
			dao.update(viaje);*/
			
			return "EXITO";
			
		}
		
		else{
			
			return "FRACASO";
			
		}
		
		
		
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}