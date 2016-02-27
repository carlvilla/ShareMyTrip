package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.Seat;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.SeatDao;

public class ValorarViajeAction implements Accion{

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		HttpSession session=request.getSession();
		Long idUsuario = (Long)((User) session.getAttribute("user")).getId();

		String comentario = request.getParameter("comentario");
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		
		SeatDao daoSeat = PersistenceFactory.newSeatDao();
		
		Seat asiento = daoSeat.findByUserAndTrip(idUsuario, idViaje);
		asiento.setComment(comentario);
		daoSeat.update(asiento);
		
		return "EXITO";
	}

}
