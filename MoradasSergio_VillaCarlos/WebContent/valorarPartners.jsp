<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<%@ taglib uri="http://www.uniovi.es/sdi/comment" prefix="cm" %>
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
<title>ShareMyTrip - Valorar Compañeros</title>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
	<h1>Valorar Compañeros</h1>
	<div class="container">
	<form id="usrform" action="valorarPartners?idViaje=${param.idViaje}" method="POST">
	<table id="tabla" class="table table-hover">
	<thead>
				<tr>
					<th>Compañero</th>
					<th>Comentario</th>
					<th>Valoracion</th>
				</tr>
			</thead>
	<c:forEach var="entry" items="${partners}" varStatus="i">
				<tr id="item_${i.index}">
					<td style="width: 200px"><p >${entry.name} ${entry.surname} (${entry.email})</p></td>
					<td style="width: 200px"><textarea class="form-control" rows="4" cols="20" name="${entry.id}" form="usrform">Introduzca su comentario...</textarea></td>
					<td style="width: 200px"><input class="form-control" type="number" name="${entry.id}_valoracion" value="0" min="0" max="10"></td>
				</tr>
	</c:forEach>
	</table>
		<input  class="btn btn-lg btn-primary btn-block"  type="submit" value="Valorar"/>
	</form>		
	</div>
</body>
</html>