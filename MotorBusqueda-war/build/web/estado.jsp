<%-- 
    Document   : estado
    Created on : 13/04/2015, 10:15:50
    Author     : Emi
--%>

<%@page import="Datos.Estado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estado</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilo.css">
        <script src="js/jquery.min.js"></script>
        <script>
	$(document).ready(function() {
                var resultado = "";
		interval = setInterval(function(){
                        console.log("vuelta");
			$.post('EstadoServlet', {
				
			}, function(responseText) {
                            resultado = responseText; 
                            if(resultado.length != 2){ 
                                $('#resultado').html(responseText);
                                clearInterval(interval); 
                            }                           
			});
		},1000);                
	});
    </script>
    </head>
    <body>
        <nav id="navbar-example" class="navbar navbar-default navbar-static">
            <div class="container-fluid">
                <div class="navbar-header"><a class="navbar-brand" href="index.jsp">Motor de Búsqueda</a></div>
                <div class="collapse navbar-collapse bs-example-js-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="index.jsp">Buscador</a></li>
                        <li><a href="indexador.jsp">Indexador</a></li>
                    </ul>                        
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <h3>Estado:</h3>
                <div id="resultado">procesando...</div>
                <a class="btn btn-default" href="indexador.jsp" role="button">Volver</a> 
            </div>
        </div>
    </body>
</html>
