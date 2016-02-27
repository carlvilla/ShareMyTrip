<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp" %>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Valorar Viaje</title>
</head>
<body>
	<h1 align="center">Valorar Viaje</h1>
	<form action="valorarViaje?idViaje=${param.idViaje}" method="POST">
		<textarea rows="4" cols="50" name="comentario">Introduzca su comentario...</textarea>
		<input type="submit" value="Valorar"/>
	</form>				
</body>
</html>
