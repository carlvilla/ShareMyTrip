<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuarioRegistrarse.jsp" %>

<!DOCTYPE html>
<html>
<head>
<style>
h1 {
	text-align: center;
}
</style>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShareMyTrip - Registrarse</title>
<link rel="stylesheet" href="css/registrarse.css" type="text/css"></link>

</head>
<body>
	<form action="registrarse" method="post">

	<p>¿Ya tienes una cuenta?</p><a href="login.jsp">Iniciar sesión</a>
 	<h1>Registrarse</h1>
 	
 	<hr><br>
 	<table>
    	<tr> 
	    	<td><input type="text" name="nombreUsuario" placeholder="Nombre" size="20"></td>
	    	<td><input type="text" name="apellidosUsuario" placeholder="Apellidos"size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="text" name="email" placeholder="Email"size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="text" name="loginUsuario" placeholder="Nombre Usuario" size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="password" name="passwordUsuario" placeholder="Contraseña"  size="20"></td>
      	</tr>
      	<tr> 
      		<td><input type="password" name="confirmPasswordUsuario" placeholder="Confirma tu contraseña"  size="20"></td>
      	</tr>
      	<tr>
    	    <td><input type="submit"  value="Registrarse"/></td>
      	</tr>
      </table>
   </form>
	<c:if test="${error != null}">
   		<p align="center"><c:out value="${error}"/></p>
   </c:if>
   
   <br/>
   
   
   <p>
   	¡Registrate hoy y disfruta de todas las ventajas! 
   </p>
   
   <ul>
   	<li>Acceso a toda la información de los viajes</li>
   	<li>Alquilar plazas en viajes</li>
   	<li>...</li>
   
   </ul>
   
</body>
</html>