package uo.sdi.acciones;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ValorarPartnersAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		

		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		Trip trip = tripDao.findById(idViaje);
		
		RatingDao daoRating = PersistenceFactory.newRatingDao();
		SeatDao daoSeat = PersistenceFactory.newSeatDao();
		List<Seat> asientos = daoSeat.findByTrip(idViaje);
		
		for(Seat seat:asientos){
			if(!seat.getUserId().equals(idUsuario) && !seat.getUserId().equals(trip.getPromoterId())){
				String comentario = request.getParameter(""+seat.getUserId());
				Integer valoracion = Integer.parseInt(request.getParameter(seat.getUserId()+"_valoracion"));
				
				Rating rating = new Rating();
				rating.setComment(comentario);
				rating.setSeatAboutTripId(idViaje);
				rating.setSeatFromTripId(idViaje);
				rating.setSeatAboutUserId(seat.getUserId());
				rating.setSeatFromUserId(idUsuario);
				rating.setValue(valoracion);
				
				daoRating.save(rating);
			}
		}
		
		
		
		return "EXITO";
	}

}
