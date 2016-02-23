package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class ModificarDatosAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		String nuevoEmail=request.getParameter("email");
		String nuevoNombre=request.getParameter("nombre");
		String nuevosApellidos=request.getParameter("apellidos");
		
		String antiguaContraseña = request.getParameter("apellidos");
		String nuevaContraseña = request.getParameter("apellidos");
		String nuevaContraseñaVerificacion = request.getParameter("apellidos");
		
		HttpSession session=request.getSession();
		User usuario=((User)session.getAttribute("user"));
	
		
		//Solo se modificaran los datos si las contraseña antigua es correcta 
		//y la nueva y de verificación también (si estas están vacias la
		//contraseña no se modifica)
		if(usuario.getPassword().equals(antiguaContraseña) 
				&& nuevaContraseña.equals(nuevaContraseñaVerificacion)){
			
			
			usuario.setEmail(nuevoEmail);
			usuario.setName(nuevoNombre);
			usuario.setSurname(nuevosApellidos);
			
			if(nuevaContraseña!=""){
			
				usuario.setPassword(nuevaContraseña);
			
			}
			
			
		}
		
		else{
			
			if(!usuario.getPassword().equals(antiguaContraseña)){
				request.setAttribute("error", "La contraseña actual es incorrecta");
			}
			
			else{
				request.setAttribute("error","Las contraseñas no coinciden");
			}
			
			
		}
		
		
		try {
			UserDao dao = PersistenceFactory.newUserDao();
			dao.update(usuario);
			Log.debug("Modificado email de [%s] con el valor [%s]", usuario.getLogin(), nuevoEmail);
		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando el email de [%s]", usuario.getLogin());
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
