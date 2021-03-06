package uo.sdi.acciones;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uo.sdi.model.User;
import uo.sdi.model.UserStatus;
import uo.sdi.persistence.PersistenceFactory;
import uo.sdi.persistence.UserDao;
import alb.util.log.Log;

public class RegistrarseAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		String loginUsuario = request.getParameter("loginUsuario");
		String nombreUsuario = request.getParameter("nombreUsuario");
		String apellidosUsuario = request.getParameter("apellidosUsuario");
		String email = request.getParameter("email");
		String contraseña = request.getParameter("passwordUsuario");
		String confirmContraseña = request
				.getParameter("confirmPasswordUsuario");

		HttpSession session=request.getSession();
		
		if (comprobarCamposVacios(loginUsuario, nombreUsuario,
				apellidosUsuario, email, contraseña, confirmContraseña) == false){
			request.setAttribute("error", "Error al registrarse: campos vacios");
			return "FRACASO";
		}
		else{
			UserDao dao = PersistenceFactory.newUserDao();
			User userByLogin = dao.findByLogin(loginUsuario);
			if(userByLogin!=null){
				request.setAttribute("error", "Error al registrarse: nombre de usuario en uso");
				return "FRACASO";
			}
			else{
				if(confirmContraseña.compareTo(contraseña)==0){
					User user = new User();
					user.setId((long) 308);
					user.setEmail(email);
					user.setLogin(loginUsuario);
					user.setName(nombreUsuario);
					user.setSurname(apellidosUsuario);
					user.setPassword(contraseña);
					user.setStatus(UserStatus.ACTIVE);
					dao.save(user);
					
					//Como el id se auto genera hay que obtener el usuario
					//de la base para saber que id fue el adjudicado
					user.setId(dao.findByLogin(loginUsuario).getId());
							
					session.setAttribute("user", user);
						
					Log.info("Nombre de usuario [%s] ", loginUsuario);
					return "EXITO";
				}
				else{
					request.setAttribute("error", "Error al registrarse: las contraseñas no coinciden");
					return "FRACASO";
				}
			}
		}

			

	}

	private boolean comprobarCamposVacios(String loginUsuario,
			String nombreUsuario, String apellidosUsuario, String email,
			String contraseña, String confirmContraseña) {
		if (loginUsuario.compareTo("") == 0 || nombreUsuario.compareTo("") == 0
				|| email.compareTo("") == 0 || contraseña.compareTo("") == 0
				|| confirmContraseña.compareTo("") == 0)
			return false;
		return true;
	}

}
