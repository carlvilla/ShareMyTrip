<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Datos del viaje</title>
</head>
<body>

	<h1>Datos del viaje</h1>
		
	<jsp:useBean id="viaje" class="uo.sdi.model.Trip" scope="request" />
	
	<form action="solicitarPlaza" >
	
		<input type="hidden" name="idViaje" value="${viaje.id}">
	
	<table>

		<tr>
			<td>Origen: </td><td>${viaje.departure.city}</td>
		</tr>
		<tr>
			<td>Destino: </td><td>${viaje.destination.city}</td>
		</tr>
		<tr>
			<td>Fecha salida: </td>
			<td><jsp:getProperty property="departureDate" name="viaje"/></td>		
		</tr>
		
		<tr>
			<td>Fecha llegada: </td>
			<td><jsp:getProperty property="arrivalDate" name="viaje"/></td>		
		</tr>
		
		
		<tr>
			<td>Precio estimado: </td>
			<td><jsp:getProperty property="estimatedCost" name="viaje"/> €</td>		
		</tr>
		
		<tr>
			<td>Asientos del vehiculo: </td>
			<td><jsp:getProperty property="maxPax" name="viaje"/></td>		
		</tr>
				
		<tr>
			<td>Asientos libres: </td>
			<td><jsp:getProperty property="availablePax" name="viaje"/></td>		
		</tr>
				
		
		<tr>
			<td>Comentario: </td>
			<td><jsp:getProperty property="comments" name="viaje"/></td>		
		</tr>
		
		<tr>
			<td>Fecha cierre inscripción: </td>
			<td><jsp:getProperty property="closingDate" name="viaje"/></td>		
		</tr>
				
		</table>
	
	<br/>
	
	<a href="informacionPromotor?id=${promotorId}">Datos del promotor</a>	

	<br/><br/>

	<input type="submit" value="Solicitar plaza"/>
	
	
	</form>
			
	

		
</body>
</html>
