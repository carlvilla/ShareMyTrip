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
	
	<c:forEach var="entry" items="${compañeros}" varStatus="i">
				<tr id="item_${i.index}">
					<td>${entry.name}</td>
					<td>${entry.surname}</td>
				
				</tr>
	</c:forEach>
</body>
</html>