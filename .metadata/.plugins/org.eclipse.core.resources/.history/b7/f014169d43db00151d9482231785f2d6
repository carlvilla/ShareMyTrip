<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="comprobarUsuario.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Puntuación del promotor</title>
</head>
<body>
	
	<jsp:useBean id="promotor" class="uo.sdi.model.User" scope="request" />

	
	<h3>Información de contacto</h3>
	
		<table>

		<tr>
			<td>Nombre:</td><td> <jsp:getProperty property="name" name="promotor"/></td>
		</tr>
		<tr>
			<td>Apellidos:</td><td><jsp:getProperty property="surname" name="promotor"/></td>
		</tr>
		<tr>
			<td>Email:</td>
			<td><jsp:getProperty property="email" name="promotor"/></td>		
		</tr>
				
		</table>
	
	
	<h3>Puntuación y comentarios del promotor</h3>
	
	<c:forEach var="comentario" items="${comentariosPromotor}" varStatus="i">
			<tr id="item_${i.index}">
				<td>${comentario.comment}</td>
				<td>${comentario.value}</td>
			</tr>
		</c:forEach>	
		
</body>
</html>
