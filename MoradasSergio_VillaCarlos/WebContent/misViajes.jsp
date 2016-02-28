<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html>
<html>
<head>


<title>ShareMyTrip - Mis viajes</title>
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

th{
	font-size: 14px;
}

#comboBoxOrden {
	width: 220px;
	left: 970px;
	bottom: 40px;
}
</style>


</head>
<body>

	<h1>Mis viajes</h1>

	<div class="container">
		<h2>Viajes en los que ha tenido implicación</h2>


		<div id="comboBoxOrden" class="col-xs-5 selectContainer">
			<select class="form-control" name="size"
				onchange="window.location=this.options[this.selectedIndex].value">
				<option value="">Ordenar por</option>
				<option value="misViajes?orden=origen">Origen</option>
				<option value="misViajes?orden=destino">Destino</option>
				<option value="misViajes?orden=fechaSalida">Fecha salida</option>
				<option value="misViajes?orden=fechaLlegada">Fecha llegada</option>
				<option value="misViajes?orden=fechaFin">Fecha fin inscripción</option>
				<option value="misViajes?orden=implicacion">Implicación con el viaje</option>
				<option value="misViajes?orden=estado">Estado</option>
			</select>
		</div>


		<table id="tabla" class="table table-hover">
			<thead>
				<tr>
					<%--<th>ID</th>--%>
					<th>Origen</th>
					<th>Destino</th>
					<th>Fecha salida</th>
					<th>Fecha llegada</th>
					<th>Fecha límite inscripción</th>
					<th>Implicación con el viaje</th>
					<th>Estado</th>
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
							<td>${viaje.closingDate}</td>
							<td>${entry.key}</td>
							<td>${viaje.status}</td>

							<td>
								<ul>
									<c:choose>
										<c:when
											test="${entry.key=='PROMOTOR' && viaje.status=='OPEN' && !viaje.fechaCierrePasada()}">
											<li><a href="listarSolicitudes?idViaje=${viaje.id}">Listado
													Solicitudes</a></li>
											<li><a
												href="cancelarViaje?
											idViaje=${viaje.id}&implicacion=${entry.key}">Cancelar
													viaje</a></li>
											<li><a href="mostrarDatosViaje?idViaje=${viaje.id}">Modificar
													viaje</a></li>
										</c:when>
										<c:otherwise>
											
											<c:if
												test="${entry.key!='PROMOTOR' && entry.key!='SIN PLAZA' && entry.key!='EXCLUIDO'
											&& !viaje.fechaCierrePasada()}">
												
												<li><a id="item_CancelaPlaza_${i.index}"
													href="cancelarPlaza?idViaje=${viaje.id}&implicacion=${entry.key}">
													Cancelar plaza</a>
												</li>
												
								
											</c:if>

											<c:if
												test="${viaje.fechaCierrePasada() && viaje.status=='OPEN'}">

												<li>La fecha de cierre ha pasado</li>

											</c:if>

										</c:otherwise>
									</c:choose>
								</ul>
							</td>
						</tr>
						
						
					</c:forEach>
				</c:forEach>
			</tbody>
		</table>
	</div>


</body>
</html>

