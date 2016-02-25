<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${user!=null}">


<head>
	<style>
		nav {
			height: 45px;
			left : 0;
			width : 100%;
			background : url(resources/nav_background.png);
 		    margin-bottom: 15px;
			
			}
			nav ul {
			margin : 0 auto;
			width : 800px;
			list-style : none;
			}
			nav ul li {
			float : left;
			}
			nav ul li a {
			display : block;
			margin-right : 20px;
			width : 140px;
			font-size : 14px;
			line-height : 44px;
			text-align : center;
			text-decoration : none;
			color : #ccc;
			}
			nav ul li a:hover {
			color : #fff;
			}
			nav ul li.selected a {
			color : #fff;
			}
			
	
	</style>
</head>
<nav>

        <ul class="menu">  
            <li><a href="principal.jsp">Home</a></li>  
            <li><a href="modificarDatos.jsp">Modificar datos</a></li>  
            <li><a href="listarViajes">Lista de viajes</a></li>
             <li><a href="misViajes">Mis viajes</a></li> 
            <li><a href="cerrarSesion">Cerrar sesión</a></li> 
        </ul>    
        
        
</nav>

</c:if>