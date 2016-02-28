<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<%@ include file="barraNavegacion.jsp" %>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<!DOCTYPE html>
<html>
<head>
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
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Valorar Promotor</title>
</head>
<body>
	<h1 align="center">Valorar Promotor</h1>
	<div class="container">
	<form action="valorarPromotor?idViaje=${param.idViaje}" method="POST">
	<table id="tabla" class="table table-hover">
	<thead>
				<tr>
					<%-- <th>Compañero</th>--%>
					<th>Comentario</th>
					<th>Valoracion</th>
				</tr>
			</thead>
				<tr>
					<td><textarea class="form-control" rows="4" cols="50" name="comentario">Introduzca su comentario...</textarea></td>
					<td><input class="form-control" type="number" name="valoracion" value="0" min="0" max="10"></td>
				</tr>
	</table>
		<input class="btn btn-lg btn-primary btn-block" type="submit" value="Valorar"/>
	</form>			
	</div>	
</body>
</html>
