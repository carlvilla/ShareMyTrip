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
		
		String antiguaContraseña = request.getParameter("pass");
		String nuevaContraseña = request.getParameter("newPass");
		String nuevaContraseñaVerificacion = request.getParameter("verPass");
		
		HttpSession session=request.getSession();
		User usuario=((User)session.getAttribute("user"));
	
		
		//Puede querer modificar solo la información personal y
		//no la contraseña, por ello se comprueba que no modifica los campos de las
		//contraseñas. 
		if(comprobarDatos(nuevoNombre,nuevosApellidos,nuevoEmail)){
			
			if(comprobarNoModificaContraseñas(antiguaContraseña,nuevaContraseña
					,nuevaContraseñaVerificacion)){
				
				usuario.setEmail(nuevoEmail);
				usuario.setName(nuevoNombre);
				usuario.setSurname(nuevosApellidos);
				
				
			}
			
			else{
				
				if(usuario.getPassword().equals(antiguaContraseña) 
						&& !nuevaContraseña.isEmpty() 
						&& !nuevaContraseñaVerificacion.isEmpty()
						&& nuevaContraseña.equals(nuevaContraseñaVerificacion)){
					
					usuario.setEmail(nuevoEmail);
					usuario.setName(nuevoNombre);
					usuario.setSurname(nuevosApellidos);
					usuario.setPassword(nuevaContraseña);
					
					
				}
				
				else{
					
					if(!usuario.getPassword().equals(antiguaContraseña)){
						request.setAttribute("error", "La contraseña actual es incorrecta");
						return "FRACASO";
					}
					
					else if(nuevaContraseña.isEmpty() 
							&& nuevaContraseñaVerificacion.isEmpty()){
						request.setAttribute("error", "Debe especificar la nueva contraseña");
						return "FRACASO";
					}
					
					else{
						request.setAttribute("error", "Las contraseñas no coinciden");
						return "FRACASO";
					}
					
					
				}
					
				
			}
		}
		
		
		else{
			
			if(comprobarNoModificaContraseñas(antiguaContraseña,nuevaContraseña
					,nuevaContraseñaVerificacion)){
				
				request.setAttribute("error", "No puede haber campos vacios");
				return "FRACASO";
			}

	
		
	}
		
		
		try {
			UserDao dao = PersistenceFactory.newUserDao();
			dao.update(usuario);

		}
		catch (Exception e) {
			Log.error("Algo ha ocurrido actualizando los datos de [%s]", usuario.getLogin());
		}
		return "EXITO";
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
	
	public boolean comprobarDatos(String nombre,String apellidos,String email){
		
		if(nombre.isEmpty() || apellidos.isEmpty() || email.isEmpty()){
			return false;
		}
		
		return true;
		
		
		
	}
	
	
	
	public boolean comprobarNoModificaContraseñas(String antiguaContraseña,String nuevaContraseña,String contraseñaVerificacion){
		
		if(antiguaContraseña.isEmpty() && nuevaContraseña.isEmpty() && contraseñaVerificacion.isEmpty()){
			return true;
		}
		

		return false;
		
		
		
	}
	
	
}
