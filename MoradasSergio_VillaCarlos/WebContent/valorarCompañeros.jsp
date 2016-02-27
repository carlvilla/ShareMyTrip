<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="comprobarUsuario.jsp"%>
<%@ include file="barraNavegacion.jsp"%>
<%@ include file="barraNavegacionMisViajes.jsp"%>
<%@ taglib uri="http://www.uniovi.es/sdi/comment" prefix="cm" %>
<!DOCTYPE html>
<html>
<head>

<title>ShareMyTrip - Valorar Compañeros</title>
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
	<h1>Valorar Compañeros</h1>
	<form id="usrform" action="valorarCompañeros?idViaje=${param.idViaje}" method="POST">
	<table id="tabla" class="table table-hover">
	<c:forEach var="entry" items="${compañeros}" varStatus="i">
				<tr id="item_${i.index}">
					<td>${entry.name} ${entry.surname} (${entry.email})</td>
					<td><textarea rows="4" cols="50" name="${entry.id}" form="usrform">Introduzca su comentario...</textarea></td>
				</tr>
	</c:forEach>
	</table>
		<input type="submit" value="Valorar"/>
	</form>		
</body>
</html>