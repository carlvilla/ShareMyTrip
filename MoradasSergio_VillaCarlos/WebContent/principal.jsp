<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ taglib uri="http://www.uniovi.es/sdi/showcomments" prefix="sc" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/principal.css" type="text/css"></link>
<title>ShareMyTrip - Página principal del usuario</title>
</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />

	<center><h2>Bienvenido/a <jsp:getProperty property="name" name="user" />!!</h2></center>
	
	<sc:showComments idUsuario="${user.id}"/>
	
	<c:if test="${notificacionRegistro==true}">
		<div id="exitoRegistroViaje" class="alert alert-success">
			<strong>Éxito!</strong> Se ha añadido el viaje con éxito.
		</div>

	</c:if>
	
</body>
</html>
