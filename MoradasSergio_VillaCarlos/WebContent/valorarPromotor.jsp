<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp" %>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Valorar Promotor</title>
</head>
<body>
	<h1 align="center">Valorar Promotor</h1>
	<form action="valorarPromotor?idViaje=${param.idViaje}" method="POST">
	<table id="tabla" class="table table-hover">
	<thead>
				<tr>
					<%-- <th>Compañero</th>--%>
					<th>Comentario</th>
					<th>Valoracion</th>
				</tr>
			</thead>
				<tr>
					<td><textarea rows="4" cols="50" name="comentario">Introduzca su comentario...</textarea></td>
					<td><input type="number" name="valoracion" value="0" min="0" max="10"></td>
				</tr>
	</table>
		<input type="submit" value="Valorar"/>
	</form>				
</body>
</html>
