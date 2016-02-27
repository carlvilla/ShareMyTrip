package uo.sdi.acciones;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;

public class ValorarCompañerosAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		

		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		RatingDao daoRating = PersistenceFactory.newRatingDao();
		SeatDao daoSeat = PersistenceFactory.newSeatDao();
		List<Seat> asientos = daoSeat.findByTrip(idViaje);
		
		for(Seat seat:asientos){
			if(!seat.getUserId().equals(idUsuario)){
				String comentario = request.getParameter(""+seat.getUserId());
				//Valor
				Rating rating = new Rating();
				rating.setComment(comentario);
				rating.setSeatAboutTripId(idViaje);
				rating.setSeatFromTripId(idViaje);
				rating.setSeatAboutUserId(seat.getUserId());
				rating.setSeatFromUserId(idUsuario);
				//cambiar
				rating.setValue(5);
				daoRating.save(rating);
			}
		}
		
		
		
		return "EXITO";
	}

}
