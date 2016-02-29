<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Modificar datos de usuario</title>
<link rel="stylesheet" href="css/modificarDatos.css" type="text/css"></link>

</head>
<body>

	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />

	<center>
		<h2>Modificar datos personales</h2>
	</center>

	<form action="modificarDatos" method="POST" class="form-horizontal">


		<div class="container">

			<div class="form-group">

				<label class="control-label col-sm-2" for="login">Login:</label>
				<div class="col-sm-10">
					<input class="form-control" id="login" type="text" name="country"
						value=<jsp:getProperty property="login" name="user" /> readonly>
				</div>

				<label class="control-label col-sm-2" for="nombre">Nombre:</label>
				<div class="col-sm-10">
					<input type="text" name="nombre" class="form-control" id="nombre"
						value=<jsp:getProperty property="name" name="user"/>>
				</div>

				<label class="control-label col-sm-2" for="apellidos">Apellidos:</label>
				<div class="col-sm-10">
					<input type="text" name="apellidos" class="form-control"
						id="apellidos"
						value=<jsp:getProperty property="surname" name="user"/>>
				</div>



				<label class="control-label col-sm-2" for="email">Apellidos:</label>
				<div class="col-sm-10">
					<input type="text" name="email" class="form-control" id="email"
						value=<jsp:getProperty property="email" name="user"/>>
				</div>

			</div>


			<div class="form-group">
				<label class="control-label col-sm-2" for="pwd">Contraseña
					anterior:</label>
				<div class="col-sm-10">
					<input name="pass" type="password" class="form-control" id="pwd">
				</div>
			</div>


			<div class="form-group">
				<label class="control-label col-sm-2" for="pwdNueva">Nueva
					contraseña:</label>
				<div class="col-sm-10">
					<input name="newPass" type="password" class="form-control"
						id="pwdNueva">
				</div>
			</div>

			<div class="form-group">
				<label class="control-label col-sm-2" for="pwdConfirmacion">Confirmar
					contraseña:</label>
				<div class="col-sm-10">
					<input name="verPass" type="password" class="form-control"
						id="pwdConfirmacion">
				</div>
			</div>


			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<input class="btn btn-primary btn-lg" type="submit"
						value="Modificar" />
				</div>
			</div>
		</div>
	</form>



	<br />

	<c:if test="${error != null}">
		<p align="center">
			<c:out value="${error}" />
		</p>
	</c:if>

</body>
</html>
