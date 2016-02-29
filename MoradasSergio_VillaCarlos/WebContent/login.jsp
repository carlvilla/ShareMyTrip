<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="barraPublica.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head> 
<link href="bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
<title>ShareMyTrip - Inicie sesión</title>
<link rel="stylesheet" href="css/login.css" type="text/css"></link>
</head> 
<body>
  <form class="form-signin" action="validarse" method="post">

 	<center><h1>Inicie sesión</h1></center>
 	<hr><br>
 	<table align="center">
    	<tr> 
    		<td id="loginLabel" align="right">Identificador de usuario:  </td>
	    	<td><input type="text"  id="nombreUsuario" class="form-control" name="nombreUsuario" align="left" size="15"></td>
      	</tr>
      	<tr> 
    		<td id="passLabel" align="right">Contraseña:  </td>
	    	<td><input type="password" id="passwordUsuario" class="form-control" name="passwordUsuario" align="left" size="15"></td>
      	</tr>
      	<tr>
		<td><br></td>
		</tr>
      	<tr>
    	    <td colspan="2"><input class="btn btn-lg btn-primary btn-block" type="submit"  value="Enviar" /></td>
      	</tr>
      </table>
   </form>
   <c:if test="${error != null}">
   		<p align="center"><c:out value="${error}"/></p>
   </c:if>
   

   
</body>
</html>