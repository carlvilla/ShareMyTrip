<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="comprobarNavegacion.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
<title>ShareMyTrip - Modificar datos de usuario</title>
</head>
<body>
	
	<jsp:useBean id="user" class="uo.sdi.model.User" scope="session" />
	
	<form action="modificarDatos" method="POST">				
	
	<table>
		<tr>
			<td>Login:</td><td id="login"> <jsp:getProperty property="login" name="user"/> </td>
		</tr>
		<tr>
			<td>Nombre:</td><td id="name">
			<input type="text" name="nombre" size="15"
						value="<jsp:getProperty property="name" name="user"/>"> 
						</td>
		</tr>
		<tr>
			<td>Apellidos:</td><td id="surname">
			<input type="text" name="email" size="15"
						value="<jsp:getProperty property="surname" name="user"/>">
		
			</td> 

			
		</tr>
		<tr>
			<td>Email:</td>
			<td><input type="text" name="email" size="15"
						value="<jsp:getProperty property="email" name="user"/>" />
						</td>
					
			
		</tr>
		
		
		
	</table>

		<input type="submit" value="Modificar"/>

	</form>

</body>
</html>
