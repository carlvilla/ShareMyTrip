
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<c:if test="${user==null}">

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
 
    <ul class="nav navbar-nav navbar-right">
      <li><a id="listarViajes" href="listarViajes"> Lista viajes</a></li>
      <li><a href="registrarse.jsp"><span class="glyphicon glyphicon-user"></span> Registrarse</a></li>
      <li><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
  </div>
</nav>


</c:if>