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
        <title>JSP Page</title>
        <script src="js/jquery.min.js"></script>
        <script>
	$(document).ready(function() {
		$('#submit').click(function(event) {
			$.post('EstadoServlet', {
				
			}, function(responseText) {
				$('#resultado').html(responseText);
			});
		});
	});
    </script>
    </head>
    <body>
        <h1>Hello World! ESTADO</h1>
        <input type="button" id="submit" value="Añadir" /> 
        <div id="resultado"></div>
        <script>//setInterval(function(){console.log('<%= Estado.leer() %>');},1000);</script>        
    </body>
</html>
