<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Datos del viaje</title>
</head>
<body>

	<h1>Datos del viaje</h1>
		
	<jsp:useBean id="viaje" class="uo.sdi.model.Trip" scope="request" />
	<jsp:useBean id="promotor" class="uo.sdi.model.User" scope="request" />
	
	<h2>Datos del promotor</h2>
	
	<h3>Información de contacto</h3>
	
		<table>

		<tr>
			<td>Nombre:</td><td id="name">
			<input type="text" name="nombre" size="15"
						value="<jsp:getProperty property="name" name="promotor"/>"> 
						</td>
		</tr>
		<tr>
			<td>Apellidos:</td><td id="surname">
			<input type="text" name="apellidos" size="15"
						value="<jsp:getProperty property="surname" name="promotor"/>">
			</td> 
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" size="15"
						value="<jsp:getProperty property="email" name="promotor"/>" />
						</td>		
		</tr>
				
	</table>
	
	<h3>Puntuación y comentarios del promotor</h3>
	
	
	
		
</body>
</html>
