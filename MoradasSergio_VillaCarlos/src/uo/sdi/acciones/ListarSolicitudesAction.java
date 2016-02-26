package uo.sdi.acciones;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.model.Application;
import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;

public class ListarSolicitudesAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		List<Application> solicitudes;
		List<User> usuarios = new LinkedList<User>();
		
		solicitudes = PersistenceFactory.newApplicationDao()
				.findByTripId(Long.valueOf(request.getParameter("idViaje")));
		
		UserDao dao = PersistenceFactory.newUserDao();
		
		for(Application app: solicitudes){
			usuarios.add(dao.findById(app.getUserId()));
				
		}
		
		request.setAttribute("solicitantes", usuarios);
		
		Log.debug("Se han obtenido [%s] solicitudes", usuarios.size());

			
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
