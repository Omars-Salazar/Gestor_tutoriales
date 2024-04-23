<%-- 
    Document   : Categorias
    Created on : 18/04/2024, 9:37:34 a.m.
    Author     : Omar Salazar
--%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="Omar Salazar" />
        <title>Categorías - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href="css/styles.css" rel="stylesheet" />
        <link rel="stylesheet" href="templates/style.css">
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                        <li class="nav-item mx-0 mx-lg-1"><a class="nav-link py-3 px-0 px-lg-3 rounded" href="Categorias.jsp">Categorías</a></li>
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
                        <h1 class="mt-4">Categorías</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">Listado de Categorías</li>
                        </ol>
                    </div>
                    <div class="card mb-4">
                        <div class="card-header">
                            <i class="fas fa-table me-1"></i>
                            Lista de Categorías
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Nombre</th>
                                    </tr>
                                </thead>
                                <tbody id="categoriasTableBody"> <!-- Cambiado el ID para ser específico -->
                                    <!-- Las filas serán cargadas dinámicamente aquí -->
                                </tbody>
                            </table>
                        </div>

                        <h2>Agregar Nueva Categoría</h2>
                        <form action="AgregarCategoria" method="POST">
                            <div class="form-group mb-3">
                                <label for="nombreCategoria">Nombre de la categoría:</label>
                                <input type="text" class="form-control" id="nombreCategoria" name="nombreCategoria" required>
                            </div>
                            <button type="submit" class="btn btn-primary">Agregar Categoría</button>
                        </form>
                        <form action="EliminarCategoria" method="POST" onsubmit="return confirm('¿Estás seguro de que deseas eliminar este tutorial?');" style="display: flex; flex-direction: column;">
                            <input type="text" name="idCategoria" placeholder="Ingrese el ID del tutorial para eliminar" required>
                            <button type="submit" class="btn btn-primary mb-2">Eliminar Tutorial</button>
                        </form>
                     <!-- Botón para abrir el modal de edición de categoría -->
<button type="button" class="btn btn-warning mb-2" data-bs-toggle="modal" data-bs-target="#editCategoryModal">
    Editar Categoría
</button>

<!-- Modal para editar categoría -->
<div class="modal fade" id="editCategoryModal" tabindex="-1" aria-labelledby="editCategoryModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="editCategoryModalLabel">Editar Categoría</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="EditarCategoriaServlet" method="POST">
                    <div class="mb-3">
                        <label for="editCategoryId" class="form-label">ID de la Categoría:</label>
                        <input type="number" class="form-control" id="editCategoryId" name="idCategoria" required>
                    </div>
                    <div class="mb-3">
                        <label for="editCategoryName" class="form-label">Nuevo Nombre de la Categoría:</label>
                        <input type="text" class="form-control" id="editCategoryName" name="nuevoNombre" required>
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

                    </div>
                </main>
            </div>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function () {
                fetch('AgregarCategoria') // Asegúrate de que la URL coincida con el URL pattern del servlet
                        .then(response => response.text())
                        .then(html => {
                            document.getElementById('categoriasTableBody').innerHTML = html; // Insertar las filas de la tabla dentro del tbody
                        })
                        .catch(error => console.error('Error al cargar las categorías:', error));
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
