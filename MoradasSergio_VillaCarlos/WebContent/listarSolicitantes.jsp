<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Listado de solicitantes</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

</head>

<body>

	<center><h2>Solicitantes de plaza</h2></center>
	
	<br/>
	
	
	<div class="container">
		<table id="tabla" class="table table-hover">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Email</th>
				<th>Administrar</th>
				
			</tr>
			<c:forEach var="user" items="${solicitantes}" varStatus="i">
				<tr id="item_${i.index}">
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.email}</td>
					
					
					<c:choose>
					<c:when test="${viaje.availablePax > 0}">
					<td>
						<ul>
							<li><a href="administrarSolicitud?decision=aceptada&idViaje=${param.idViaje}&idUsuario=${user.id}">Aceptar solicitud</a></li>
							<li><a href="administrarSolicitud?decision=cancelada&idViaje=${param.idViaje}&idUsuario=${user.id}">Cancelar solicitud</a></li>			
						</ul>
					</td>
					</c:when>
					<c:otherwise>
					
					<td id="sinPlaza">
						No se puede administrar la solicitud porque no quedan plazas
					</td>
					</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
	<center><h2>Pasajeros confirmados</h2></center>
	
	<br/>
	
	<div class="container">
		<table id="tabla" class="table table-hover">
			<tr>
				<th>Nombre</th>
				<th>Apellidos</th>
				<th>Email</th>
				
			</tr>
			<c:forEach var="user" items="${confirmados}" varStatus="i">
				<tr id="item_${i.index}">
					<td>${user.name}</td>
					<td>${user.surname}</td>
					<td>${user.email}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	
	
</body>
</html>