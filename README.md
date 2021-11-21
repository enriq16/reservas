# reservas
Sistema de reservas de mesas en restaurantes.

- Crear BD
Script en resources

- Crear DataSource en Server

Ej:

<datasource jndi-name="java:jboss/datasources/laboratorio" pool-name="LaboratorioPostgres" enabled="true" use-java-context="true">
                    <connection-url>jdbc:postgresql://localhost:5432/bd_reservas</connection-url>
                    <driver>postgres</driver>
                    <pool>
                        <max-pool-size>20</max-pool-size>
                    </pool>
                    <security>
                        <user-name>postgres</user-name>
                        <password>postgres</password>
                    </security>
</datasource>

- Administración de datos del restaurante (CRUD: POST, PUT, DELETE, GET)

<br>url: http://localhost:8080/reservas/RestauranteController
<br>metodo de envio de parametros: x-www-form-urlencoded
<br>Parametros a enviar: 
<br>POST: nombre_restaurante, direccion
<br>PUT: id, nombre_restaurante, direccion
<br>DELETE: id

