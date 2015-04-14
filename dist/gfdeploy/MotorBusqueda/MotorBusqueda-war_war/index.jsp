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
        <title>Buscador</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilo.css">
    </head>
    <body>
        <nav id="navbar-example" class="navbar navbar-default navbar-static">
            <div class="container-fluid">
                <div class="navbar-header"><a class="navbar-brand" href="index.jsp">Motor de Búsqueda</a></div>
                <div class="collapse navbar-collapse bs-example-js-navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Buscador</a></li>
                        <li><a href="indexador.jsp">Indexador</a></li>
                    </ul>                        
                </div>
            </div>
        </nav>
        <div class="container">
            <div class="row">
                <form method="POST" action="Buscador" class="form-inline">
                    <div class="form-group col-xs-8"><input type="text" name="cadena" class="form-control"></div>
                    <input type="submit" value="Buscar" class="btn btn-default col-xs-4">
                </form>
            </div>
            <br><br>  
            <div class="row">
                <table class="table table-hover" id="resultados">
                 ${resultado}
                </table>
            </div>
        </div>        
    </body>
</html>
