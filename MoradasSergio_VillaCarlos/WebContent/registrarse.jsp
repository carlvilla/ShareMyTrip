<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuarioRegistrarse.jsp"%>
<%@ include file="barraPublica.jsp"%>
<!DOCTYPE html>
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ShareMyTrip - Registrarse</title>
<link rel="stylesheet" href="css/registrarse.css" type="text/css"></link>

</head>
<body>
	<form action="registrarse" method="post">


		<h1>Registrarse</h1>

		<hr>
		<br> <br />
		<div class="col-sm-4">
		</div>
		<div class="col-sm-4">
		<table>
			<tr>
				<td><input type="text" class="form-control" name="nombreUsuario"
					placeholder="Nombre" size="20"></td>
				<td><input type="text" class="form-control"  name="apellidosUsuario"
					placeholder="Apellidos" size="20"></td>
			</tr>
			<tr>
				<td><input type="text" class="form-control" name="email" placeholder="Email"
					size="20"></td>
			</tr>
			<tr>
				<td><input type="text" class="form-control" name="loginUsuario"
					placeholder="Nombre Usuario" size="20"></td>
			</tr>
			<tr>
				<td><input type="password" class="form-control" name="passwordUsuario"
					placeholder="Contraseña" size="20"></td>
			</tr>
			<tr>
				<td><input type="password" class="form-control" name="confirmPasswordUsuario"
					placeholder="Confirma tu contraseña" size="20"></td>
			</tr>
			<tr>
				<td colspan="2"><input type="submit" class="btn btn-lg btn-primary btn-block" value="Registrarse" /></td>
			</tr>
		</table>
		</div>
		<div class="col-sm-4">
			<p>¡Registrate hoy y disfruta de todas las ventajas!</p>

			<ul>
				<li>Acceso a toda la información de los viajes</li>
				<li>Alquilar plazas en viajes</li>
				<li>Registrar tus propios viajes</li>
				<li>Comentar los viajes en los que has participado</li>
				<li>...</li>

			</ul>

		</div>

	</form>
	<c:if test="${error != null}">
		<p align="center">
			<c:out value="${error}" />
		</p>
	</c:if>

	<br />


</body>
</html>