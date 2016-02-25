<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>

<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Mis viajes</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
$(document).ready(function() {

    $('#tabla tr').click(function() {
        var href = $(this).find("a").attr("href");
        if(href) {
            window.location = href;
        }
    });

});
</script>

</head>
<body>

	<center><h1>Mis viajes</h1></center>

	<div class="container">
		<h2>Viajes en los que ha tenido implicación</h2>

		<table id="tabla" class="table table-hover" data-link="row">
			<thead>
				<tr>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha salida</th>
					<th>Fecha llegada</th>
					<th>Fecha límite inscripción</th>
					<th>Implicación con el viaje</th>
				</tr>
			</thead>
			<tbody>

				<c:forEach var="entry" items="${viajes}" varStatus="i">


					<c:forEach var="viaje" items="${entry.value}">

						<tr id="item_${i.index}">
							<td><a href="administracionViaje?idViaje=${viaje.id}&implicacion=${entry.key}">${viaje.id}</a></td>
							<td>${viaje.departure.city}</td>
							<td>${viaje.destination.city}</td>
							<td>${viaje.departureDate}</td>
							<td>${viaje.arrivalDate}</td>
							<td>${viaje.closingDate}</td>
							<td>${entry.key}</td>

						</tr>


					</c:forEach>

				</c:forEach>

			</tbody>
		</table>
	</div>






</body>
</html>

