<%-- 
    Document   : index
    Created on : 18/04/2024, 9:37:34 a. m.
    Author     : Omar Salazar
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel ="stylesheet" href="templates/style.css">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    
    <body class="sb-nav-fixed">
        <nav class="navbar navbar-expand-lg bg-secondary text-uppercase fixed-top" id="mainNav">
            <div class="container">
                
                <button class="navbar-toggler text-uppercase font-weight-bold bg-primary text-white rounded" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto">
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="index.jsp">Tutoriales</a></li>
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="Categorias.jsp">Categorias</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <!-- Sidebar content here -->
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Agrega un Tutorial</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Agrega un Tutorial</li>
                        </ol>
                        <div class="row">
                            <!-- Additional rows for other content -->
                        </div>

                        <!-- Button to open the modal to add a tutorial -->
                        <button type="button" class="btn btn-primary mb-2" data-bs-toggle="modal" data-bs-target="#addTutorialModal">
                            Agregar Tutorial
                        </button>

                        <div>
                            <h1>Opciones</h1>
                            <div style="display: flex; align-items: flex-start; gap: 5px;">
                                <form action="EliminarTutorial" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este tutorial?');" style="display: flex; flex-direction: column;">
                                    <input type="text" name="tituloTutorial" placeholder="Ingrese el título del tutorial para eliminar" required>
                                    <button type="submit" class="btn btn-primary mb-2">Eliminar Tutorial</button>
                                </form>

                                <button type="button" class="btn btn-secondary mb-2" data-bs-toggle="modal" data-bs-target="#editTutorialModal">
                                    Editar Tutorial
                                </button>

                            </div>
                        </div>






                        <!-- Modal para agregar tutorial -->
                        <div class="modal fade" id="addTutorialModal" tabindex="-1" aria-labelledby="addTutorialModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="addTutorialModalLabel">Agregar Nuevo Tutorial</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="addTutorialForm" action="SvAgregar" method="POST">
                                            <div class="mb-3">
                                                <label for="titulo" class="form-label">Título</label>
                                                <input type="text" class="form-control" id="titulo" name="titulo" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="prioridad" class="form-label">Prioridad</label>
                                                <input type="number" class="form-control" id="prioridad" name="prioridad" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="url" class="form-label">URL</label>
                                                <input type="text" class="form-control" id="url" name="url" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="estado" class="form-label">Estado</label>
                                                <input type="text" class="form-control" id="estado" name="estado" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="categoria" class="form-label">Categoría</label>
                                                <select class="form-control" id="categoria" name="categoria" required>
                                                    <option value="1">Lógica de Programación</option>
                                                    <option value="2">Flutter</option>
                                                    <option value="3">Node.js</option>
                                                    <option value="4">Lenguajes de Programación</option>
                                                    <option value="5">HTML</option>
                                                    <option value="6">Bases de Datos</option>
                                                    <option value="7">Matemáticas</option>
                                                    <option value="8">Geometría</option>
                                                    <option value="9">Física</option>
                                                    <option value="10">Instalaciones</option>
                                                    <option value="11">Juegos</option>
                                                    <option value="12">Vida Cotidiana</option>
                                                </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Guardar Tutorial</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- Modal para editar tutorial -->
                        <div class="modal fade" id="editTutorialModal" tabindex="-1" aria-labelledby="editTutorialModalLabel" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title" id="editTutorialModalLabel">Editar Tutorial</h5>
                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                    </div>
                                    <div class="modal-body">
                                        <form id="editTutorialForm" action="EditarTutorial" method="POST">
                                            <div class="mb-3">
                                                <label for="editIdTutorial" class="form-label">ID del Tutorial</label>
                                                <input type="number" class="form-control" id="editIdTutorial" name="idTutorial" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="editTitulo" class="form-label">Título</label>
                                                <input type="text" class="form-control" id="editTitulo" name="titulo" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="editPrioridad" class="form-label">Prioridad</label>
                                                <input type="number" class="form-control" id="editPrioridad" name="prioridad" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="editUrl" class="form-label">URL</label>
                                                <input type="text" class="form-control" id="editUrl" name="url" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="editEstado" class="form-label">Estado</label>
                                                <input type="text" class="form-control" id="editEstado" name="estado" required>
                                            </div>
                                            <div class="mb-3">
                                                <label for="editCategoria" class="form-label">Categoría</label>
                                                <select class="form-control" id="editCategoria" name="categoria" required>
                                                    <option value="1">Lógica de Programación</option>
                                                    <option value="2">Flutter</option>
                                                    <option value="3">Node.js</option>
                                                    <option value="4">Lenguajes de Programación</option>
                                                    <option value="5">HTML</option>
                                                    <option value="6">Bases de Datos</option>
                                                    <option value="7">Matemáticas</option>
                                                    <option value="8">Geometría</option>
                                                    <option value="9">Física</option>
                                                    <option value="10">Instalaciones</option>
                                                    <option value="11">Juegos</option>
                                                    <option value="12">Vida Cotidiana</option>                        </select>
                                            </div>
                                            <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                                        </form>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cerrar</button>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <!-- Card for displaying tutorials -->
                        <div class="card mb-4">
                            <div class="card-header">
                                <i class="fas fa-table me-1"></i>
                                DataTable Example
                            </div>
                            <div class="card-body">
                                <table id="datatablesSimple" class="table">
                                    <thead>
                                        <tr>

                                    </thead>
                                    <tbody id="tutorialsTableBody">
                                        <!-- Tutorial rows will be loaded here dynamically -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </main>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function () {
            fetch('SvAgregar', {method: 'GET'})
                    .then(response => response.text())
                    .then(html => {
                        document.getElementById('tutorialsTableBody').innerHTML = html; // Insertar la tabla HTML dentro del tbody
                    });
        });

    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
    <script src="assets/demo/chart-area-demo.js"></script>
    <script src="assets/demo/chart-bar-demo.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
    <script src="js/datatables-simple-demo.js"></script>
</body>


</html>

