
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>        
        <title>Interface Cliente</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>        

    </head>
    <body>
        <jsp:include page="/WEB-INF/general/cabecera.jsp"/>
        <section id="conceptoUP" class="mt-5">
            <div class="container">
                <div class="row">
                    <div class="col-md-10">
                        <div class="card">
                            <div class="card-header">
                                <h4>
                                    ${param.accion=="agregar"?"Agregar Cliente.":param.accion=="editar"?"Editar Cliente.":"XXX"}
                                </h4>
                            </div>
                            <form id="fmrConceptoUP" class="was-validated">

                                <div class="modal-body">
                                    <div class="form-group">
                                        <input name="id" type="hidden" value="${cc != null ? cc.id : "0"}">
                                        <label for="cedula">Cédula</label>
                                        <input type="text" value="${cc!=null ? cc.cedula : ""}"
                                               class="form-control" name="cedula" required>
                                    </div>                                    
                                    <div class="form-group">                                        
                                        <label for="nombre">Nombre</label>
                                        <input type="text" value="${cc != null ? cc.nombre : ""}"
                                               class="form-control" name="nombre" required>
                                    </div>
                                    <div class="form-group">
                                        <label for="apellido">Apellido</label>
                                        <input type="text" value="${cc!=null ? cc.apellido: ""}"
                                               class="form-control" name="apellido" required>
                                    </div>                                                                                
                                </div>
                                <div class="modal-footer">
                                    <button id="btnConceptoUP" class="btn btn-primary" type="button">Guardar</button>
                                </div>    
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>

<jsp:include page="/WEB-INF/general/piePagina.jsp"/>

 <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
 <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" 
         integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" 
         crossorigin="anonymous"></script>
 <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" 
         integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" 
         crossorigin="anonymous"></script>    
<script>
            $(document).ready(function () {
                $("#btnConceptoUP").on("click", function () {

                    let datos = $("#fmrConceptoUP").serialize();

                    $.ajax({
                        url: "${pageContext.request.contextPath}/ClienteController",
                        data: datos,
                        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
                        dataType: "json", 
                        method: "${param.accion == "agregar"? "POST" : param.accion == "editar" ? "PUT" : ""}",
                        success: function (data, textStatus, jqXHR) {
                            if(data.status == "exito")
                                window.alert("Operación Status: " + textStatus + " | "+data.mensaje);
                            else
                                window.alert("Operación Status: " + textStatus + " | "+data.error);
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            window.alert("Operación Status: " + textStatus + ", error: " + errorThrown);
                        },
                        complete: function (jqXHR, textStatus) {
                            console.log("Complete redireccionando");
                            window.location = "${pageContext.request.contextPath}/ClienteController";
                        }
                    });
                });
            });
</script>
    </body>
</html>
