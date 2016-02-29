<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Puntuación del promotor</title>
</head>
<body>

	<jsp:useBean id="promotor" class="uo.sdi.model.User" scope="request" />


	<h3>Información de contacto</h3>

	<table>

		<tr>
			<td>Nombre:</td>
			<td><jsp:getProperty property="name" name="promotor" /></td>
		</tr>
		<tr>
			<td>Apellidos:</td>
			<td><jsp:getProperty property="surname" name="promotor" /></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><jsp:getProperty property="email" name="promotor" /></td>
		</tr>

	</table>


	<h3>Puntuación y comentarios del promotor</h3>

	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>Puntuación</th>
					<th>Comentario</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="comentario" items="${comentariosPromotor}"
					varStatus="i">
					<tr class="info" id="item_${i.index}">
						<td>${comentario.value}</td>
						<td>${comentario.comment}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

	<br/>

	<h3>Puntuación y comentarios de los participantes</h3>
	
	<br/>

	<div class="container">
		<table class="table">
			<thead>
				<tr>
					<th>Nombre</th>
					<th>Apellidos</th>
					<th>Email</th>
					<th>Puntuación</th>
					<th>Comentario</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="entry" items="${comentariosAceptados}" varStatus="i">


					<c:forEach var="comentario" items="${entry.value}">

						<tr id="item_${i.index}">
							
							<td>${entry.key.name}</td>
							<td>${entry.key.surname}</td>
							<td>${entry.key.email}</td>
							<td>${comentario.value}</td>
							<td>${comentario.comment}</td>

						</tr>
					</c:forEach>
				</c:forEach>

			</tbody>
		</table>
	</div>








</body>
</html>
