<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Registrar Viaje</title>
</head>
<body>

	<h1 align="center">Datos del viaje</h1>
		
<form action="modificarViaje" method="POST">				
	<div class="col-sm-4">
	<table>
	<caption style="text-align: center;font-size: 2rem">Lugar de Salida</caption>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="calleSalida"  
				value="${viajeModificar.departure.address}" align="left" size="20"></td>
			<td><input class="form-control form-control-sm" type="text" name="ciudadSalida" 
				value="${viajeModificar.departure.city}" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="provinciaSalida" 
				value="${viajeModificar.departure.state}" align="left" size="20"></td>
			<td><input class="form-control form-control-sm" type="text" name="paisSalida" 
				value="${viajeModificar.departure.country}" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="postalSalida" 
				value="${viajeModificar.departure.zipCode}" align="left" size="20"></td>
		</tr>
		<tr>
		<td><br></td>
		</tr>
		<tr>
			<td>Coordenadas GPS:</td>		
		</tr>	
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="longitudSalida" 
				value="${viajeModificar.departure.waypoint.lon}" align="left" size="20"></td>		
			<td><input class="form-control form-control-sm" type="text" name="latitudSalida" 
				value="${viajeModificar.departure.waypoint.lat}" align="left" size="20"></td>			
		</tr>	
	</table>
	</div>
	<div class="col-sm-4">
	<table>
	<caption style="text-align: center;font-size: 2rem">Lugar de Destino</caption>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="calleDestino"  
				value="${viajeModificar.destination.address}" align="left" size="20"></td>
			<td><input class="form-control form-control-sm" type="text" name="ciudadDestino" 
				value="${viajeModificar.destination.city}" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="provinciaDestino" 
				value="${viajeModificar.destination.state}" align="left" size="20"></td>
			<td><input class="form-control form-control-sm" type="text" name="paisDestino" 
				value="${viajeModificar.destination.country}" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="postalDestino" 
				value="${viajeModificar.destination.zipCode}" align="left" size="20"></td>
		</tr>
		<tr>
		<td><br></td>
		</tr>
		<tr>
			<td>Coordenadas GPS:</td>		
		</tr>	
		<tr>
			<td><input class="form-control form-control-sm" type="text" name="longitudDestino" 
				value="${viajeModificar.destination.waypoint.lon}" align="left" size="20"></td>		
			<td><input class="form-control form-control-sm" type="text" name="latitudDestino" 
				value="${viajeModificar.destination.waypoint.lat}" align="left" size="20"></td>			
		</tr>	
		
		
	</table>
	</div>
	<div class="col-sm-4">
	<table>
	<caption style="text-align: center;font-size: 2rem">Otros datos del viaje</caption>
		<tr>
			<td><p>Fecha y Hora Salida:</p></td>
			<td><input class="form-control form-control-sm" type="text" name="fechaHoraSalida" 
				value="${viajeModificar.departureDate}" align="left" size="18"></td>
		</tr>
		<tr>
			<td>Fecha y Hora LLegada:</td>
			<td><input class="form-control form-control-sm" type="text" name="fechaHoraLLegada" 
				value="${viajeModificar.arrivalDate}" align="left" size="18"></td>
		</tr>
		<tr>
			<td>Fecha limite para apuntarse:</td>
			<td><input  class="form-control form-control-sm" type="text" name="fechaLimite"  
				value="${viajeModificar.closingDate}" align="left" size="18"></td>
		</tr>
		<tr>
			<td>Coste Viaje:</td>
			<td><input class="form-control form-control-sm" type="text" name="costeViaje" 
				value="${viajeModificar.estimatedCost}" align="left" size="18"></td>		
		</tr>	
		<tr>
			<td>Descripción del Viaje</td>		
			<td><input class="form-control form-control-sm" type="text" name="descripcionViaje"
				value="${viajeModificar.comments}" align="left" size="18" ></td>			
		</tr>	
		<tr>
			<td>Nº maximo de plazas</td>		
			<td><input class="form-control form-control-sm" type="text" name="numeroMaxPlazas"
				value="${viajeModificar.maxPax}" align="left" size="18" ></td>			
		</tr>	
		<tr>
			<td>Nº plazas disponibles</td>		
			<td><input class="form-control form-control-sm" type="text" name="numeroDispPlazas"
				value="${viajeModificar.availablePax}" align="left" size="18" ></td>			
		</tr>
		<tr>
		<td><br></td>
		</tr>	
		<tr>
		<td><input class="btn btn-primary btn-lg btn-block" type="submit" value="Modificar Viaje"/></td>
		</tr>
		<tr>
		<td><br></td>
		</tr>	
		<tr>
		<c:if test="${error != null}">
			<td><img src="resources/error.png" align="right"></td>
   			<td><p align="center"><c:out value="${error}"/></p></td>
   		</c:if>	
   		</tr>
	</table>
	</div>
		


	</form>
		
</body>
</html>