<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Reservas</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" 
                    data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" 
                    aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
        <div class="d-flex justify-content-end collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" 
                       href="${pageContext.request.contextPath}/ClienteController">Clientes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" 
                       href="${pageContext.request.contextPath}/RestauranteController">Restaurantes</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" 
                       href="${pageContext.request.contextPath}/MesaController">Mesas</a>
                </li>                            
                <li class="nav-item">
                    <a class="nav-link" 
                       href="${pageContext.request.contextPath}/ReservaMesasController">Reservas</a>
                </li>                            
            </ul>                        
        </div>
        </div>
</nav>
</header>
