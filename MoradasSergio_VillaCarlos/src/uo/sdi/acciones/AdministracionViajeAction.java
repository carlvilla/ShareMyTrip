package uo.sdi.acciones;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;

public class AdministracionViajeAction implements Accion {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {

		HttpSession sesion = request.getSession();
		
		Long idViaje = Long.valueOf(request.getParameter("idViaje"));
		String implicacion = request.getParameter("implicacion");
	
		
		Log.info("Se ha seleccionado el viaje [%s] con implicacion [%s]",idViaje,implicacion);
		
		if(implicacion.equals("PENDIENTE") || implicacion.equals("ADMITIDO")){
			
			String nextJSP = "administrarViajeSolicitante.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			
			try {

				dispatcher.forward(request,response);
			} catch (ServletException | IOException e) {
				
				Log.error("Hubo un error al continuar en página JSP de "
						+ "administración de viajes por el solicitante");
			}

		}
		
		else if(implicacion.equals("PROMOTOR")){
			
			String nextJSP = "/administrarViajePromotor.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextJSP);
			
			try {
				
				dispatcher.forward(request,response);
			} catch (ServletException | IOException e) {
				
				
				Log.error("Hubo un error al continuar en página JSP de "
						+ "administración de viajes por el promotor");
				
			}
			
		}
		
		else{
			
			
			Log.info("Sobre el viaje [%s] con implicacion [%s] no se puede "
					+ "realizar ninguna operación",idViaje,implicacion);
			
			
			
			
		}
		
		return "EXITO";
		
	}
	
	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
