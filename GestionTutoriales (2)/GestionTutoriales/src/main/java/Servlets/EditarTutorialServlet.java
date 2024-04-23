/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import clases.GestionTutorial;
import static clases.GestionTutorial.editarTutorial;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
@WebServlet(name = "EditarTutorialServlet", urlPatterns = {"/EditarTutorial"})
public class EditarTutorialServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idTutorial = Integer.parseInt(request.getParameter("idTutorial"));
            String titulo = request.getParameter("titulo");
            String url = request.getParameter("url");
            String estado = request.getParameter("estado");
            int prioridad = Integer.parseInt(request.getParameter("prioridad"));
            int idCategoria = Integer.parseInt(request.getParameter("categoria"));
            
            GestionTutorial.editarTutorial(idTutorial, titulo, url, estado, prioridad, idCategoria);
            response.sendRedirect("index.jsp"); // Redirigir a la página principal o a una de confirmación
        } catch (NumberFormatException e) {
            // Manejar excepción para entradas numéricas inválidas
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error en formato numérico.");
        } catch (SQLException e) {
            // Manejar excepciones de base de datos
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al editar el tutorial.");
        }
    }

   
}