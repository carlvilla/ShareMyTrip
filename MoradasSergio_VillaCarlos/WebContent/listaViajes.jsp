<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarNavegacion.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Listado de viajes</title>

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

<link rel="stylesheet" href="css/listaViajesCss.css" type="text/css"></link

</head>

<body>

	<center><h2>Viajes disponibles</h2></center>
	
	
        <div id="comboBoxOrden" class="col-xs-5 selectContainer">
            <select class="form-control" name="size" onchange="window.location=this.options[this.selectedIndex].value">
                <option value="">Ordenar por</option>
                <option value="listarViajes?orden=origen">Origen</option>
                <option value="listarViajes?orden=destino">Destino</option>
                <option value="listarViajes?orden=fechaSalida">Fecha salida</option>
                <option value="listarViajes?orden=fechaLlegada">Fecha llegada</option>
                <option value="listarViajes?orden=fechaFin">Fecha fin inscripción</option>
                <option value="listarViajes?orden=plazas">Plazas disponible</option>
            </select>
        </div>

	
	<br/>
	
	<div class="container">
		<table id="tabla" class="table table-hover">
			<tr>
				<th>ID viaje</th>
				<th>Origen</th>
				<th>Destino</th>
				<th>Fecha salida</th>
				<th>Fecha llegada</th>
				<th>Fecha fin inscripción</th>
				<th>Plazas libres</th>
			</tr>
			<c:forEach var="entry" items="${listaViajes}" varStatus="i">
				<tr>
					<td><a id="item_${i.index}" href="mostrarViaje?id=${entry.id}">${entry.id}</a></td>
					<td>${entry.departure.city}</td>
					<td>${entry.destination.city}</td>		
					<td>${entry.departureDate}</td>
					<td>${entry.arrivalDate}</td>
					<td>${entry.closingDate}</td>				
					<td>${entry.availablePax}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>