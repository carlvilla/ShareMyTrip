<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head> 
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">

<title>ShareMyTrip - Administrar viaje</title>
</head> 
<body>
  <form class="form-signin" action="validarse" method="post">

 	<center><h2>Administrar viaje</h2></center>
 	
 	<form method="get" action="cancelarSolicitud?idViaje=${idViaje}&idUsuario=${idUsuario}&implicacion${implicacion}">
    	<button type="submit">Cancelar solicitud de plaza</button>
	</form>
 	
   
</body>
</html>