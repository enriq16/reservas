
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>        
        <title>Clientes</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/f90d3bf50d.js" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="/WEB-INF/general/cabecera.jsp"/>
        <header> 
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h2 class="float-right">
                            <i class="fas fa-cog"></i> Administración Mesa</h2>
                    </div>
                    
                    <div class="col-md-4">
                            <form>
                            <div class="form-group">
                                <label for="idRest">Restaurante</label>
                                <select class="form-control" id="idRest" name="idRest">
                                 <option value="0">Seleccionar Restaurante</option>
                                 <c:forEach var="lc" items="${listRest}" varStatus="status" >
                                 <option value="${lc.idRestaurante}" ${lc.idRestaurante == idRest ? 'selected' : ''}>
                                 ${lc.nombre}
                                 </option>
                                 </c:forEach>
                                </select>
                            </div>
                            </form>    
                    </div>
                    
                </div>
            </div>
        </header>

        <section class="mt-2">
            <div class="container">
                <div class="row">
                    <div class="col-md-3">
                        <a id="addMesa" href="${pageContext.request.contextPath}/MesaController?accion=agregar&idRest=${idRest}"
                           class="btn btn-primary btn-block">
                            <i class="fas fa-plus"></i> Agregar Mesa
                        </a>
                    </div>
                </div>
            </div>
        </section>

        <section id="clientes">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="card">
                            <div class="card-header">
                                <h4>Listado Mesas</h4>
                            </div>
                            <table class="table table-striped">
                                <thead class="thead-dark">
                                    <tr>
                                        <th>#</th>
                                        <th>Nombre</th>
                                        <th>Latitud</th>
                                        <th>Longitud</th>
                                        <th>Planta</th>                                        
                                        <th>Capacidad</th>
                                        <th>Restaurante</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody id="listaMesas">
                                    <c:forEach var="c" items="${list}" varStatus="status" >
                                        <tr>
                                            <td>${status.count}</td>
                                            <td>${c.nombreMesa}</td>
                                            <td>${c.posicionLat}</td> 
                                            <td>${c.posicionLon}</td>
                                            <td>${c.planta}</td>
                                            <td>${c.capacidad}</td>
                                            <td>${c.restaurante.nombre}</td>
                                            <td>
                                            <a href="${pageContext.request.contextPath}/MesaController?accion=editar&id=${c.id}&idRest=${idRest}"
                                               class="btn btn-secondary">
                                             <i class="fas fa-angle-double-right"></i> Editar
                                            </a>                                                
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
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
        
                $("#addMesa").on("click", function(event){
                    if($("#idRest").val() == "0"){
                        event.preventDefault();
                        alert("Debe seleccionar un restaurant para agregar una mesa.");
                    }                    
                });
        
                $("#idRest").on("change", function () {
                    if($(this).val() != "0")
                        window.location = "${pageContext.request.contextPath}/MesaController?idRest="+$(this).val();
                    else{
                        console.log("No redirecciono");
                    }
                });
            });        
        </script>        
    </body>
</html>
