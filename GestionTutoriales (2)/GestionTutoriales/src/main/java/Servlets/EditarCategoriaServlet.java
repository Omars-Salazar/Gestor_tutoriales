/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import static clases.GestionCategorias.editarCategoria;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Omar Salazar
 */
@WebServlet(name = "EditarCategoriaServlet", urlPatterns = {"/EditarCategoriaServlet"})
public class EditarCategoriaServlet extends HttpServlet {
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idCategoria = Integer.parseInt(request.getParameter("idCategoria"));
        String nuevoNombre = request.getParameter("nuevoNombre");

        try {
            editarCategoria(idCategoria, nuevoNombre);
            response.sendRedirect("Categorias.jsp"); // Redireccionar a la página que lista las categorías
        } catch (NumberFormatException e) {
            // Manejo de error de formato numérico
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de categoría inválido");
        } catch (SQLException e) {
            // Manejo de error de SQL
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar la categoría: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otros errores
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error procesando la solicitud: " + e.getMessage());
        }
    }

}
