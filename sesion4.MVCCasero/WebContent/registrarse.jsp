<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShareMyTrip - Registrarse</title>
</head>
<body>
	<form action="registrarse" method="post">

 	<center><h1>Registrarse</h1></center>
 	<hr><br>
 	<table align="center">
    	<tr> 
	    	<td><input type="text" name="nombreUsuario" placeholder="Nombre" align="left" size="20"></td>
	    	<td><input type="text" name="apellidosUsuario" placeholder="Apellidos" align="left" size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="text" name="email" placeholder="Email" align="left" size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="text" name="loginUsuario" placeholder="Nombre Usuario" align="left" size="20"></td>
      	</tr>
      	<tr> 
	    	<td><input type="password" name="passwordUsuario" placeholder="Contraseņa" align="left" size="20"></td>
      	</tr>
      	<tr> 
      		<td><input type="password" name="confirmPasswordUsuario" placeholder="Confirma tu contraseņa" align="left" size="20"></td>
      	</tr>
      	<tr>
    	    <td><input type="submit"  value="Registrarse"/></td>
      	</tr>
      </table>
   </form>
<c:if test="${error != null}">
   		<p align="center"><c:out value="${error}"/></p>
   </c:if>
</body>
</html>