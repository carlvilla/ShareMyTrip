<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp" %>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>

<title>ShareMyTrip - Listado de viajes</title>
</head>

<body>
	<table border="1" align="center">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Plazas libres</th>
			</tr>
		<c:forEach var="entry" items="${listaViajes}" varStatus="i">
			<tr id="item_${i.index}">
				<td><a href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
				<td>${entry.departure.city}</td>
				<td>${entry.destination.city}</td>
				<td>${entry.availablePax}</td>
			</tr>
		</c:forEach>
		
		
	</table>
</body>
</html>