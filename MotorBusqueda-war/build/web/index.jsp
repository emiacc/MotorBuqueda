<%-- 
    Document   : index
    Created on : 11/04/2015, 12:27:00
    Author     : Emiliano
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
         <form method="POST" action="Indexador">
            <input type="text" name="url">  
            <input type="submit" value="Indexar">
        </form>
        
        <form method="POST" action="Buscador">
            <input type="text" name="cadena">  
            <input type="submit" value="Buscar">
        </form>
        
        <form method="POST" action="IndexadorDirectorio">
            <input type="text" name="cadena">  
            <input type="submit" value="Indexat Todo">
        </form>
    </body>
</html>
