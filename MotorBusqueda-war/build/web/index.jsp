<%-- 
    Document   : index
    Created on : 11/04/2015, 12:27:00
    Author     : Emiliano
--%>

<%@page import="org.apache.jasper.tagplugins.jstl.ForEach"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buscador</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilo.css">
        <script type="text/javascript" src="js/jquery.min.js"></script>
        <script type="text/javascript" src="js/paging.js"></script>
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
                <div style="display:none; border: 0px;" id="NavPosicion"></div>
                <table class="table table-hover" id="resultados">
                    <tr><td></td></tr>  
                    ${resultado}
                </table>
            </div>
                
                
            <nav>
                <ul class="pagination">
                    <li id="prev" class="disabled">
                        <a href="#" aria-label="Previous">
                          <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>                                      
            <script>  
                var total = 0${cantidad};
                paginas = total / 10;
                var actual = 1;
                if(total!==0){
                    var pager = new Pager('resultados', 10);
                    pager.init();
                    pager.showPageNav('pager', 'NavPosicion');
                    pager.showPage(1);

                    for(var i = 0; i<paginas; i++){
                        document.write("<li class='pag' id="+(i+1)+"><a href='#' onclick='cambiar("+(i+1)+")'>"+(i+1)+"</a></li>");
                    }
                    $("#"+actual).addClass("active");
                }
                else $(".pagination").hide();                
                
                function cambiar(n){
                    pager.showPage(n);
                    actual=n;
                    $(".pag").removeClass("active");
                    $("#"+actual).addClass("active");       
                    
                    if(actual > 1){
                        $("#prev").removeClass("disabled");
                        $("#prev > a").attr("onclick", "cambiar("+(actual-1)+")");
                    }
                    else {
                        $("#prev").addClass("disabled");
                    }
                    if(actual > paginas){
                        $("#next").addClass("disabled");
                    }
                    else {
                        $("#next").removeClass("disabled");
                        $("#next > a").attr("onclick", "cambiar("+(actual+1)+")");
                    }
                }
            </script>
                    <li id="next">
                      <a href="#" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                      </a>
                    </li>
                </ul>
            </nav>
                
        </div>        
    </body>
</html>
