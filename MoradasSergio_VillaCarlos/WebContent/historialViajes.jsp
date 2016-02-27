<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html>
<html>
<head>

<title>ShareMyTrip - Historial viajes</title>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>


<style>
h1 {
	text-align: center;
}

li{	
	font-size: 14px
}
td{
	font-size: 14px;
	vertical-align:middle !important;
}
</style>


</head>
<body>

	<h1>Historial Viajes</h1>



	<div class="container">

		<table id="tabla" class="table table-hover">
			<thead>
				<tr>
					<%--<th>ID</th>--%>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha salida</th>
					<th>Fecha llegada</th>
					<th>Implicación con el viaje</th>
					<th>Status</th>
					<th>Administracion</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${viajes}" varStatus="i">
					<c:forEach var="viaje" items="${entry.value}">

						<tr id="item_${i.index}">
							<%--<td></td>--%>
							<td>${viaje.departure.city}</td>
							<td>${viaje.destination.city}</td>
							<td>${viaje.departureDate}</td>
							<td>${viaje.arrivalDate}</td>
							<td>${entry.key}</td>
							<td>${viaje.status}</td>
							
							<td>
							<c:if test="${viaje.status!='CANCELLED'}">
								<ul>
									<c:choose>
										<c:when test="${entry.key=='PROMOTOR'}">
											<li><a href="#">Valorar Viaje</a></li>
											<li><a href="#">Valorar participantes</a></li>
										</c:when>
										<c:otherwise>
											<li><a href="valorarViaje.jsp?idViaje=${viaje.id}">Valorar Viaje</a></li>
											<li><a href="#">Valorar Promotor</a></li>
											<li><a href="#">Valorar Compañeros</a></li>
										</c:otherwise>
									</c:choose>
								</ul>
							</c:if>	
							</td>
							
						</tr>
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>

