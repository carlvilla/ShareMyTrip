<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Datos del viaje</title>
</head>
<body>

	<h1>Datos del viaje</h1>
		
	<jsp:useBean id="viaje" class="uo.sdi.model.Trip" scope="request" />
	<jsp:useBean id="promotor" class="uo.sdi.model.User" scope="request" />
	
	<form action="solicitarPlaza" >
	
		<input type="hidden" name="idViaje" value="${viaje.id}">
	
	<table>

		<tr>
			<td>Origen:</td><td>${viaje.departure.city}</td>
		</tr>
		<tr>
			<td>Destino:</td><td>${viaje.destination.city}</td>
		</tr>
		<tr>
			<td>Fecha salida:</td>
			<td><jsp:getProperty property="departureDate" name="viaje"/></td>		
		</tr>
		
		<tr>
			<td>Fecha llegada:</td>
			<td><jsp:getProperty property="arrivalDate" name="viaje"/></td>		
		</tr>
		
		
		<tr>
			<td>Precio estimado:</td>
			<td><jsp:getProperty property="estimatedCost" name="viaje"/> €</td>		
		</tr>
		
		<tr>
			<td>Comentario:</td>
			<td><jsp:getProperty property="comments" name="viaje"/></td>		
		</tr>
				
		</table>
	
	<br/>
	
	<a href="informacionPromotor?id=${promotor.id}">Datos del promotor</a>	

	<br/><br/>

	<input type="submit" value="Solicitar plaza"/>
	
	
	</form>
			
	

		
</body>
</html>
