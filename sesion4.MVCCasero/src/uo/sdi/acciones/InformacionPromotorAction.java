package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import uo.sdi.model.Rating;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import alb.util.log.Log;

public class InformacionPromotorAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		User promotor;
		List<Rating> comentariosPromotor;
		
		Long idPromotor = Long.valueOf(request.getParameter("id"));
		
		try {
			comentariosPromotor = PersistenceFactory.newRatingDao()
					.findByAbout(idPromotor);

			request.setAttribute("comentariosPromotor", comentariosPromotor);
			
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
