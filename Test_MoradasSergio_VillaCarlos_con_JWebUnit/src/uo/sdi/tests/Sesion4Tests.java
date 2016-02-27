package uo.sdi.tests;

import org.junit.*;

import static net.sourceforge.jwebunit.junit.JWebUnit.*;

/**
 * Estos tests deben de ejecutarse con la base de datos inicializada con los
 * datos proporcionados en el fichero Script.sql. Los tests de comprobación
 * de viajes en el listado funcionarán hasta 2020, ya que se pasa la fecha 
 * de cierre de las inscripciones a los viajes.
 * 
 * @author Sergio Moradas y Carlos Villa
 *
 */
public class Sesion4Tests {

    @Before
    public void prepare() {
        setBaseUrl("http://localhost:8280/MoradasSergio_VillaCarlos");
        
    }
    
    @Test
    public void registrarseSinExito(){
    	beginAt("/registrarse.jsp");
    	
    	//Rellenar información formulario
        setTextField("nombreUsuario", "usuario10"); // Rellenar primer campo de formulario
        setTextField("apellidosUsuario", "usuario10");
        setTextField("email", "usuario10@uniovi.es");
        setTextField("loginUsuario", "usuario10");
        setTextField("passwordUsuario", "usuario10");
        setTextField("confirmPasswordUsuario", "PasswordNoCoincide");
   
        submit();
        
        //La contraseña introducida doblemente no coincide
        assertTextPresent("Error al registrarse: las contraseñas no coinciden");
       
    }

    @Test
    public void testListarViajesUsuarioPublico() {
        beginAt("/");  // Navegar a la URL
        assertLinkPresent("listarViajes");  // Comprobar que existe el hipervínculo
        clickLink("listarViajes"); // Seguir el hipervínculo

        assertTitleEquals("ShareMyTrip - Listado de viajes");  // Comprobar título de la página

        // La base de datos contiene 2 viajes tal y como se entrega
        assertElementPresent("item_0"); // Comprobar elemento presente en la página
        assertElementPresent("item_1"); // Comprobar elemento presente en la página
        assertElementPresent("item_2"); // Comprobar elemento presente en la página

    }
    
    @Test
    public void testListarViajesUsuarioRegistrado() {
        beginAt("/");  // Navegar a la URL
        assertLinkPresent("listarViajes");  // Comprobar que existe el hipervínculo
        clickLink("listarViajes"); // Seguir el hipervínculo

        assertTitleEquals("ShareMyTrip - Listado de viajes");  // Comprobar título de la página

        // La base de datos contiene 2 viajes tal y como se entrega
        assertElementPresent("item_0"); // Comprobar elemento presente en la página
        assertElementPresent("item_1"); // Comprobar elemento presente en la página
        assertElementPresent("item_2"); // Comprobar elemento presente en la página

    }

    @Test
    public void testIniciarSesionConExito() {
    	// Rellenando el formulario HTML
    	beginAt("/"); 
        setTextField("nombreUsuario", "user1"); // Rellenar primer campo de formulario
        setTextField("passwordUsuario", "user1"); //Introducir contraseña
        submit(); // Enviar formulario
        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
        assertTextInElement("login", "user1");  // Comprobar cierto elemento contiene cierto texto
        assertTextInElement("name", "Fernando");  // Comprobar cierto elemento contiene cierto texto
    
    }

    @Test
    public void testIniciarSesionConExitoConQueryString() {
    	// Rellenando el formulario HTML
        beginAt("/validarse?nombreUsuario=user2&passwordUsuario=user2");  // Navegar a la URL
        assertTitleEquals("ShareMyTrip - Página principal del usuario");  // Comprobar título de la página
        assertTextInElement("login", "user2");  // Comprobar cierto elemento contiene cierto texto
        assertTextInElement("name", "Luisa");  // Comprobar cierto elemento contiene cierto texto
    }
    
    @Test
    public void testIniciarSesionSinExito() {
    	// Rellenando el formulario HTML
        beginAt("/");  // Navegar a la URL
        setTextField("nombreUsuario", "yoNoExisto"); // Rellenar primer campo de formulario
        submit(); // Enviar formulario
        assertTitleEquals("ShareMyTrip - Inicie sesión");  // Comprobar título de la página
    }
    
    @Test
    public void registrarViajeNuevo(){
    	
    	//Iniciar sesión
    	beginAt("/"); 
        setTextField("nombreUsuario", "user1");
        setTextField("passwordUsuario", "user1"); 
        submit(); 
        assertTitleEquals("ShareMyTrip - Página principal del usuario"); 
        
        //Nos dirigimos a la página de registro de viajes
        clickLink("misViajes");
        clickLink("registrarViaje");
    
    }
    
    
    @Test
    public void cerrarSesion(){
    	
    }

}