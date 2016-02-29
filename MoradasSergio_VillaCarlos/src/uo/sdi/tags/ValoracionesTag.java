package uo.sdi.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import uo.sdi.model.Rating;
import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.RatingDao;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;

public class ValoracionesTag extends TagSupport {

	private static final long serialVersionUID = 1L;

	private Long idViaje;

	private Long idUsuario;

	private String implicacion;

	public int doEndTag() {

		RatingDao ratingDao = PersistenceFactory.newRatingDao();
		SeatDao seatDao = PersistenceFactory.newSeatDao();
		Seat seat = seatDao.findByUserAndTrip(idUsuario, idViaje);
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		Trip trip = tripDao.findById(idViaje);
		List<Seat> asientos = seatDao.findByTrip(idViaje);
		
		try {
			JspWriter out = pageContext.getOut();
			
			if (implicacion.compareTo("PROMOTOR") == 0) {
				boolean comments = false;
				List<Rating> ratings = ratingDao.findByFrom(idUsuario, idViaje);
				for (Rating r : ratings) {
					if (r.getComment() != null) {
						comments = true;
					}
				}
				if (!comments && asientos.size()>1)
					out.println("<li><a href=\"informacionPartners?idViaje="
							+ idViaje + "\">Valorar Compañeros</a></li>");
			} else {
				List<Rating> ratings = ratingDao.findByFrom(idUsuario, idViaje);
				boolean comments = false;
				boolean commentPromotor = false;
				for (Rating r : ratings) {
					if (r.getComment() != null) {
						if(r.getSeatAboutUserId().equals(trip.getPromoterId()))
							commentPromotor=true;
						else
							comments = true;
					}
				}
				
				if(!(asientos.size()==2 && !idUsuario.equals(trip.getPromoterId())))
						if(asientos.size()>1)
							if (!comments)
								out.println("<li><a href=\"informacionPartners?idViaje="
										+ idViaje + "\">Valorar Compañeros</a></li>");
				if (seat != null && seat.getComment() == null)
					out.println("<li><a href=\"valorarViaje.jsp?idViaje="
							+ idViaje + "\">Valorar Viaje</a></li>");
				if(!commentPromotor)
					out.println("<li><a href=\"valorarPromotor.jsp?idViaje="
							+ idViaje + "\">Valorar Promotor</a></li>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return EVAL_PAGE;
	}

	public Long getIdViaje() {
		return idViaje;
	}

	public void setIdViaje(Long idViaje) {
		this.idViaje = idViaje;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsario) {
		this.idUsuario = idUsario;
	}

	public String getImplicacion() {
		return implicacion;
	}

	public void setImplicacion(String implicacion) {
		this.implicacion = implicacion;
	}

}
