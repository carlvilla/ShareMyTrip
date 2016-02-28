package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Rating;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.TripDao;

public class ValorarPromotorAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		RatingDao daoRating = PersistenceFactory.newRatingDao();
		
		String comentario = request.getParameter("comentario");
		Integer valoracion = Integer.parseInt(request.getParameter("valoracion"));
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		Trip viaje = tripDao.findById(idViaje);
		
		Rating rating = new Rating();
		rating.setComment(comentario);
		rating.setSeatAboutTripId(idViaje);
		rating.setSeatFromTripId(idViaje);
		rating.setSeatAboutUserId(viaje.getPromoterId());
		rating.setSeatFromUserId(idUsuario);
		rating.setValue(valoracion);
		
		daoRating.save(rating);
		
		return "EXITO";
	}

}
