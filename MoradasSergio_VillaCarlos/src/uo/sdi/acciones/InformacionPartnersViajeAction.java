package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Seat;
import uo.sdi.model.Trip;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.TripDao;
import uo.sdi.persistence.UserDao;

public class InformacionPartnersViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();

		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		SeatDao seatDao = PersistenceFactory.newSeatDao();
		
		List<Seat> listaAsientos = seatDao.findByTrip(idViaje);
		
		UserDao userDao = PersistenceFactory.newUserDao();
		List<User> partners =  new LinkedList<>();
		
		TripDao tripDao = PersistenceFactory.newTripDao();
		Trip viaje = tripDao.findById(idViaje);
		
		for(Seat seat : listaAsientos){
			if(!seat.getUserId().equals(idUsuario) && !seat.getUserId().equals(viaje.getPromoterId())){
				User compi = userDao.findById(seat.getUserId());
				partners.add(compi);
			}
		}
		
		request.setAttribute("partners",partners);
		
		return "EXITO";
	}

}
