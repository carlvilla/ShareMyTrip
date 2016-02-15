<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html><head> <title>ShareMyTrip - Inicie sesión</title>
<body>
  <form action="validarse" method="post">

 	<center><h1>Inicie sesión</h1></center>
 	<hr><br>
 	<table align="center">
    	<tr> 
    		<td align="right">Su identificador de usuario</td>
	    	<td><input type="text" name="nombreUsuario" align="left" size="15"></td>
      	</tr>
      	<tr>
    	    <td><input type="submit" value="Enviar"/></td>
      	</tr>
      </table>
   </form>
   <a id="listarViajes" href="listarViajes">Lista de viajes</a>
</body>
</html>