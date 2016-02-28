<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user!=null}">


<head>

<link rel="stylesheet" href="css/barraNavegacionCss.css" type="text/css"></link>

</head>
<nav>

        <ul class="menu">  
            <li><a id="principal" href="principal.jsp">Home</a></li>  
            <li><a id="modificarDatos" href="modificarDatos.jsp">Modificar datos</a></li>  
            <li><a id="listarViajes" href="listarViajes">Lista de viajes</a></li>
             <li><a id="misViajes" href="misViajes">Mis viajes</a></li> 
            <li><a id="cerrarSesion" href="cerrarSesion">Cerrar sesión</a></li> 
        </ul>    
        
        
</nav>

</c:if>