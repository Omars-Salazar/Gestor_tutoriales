/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;
import clases.GestionCategorias;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Omar Salazar
 */


@WebServlet(name = "EliminarCategoriaServlet", urlPatterns = {"/EliminarCategoria"})
public class EliminarCategoriaServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Obtener el ID de la categoría desde el formulario
        String idStr = request.getParameter("idCategoria");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                int idCategoria = Integer.parseInt(idStr);

                // Llamar al método para eliminar la categoría
                GestionCategorias.eliminarCategoria(idCategoria);

                // Redireccionar al usuario a una página de confirmación o de lista de categorías
                response.sendRedirect("Categorias.jsp"); // Asegúrate de que esta página exista
            } catch (NumberFormatException e) {
                // Manejar el error de formato incorrecto
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de categoría inválido");
            }
        } else {
            // Enviar un error si el ID de la categoría no está presente
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "ID de categoría no proporcionado");
        }
    }
}
