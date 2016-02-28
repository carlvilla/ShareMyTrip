<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	<table>
		<tr>
			<td>Login:</td>
			<td id="login"><jsp:getProperty property="login" name="user" /></td>
		</tr>
		<tr>
			<td>Nombre:</td>
			<td id="name"><jsp:getProperty property="name" name="user" /></td>
		</tr>
		<tr>
			<td>Apellidos:</td>
			<td id="surname"><jsp:getProperty property="surname" name="user" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td id="email"><jsp:getProperty property="email" name="user" />
			</td>
		</tr>
	</table>
	<c:if test="${notificacionRegistro==true}">
		<div id="exitoRegistroViaje" class="alert alert-success">
			<strong>Éxito!</strong> Se ha añadido el viaje con éxito.
		</div>

	</c:if>
</body>
</html>
