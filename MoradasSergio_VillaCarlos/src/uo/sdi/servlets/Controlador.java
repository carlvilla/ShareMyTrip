package uo.sdi.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import alb.util.log.Log;
import uo.sdi.acciones.*;

public class Controlador extends javax.servlet.http.HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private Map<String, Map<String, Accion>> mapaDeAcciones; // <rol, <opcion, objeto Accion>>
	private Map<String, Map<String, Map<String, String>>> mapaDeNavegacion; // <rol, <opcion, <resultado, JSP>>>

	public void init() throws ServletException {  
		crearMapaAcciones();
		crearMapaDeJSP();
		
    }
	

	public void doGet(HttpServletRequest req, HttpServletResponse res)
				throws IOException, ServletException {
		
		String opcion = null, resultado, jspSiguiente;
		Accion accion;
		String rolAntes, rolDespues;
		
		try {
			opcion=req.getServletPath().replace("/","");
				
			rolAntes=obtenerRolDeSesion(req);
			
			accion=buscarAccionParaOpcion(rolAntes, opcion);
				
			resultado=accion.execute(req,res);
				
			rolDespues=obtenerRolDeSesion(req);
			
			jspSiguiente=buscarJSPSegun(rolDespues, opcion, resultado);

			req.setAttribute("jspSiguiente", jspSiguiente);

		} catch(Exception e) {
			
			req.getSession().invalidate();
			
			Log.error("Se ha producido alguna excepción no manejada [%s]",e);
			
			//Si selecciona un viaje para ver su información y es un 
			//usuario "PUBLICO" se le redirige a la JSP para registrarse
			if(opcion!=null && opcion.equals("mostrarViaje")){
				jspSiguiente="/registrarse.jsp";
			}
			
			else{
			jspSiguiente="/login.jsp";
		
			}
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(jspSiguiente); 
			
		dispatcher.forward(req, res);			
	}			
	
	
	private String obtenerRolDeSesion(HttpServletRequest req) {
		HttpSession sesion=req.getSession();
		if (sesion.getAttribute("user")==null)
			return "PUBLICO";
		else
			return "REGISTRADO";
	}

	// Obtiene un objeto accion en funci�n de la opci�n
	// enviada desde el navegador
	private Accion buscarAccionParaOpcion(String rol, String opcion) {
		
		Accion accion=mapaDeAcciones.get(rol).get(opcion);
		Log.debug("Elegida acción [%s] para opción [%s] y rol [%s]",accion,opcion,rol);
		return accion;
	}
	
	
	// Obtiene la p�gina JSP a la que habr� que entregar el
	// control el funci�n de la opci�n enviada desde el navegador
	// y el resultado de la ejecuci�n de la acci�n asociada
	private String buscarJSPSegun(String rol, String opcion, String resultado) {
		
		String jspSiguiente=mapaDeNavegacion.get(rol).get(opcion).get(resultado);
		Log.debug("Elegida página siguiente [%s] para el resultado [%s] tras realizar [%s] con rol [%s]",jspSiguiente,resultado,opcion,rol);
		return jspSiguiente;		
	}
		
		
	private void crearMapaAcciones() {
		
		mapaDeAcciones=new HashMap<String,Map<String,Accion>>();
		
		Map<String,Accion> mapaPublico=new HashMap<String,Accion>();
		mapaPublico.put("validarse", new ValidarseAction());
		mapaPublico.put("listarViajes", new ListarViajesAction());
		mapaPublico.put("registrarse", new RegistrarseAction());
		mapaDeAcciones.put("PUBLICO", mapaPublico);
		
		Map<String,Accion> mapaRegistrado=new HashMap<String,Accion>();
		mapaRegistrado.put("modificarDatos", new ModificarDatosAction());
		mapaRegistrado.put("listarViajes", new ListarViajesAction());
		mapaRegistrado.put("mostrarViaje", new MostrarDatosViajeAction());
		mapaRegistrado.put("infoPromotorParticipantes", new InfoPromotorParticipantesAction());
		mapaRegistrado.put("cerrarSesion", new CerrarSesionAction());
		mapaRegistrado.put("registrarViaje", new RegistrarViajeAction());
		mapaRegistrado.put("mostrarDatosViaje", new MostrarDatosModificarViajeAction());
		mapaRegistrado.put("modificarViaje", new ModificarViajeAction());
		mapaRegistrado.put("cancelarViaje",new CancelarViajeAction());
		mapaRegistrado.put("misViajes", new ListarMisViajesAction());
		mapaRegistrado.put("solicitarPlaza", new SolicitarPlazaAction());
		mapaRegistrado.put("cancelarPlaza", new CancelarPlazaAction());
		mapaRegistrado.put("listarSolicitudes", new ListarSolicitudesAction());
		mapaRegistrado.put("administrarSolicitud", new AdministrarSolicitudAction());
		mapaRegistrado.put("historialViajes", new MostrarHistorialViajesAction());
		mapaRegistrado.put("valorarViaje", new ValorarViajeAction());
		mapaRegistrado.put("valorarPartners", new ValorarPartnersAction());
		mapaRegistrado.put("valorarPromotor", new ValorarPromotorAction());
		mapaRegistrado.put("informacionPartners", new InformacionPartnersViajeAction());
		mapaDeAcciones.put("REGISTRADO", mapaRegistrado);
	}
	
	
	private void crearMapaDeJSP() {
				
		mapaDeNavegacion=new HashMap<String,Map<String, Map<String, String>>>();

		// Crear mapas auxiliares vacíos
		Map<String, Map<String, String>> opcionResJSP=new HashMap<String, Map<String, String>>();
		Map<String, String> resJSP=new HashMap<String, String>();

		// Mapa de navegación del público
		resJSP.put("FRACASO","/login.jsp");
		opcionResJSP.put("validarse", resJSP);
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);
		
		//Registrarse
		resJSP=new HashMap<String, String>();
		resJSP.put("FRACASO","/registrarse.jsp");
		opcionResJSP.put("registrarse", resJSP);
		
		//Cerrar Sesion
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/login.jsp");
		opcionResJSP.put("cerrarSesion", resJSP);
		
		
		mapaDeNavegacion.put("PUBLICO",opcionResJSP);
		
		
		// Crear mapas auxiliares vacíos
		opcionResJSP=new HashMap<String, Map<String, String>>();
		resJSP=new HashMap<String, String>();
		
		// Mapa de navegación de usuarios registrados
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("validarse", resJSP);
		
		//Registrarse
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		opcionResJSP.put("registrarse", resJSP);
	
		//Modificar datos
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("FRACASO","/modificarDatos.jsp");
		opcionResJSP.put("modificarDatos", resJSP);
		
		//Listar viajes
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listaViajes.jsp");
		opcionResJSP.put("listarViajes", resJSP);
		
		//Datos viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/datosViaje.jsp");
		opcionResJSP.put("mostrarViaje", resJSP);
				
		//Información Promotor
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/informacionPromotorParticipantes.jsp");
		opcionResJSP.put("infoPromotorParticipantes", resJSP);
		
		
		//Registrar Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/principal.jsp");
		resJSP.put("FRACASO","/registrarViaje.jsp");
		opcionResJSP.put("registrarViaje", resJSP);		
		
		//Mostar Datos Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/modificarViaje.jsp");
		resJSP.put("FRACASO","/misViajes");
		opcionResJSP.put("mostrarDatosViaje", resJSP);
		
		//ModificarViaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/misViajes");
		resJSP.put("FRACASO","/misViajes");
		opcionResJSP.put("modificarViaje", resJSP);
		
		//Cancelar Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/misViajes");
		resJSP.put("FRACASO","/misViajes");
		opcionResJSP.put("cancelarViaje", resJSP);	
		
		//Mis Viajes
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/misViajes.jsp");
		opcionResJSP.put("misViajes", resJSP);		
		
		//Solicitar plaza
		resJSP=new HashMap<String, String>();
		resJSP.put("FRACASO","/sinPlazas.jsp");
		resJSP.put("EXITO","/peticionRealizada.jsp");
		opcionResJSP.put("solicitarPlaza", resJSP);	

		//Cancelar plaza
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/misViajes");
		opcionResJSP.put("cancelarPlaza", resJSP);	
		
		//Listar solicitantes
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listarSolicitantes.jsp");
		opcionResJSP.put("listarSolicitudes", resJSP);	
		
		//Listar solicitantes
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/listarSolicitudes");
		opcionResJSP.put("administrarSolicitud", resJSP);	
		
		//Historial Viajes
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/historialViajes.jsp");
		opcionResJSP.put("historialViajes", resJSP);
		
		//Valorar Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/historialViajes");
		opcionResJSP.put("valorarViaje", resJSP);
		
		//Valorar Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/historialViajes");
		opcionResJSP.put("valorarPartners", resJSP);
		
		//Valorar Viaje
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/historialViajes");
		opcionResJSP.put("valorarPromotor", resJSP);
		
		//Informacion Compañeros
		resJSP=new HashMap<String, String>();
		resJSP.put("EXITO","/valorarPartners.jsp");
		opcionResJSP.put("informacionPartners", resJSP);
		
		mapaDeNavegacion.put("REGISTRADO",opcionResJSP);
	}
			
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {

		doGet(req, res);
	}

}