package uo.sdi.tests;

import static net.sourceforge.jwebunit.junit.JWebUnit.assertElementNotPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertElementPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertLinkPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextInElement;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTextPresent;
import static net.sourceforge.jwebunit.junit.JWebUnit.assertTitleEquals;
import static net.sourceforge.jwebunit.junit.JWebUnit.beginAt;
import static net.sourceforge.jwebunit.junit.JWebUnit.clickLink;
import static net.sourceforge.jwebunit.junit.JWebUnit.setBaseUrl;
import static net.sourceforge.jwebunit.junit.JWebUnit.setTextField;
import static net.sourceforge.jwebunit.junit.JWebUnit.submit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Before;
import org.junit.Test;

import uo.sdi.persistence.util.Jdbc;

/**
 * Estos tests deben de ejecutarse con la base de datos inicializada con los
 * datos proporcionados en el fichero Script.sql. Los tests de comprobación de
 * viajes en el listado funcionarán hasta 2020, ya que se pasa la fecha de
 * cierre de las inscripciones a los viajes.
 * 
 * @author Sergio Moradas y Carlos Villa
 * 
 */
public class Sesion4Tests {

	@Before
	public void prepare() throws SQLException {
		setBaseUrl("http://localhost:8280/MoradasSergio_VillaCarlos");
		executeDBScripts();
	}

	public boolean executeDBScripts() throws SQLException {

		Connection conn = Jdbc.getCurrentConnection();

		Statement stmt = conn.createStatement();

		boolean isScriptExecuted = false;
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"src/Script.sql"));
			String str;
			StringBuffer sb = new StringBuffer();
			while ((str = in.readLine()) != null) {
				sb.append(str + "\n ");
			}
			in.close();
			stmt.executeUpdate(sb.toString());
			isScriptExecuted = true;
		} catch (Exception e) {
			System.err.println("Fallo al ejecutar Script.sql");
		}
		return isScriptExecuted;
	}

	@Test
	public void registrarseSinExito() {
		beginAt("/registrarse.jsp");

		// Rellenar información formulario
		setTextField("nombreUsuario", "usuario10"); // Rellenar primer campo de
													// formulario
		setTextField("apellidosUsuario", "usuario10");
		setTextField("email", "usuario10@uniovi.es");
		setTextField("loginUsuario", "usuario10");
		setTextField("passwordUsuario", "usuario10");
		setTextField("confirmPasswordUsuario", "PasswordNoCoincide");

		submit();

		// La contraseña introducida doblemente no coincide
		assertTextPresent("Error al registrarse: las contraseñas no coinciden");

	}

	@Test
	public void testListarViajesUsuarioRegistrado() {
		beginAt("/"); // Navegar a la URL
		assertLinkPresent("listarViajes"); // Comprobar que existe el
											// hipervínculo
		
		setTextField("nombreUsuario", "usuario1");
		setTextField("passwordUsuario", "usuario1");
		submit();
		
		clickLink("listarViajes"); // Seguir el hipervínculo

		assertTitleEquals("ShareMyTrip - Listado de viajes"); // Comprobar
																// título de la
																// página

		// La base de datos contiene 9 viajes que puede 
		//ver el usuario1 tal y como se entrega
		
		assertElementPresent("item_0"); // Comprobar elemento presente en la
										// página
		assertElementPresent("item_1");
		assertElementPresent("item_2");
		assertElementPresent("item_3");
		assertElementPresent("item_4");
		assertElementPresent("item_5");
		assertElementPresent("item_6");
		assertElementPresent("item_7");
		assertElementPresent("item_8");
		assertElementNotPresent("item_9");
	}

	@Test
	public void testListarViajesUsuarioPublico() {
		beginAt("/"); // Navegar a la URL
		assertLinkPresent("listarViajes"); // Comprobar que existe el
											// hipervínculo
		clickLink("listarViajes"); // Seguir el hipervínculo

		assertTitleEquals("ShareMyTrip - Listado de viajes"); // Comprobar
																// título de la
																// página

		// La base de datos contiene 10 viajes tal y como se entrega
		assertElementPresent("item_0"); // Comprobar elemento presente en la
										// página
		assertElementPresent("item_1"); 
		assertElementPresent("item_2"); 
		assertElementPresent("item_3"); 
		assertElementPresent("item_4"); 
		assertElementPresent("item_5"); 
		assertElementPresent("item_6"); 
		assertElementPresent("item_7"); 
		assertElementPresent("item_8"); 
		assertElementPresent("item_9"); 
		assertElementNotPresent("item_10");


	}

	@Test
	public void testIniciarSesionConExito() {
		// Rellenando el formulario HTML
		beginAt("/");
		setTextField("nombreUsuario", "user1"); // Rellenar primer campo de
												// formulario
		setTextField("passwordUsuario", "user1"); // Introducir contraseña
		submit(); // Enviar formulario
		assertTitleEquals("ShareMyTrip - Página principal del usuario"); // Comprobar
																			// título
																			// de
																			// la
																			// página
		assertTextPresent("Bienvenido/a Fernando!!");

	}

	@Test
	public void testIniciarSesionConExitoConQueryString() {

		beginAt("/validarse?nombreUsuario=user2&passwordUsuario=user2");
		assertTitleEquals("ShareMyTrip - Página principal del usuario");

		assertTextPresent("Bienvenido/a Luisa!!"); //Se muestra el mensaje de 'Bienvenido'
												 //con el nombre del usuario, en 
												 //este caso se llama 'Luisa'

	}

	@Test
	public void testIniciarSesionSinExitoPassInconrrecta() {
		beginAt("/");
		setTextField("nombreUsuario", "usuario1");
		setTextField("passwordUsuario", "incorrecta");

		submit();
		assertTitleEquals("ShareMyTrip - Inicie sesión");
	}

	@Test
	public void testIniciarSesionSinExitoNoExiste() {

		beginAt("/");
		setTextField("nombreUsuario", "noExiste");
		setTextField("passwordUsuario", "noExiste");

		submit();
		assertTitleEquals("ShareMyTrip - Inicie sesión");
	}

	@Test
	public void registrarViajeNuevo() {

		// Iniciar sesión
		beginAt("/");
		setTextField("nombreUsuario", "user1");
		setTextField("passwordUsuario", "user1");
		submit();
		assertTitleEquals("ShareMyTrip - Página principal del usuario");

		// Nos dirigimos a la página de registro de viajes
		clickLink("misViajes");
		clickLink("registrarViaje");

		// Rellenar formulario
		setTextField("calleSalida", "calle");
		setTextField("ciudadSalida", "Oviedo");
		setTextField("provinciaSalida", "Asturias");
		setTextField("paisSalida", "Spain");
		setTextField("postalSalida", "33234");
		setTextField("longitudSalida", "47.3");
		setTextField("latitudSalida", "47.3");

		setTextField("calleDestino", "Calle");
		setTextField("ciudadDestino", "Bilbao");
		setTextField("provinciaDestino", "Vizcaya");
		setTextField("paisDestino", "Spain");
		setTextField("postalDestino", "77556");
		setTextField("longitudDestino", "33.45");
		setTextField("latitudDestino", "34.3");

		setTextField("fechaHoraSalida", "10/10/2016-10:00:00");
		setTextField("fechaHoraLLegada", "10/10/2016-15:00:00");
		setTextField("fechaLimite", "08/10/2016-21:00:00");
		setTextField("costeViaje", "15");
		setTextField("descripcionViaje", "Buen rollo");
		setTextField("numeroMaxPlazas", "5");
		setTextField("numeroDispPlazas", "4");

		submit();

		// Aparece un mensaje diciendo que se ha añadido correctamente
		// el viaje
		assertElementPresent("exitoRegistroViaje");

		// Si cambiamos de pestaña y volvemos a la principal, el mensaje
		// de alerta habrá desaparecido
		clickLink("misViajes");
		clickLink("principal");

		assertElementNotPresent("exitoRegistroViaje");

	}

	@Test
	public void cerrarSesion() {

		// Iniciar sesión
		beginAt("/");
		setTextField("nombreUsuario", "usuario1");
		setTextField("passwordUsuario", "usuario1");
		submit();
		assertTitleEquals("ShareMyTrip - Página principal del usuario");
		
		assertTextPresent("Bienvenido/a usuario1!!");

		// Cerrar sesión
		clickLink("cerrarSesion");

		assertTitleEquals("ShareMyTrip - Inicie sesión");

		// Iniciar sesión
		setTextField("nombreUsuario", "usuario3");
		setTextField("passwordUsuario", "usuario3");
		submit();
		assertTitleEquals("ShareMyTrip - Página principal del usuario");
		assertTextPresent("Bienvenido/a usuario3!!");


	}

	@Test
	public void usuarioNoPromotorSolicitaPlaza() {

		// Iniciar sesión
		beginAt("/");
		setTextField("nombreUsuario", "usuario1");
		setTextField("passwordUsuario", "usuario1");
		submit();
		assertTitleEquals("ShareMyTrip - Página principal del usuario");

		// Nos dirigimos a la página de listado de viajes
		clickLink("listarViajes");

		// Seleccionamos un viaje y enviamos solicitud
		clickLink("item_0");
		assertTitleEquals("ShareMyTrip - Datos del viaje");
		submit();

		// Nos dirigimos a la página de mis viajes
		clickLink("misViajes");
		assertTitleEquals("ShareMyTrip - Mis viajes");

		// Se añadió el viaje al que se le solicitó la plaza
		assertElementPresent("item_0");
		assertTextPresent("PENDIENTE");

		// Solo hay un viaje en la página de mis viajes
		assertElementNotPresent("item_1");

	}

	@Test
	public void usuarioNoPromotorCancelaPlaza() {

		// Iniciar sesión
		beginAt("/");
		setTextField("nombreUsuario", "usuario1");
		setTextField("passwordUsuario", "usuario1");
		submit();
		assertTitleEquals("ShareMyTrip - Página principal del usuario");

		// Nos dirigimos a la página de listado de viajes
		clickLink("listarViajes");

		// Seleccionamos un viaje y enviamos solicitud
		clickLink("item_0");
		assertTitleEquals("ShareMyTrip - Datos del viaje");
		submit();

		// Nos dirigimos a la página de mis viajes
		clickLink("misViajes");
		assertTitleEquals("ShareMyTrip - Mis viajes");

		// Se añadió el viaje al que se le solicitó la plaza
		assertElementPresent("item_0");
		assertTextPresent("PENDIENTE");

		// Solo hay un viaje en la página de mis viajes
		assertElementNotPresent("item_1");

		// Cancelamos la plaza
		clickLink("item_CancelaPlaza_0");

		// Ya no existe la entrada de la petición
		assertElementNotPresent("item_0");

	}

}