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
        <h1>Estado</h1>
        <div id="resultado">procesando...</div>
                
    </body>
</html>
