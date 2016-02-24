<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Registrar Viaje</title>
</head>
<body>

	<h1 align="center">Datos del viaje</h1>
		
<form action="registrarViaje" method="POST">				

	<table style="float:left">
	<caption>Lugar de Salida</caption>
		<tr>
			<td><input type="text" name="calleSalida"  placeholder="Calle" align="left" size="20"></td>
			<td><input type="text" name="ciudadSalida" placeholder="Ciudad" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input type="text" name="provinciaSalida" placeholder="Provincia" align="left" size="20"></td>
			<td><input type="text" name="paisSalida" placeholder="Pais" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input type="text" name="postalSalida" placeholder="Codigo Postal" align="left" size="20"></td>
		</tr>
		<tr>
			<td>Coordenadas GPS:</td>		
		</tr>	
		<tr>
			<td><input type="text" name="longitudSalida" placeholder="Longitud" align="left" size="20"></td>		
			<td><input type="text" name="latitudSalida" placeholder="Latitud" align="left" size="20"></td>			
		</tr>	
		
		
	</table>
	
	<table>
	<caption>Lugar de Destino</caption>
		<tr>
			<td><input type="text" name="calleDestino"  placeholder="Calle" align="left" size="20"></td>
			<td><input type="text" name="ciudadDestino" placeholder="Ciudad" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input type="text" name="provinciaDestino" placeholder="Provincia" align="left" size="20"></td>
			<td><input type="text" name="paisDestino" placeholder="Pais" align="left" size="20"></td>
		</tr>
		<tr>
			<td><input type="text" name="postalDestino" placeholder="Codigo Postal" align="left" size="20"></td>
		</tr>
		<tr>
			<td>Coordenadas GPS:</td>		
		</tr>	
		<tr>
			<td><input type="text" name="longitudDestino" placeholder="Longitud" align="left" size="20"></td>		
			<td><input type="text" name="latitudDestino" placeholder="Latitud" align="left" size="20"></td>			
		</tr>	
		
		
	</table>
	
	<table>
	<caption>Otros datos del viaje</caption>
		<tr>
			<td>Fecha Salida:</td>
			<td><input type="text" name="fechaSalida" align="left" size="15"></td>
		</tr>
		<tr>
			<td>Hora:</td>
			<td><input type="text" name="horaSalida" align="left" size="15"></td>
		</tr>
		<tr>
			<td>Fecha limite para apuntarse:</td>
			<td><input type="text" name="fechaLimite" align="left" size="15"></td>
		</tr>
		<tr>
			<td>Coste Viaje:</td>
			<td><input type="text" name="costeViaje"align="left" size="15"></td>		
		</tr>	
		<tr>
			<td>Descripción del Viaje</td>		
			<td><input type="text" name="descripcionViaje"align="left" size="15" ></td>			
		</tr>	
		<tr>
			<td>Nº maximo de plazas</td>		
			<td><input type="text" name="numeroMaxPlazas"align="left" size="15" ></td>			
		</tr>	
		<tr>
			<td>Nº plazas disponibles</td>		
			<td><input type="text" name="numeroDispPlazas"align="left" size="15" ></td>			
		</tr>	
			
	</table>
		
		<input type="submit" value="Registrar Viaje"/>

	</form>
		
</body>
</html>