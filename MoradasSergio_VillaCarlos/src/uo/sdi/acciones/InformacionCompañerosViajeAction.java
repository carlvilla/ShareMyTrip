package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uo.sdi.model.Seat;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;
import uo.sdi.persistence.UserDao;

public class InformacionCompañerosViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();

		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		SeatDao seatDao = PersistenceFactory.newSeatDao();
		
		List<Seat> listaAsientos = seatDao.findByTrip(idViaje);
		
		UserDao userDao = PersistenceFactory.newUserDao();
		List<User> compañeros =  new LinkedList<>();
		
		for(Seat seat : listaAsientos){
			if(!seat.getUserId().equals(idUsuario)){
				User compi = userDao.findById(seat.getUserId());
				compañeros.add(compi);
			}
		}
		
		request.setAttribute("compañeros",compañeros);
		
		return "EXITO";
	}

}
