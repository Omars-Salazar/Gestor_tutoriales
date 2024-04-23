/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import clases.GestionTutorial;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Omar Salazar
 */
@WebServlet(name = "EliminarTutorialServlet", urlPatterns = {"/EliminarTutorial"})
public class EliminarTutorialServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String tituloTutorial = request.getParameter("tituloTutorial"); // Cambio aquí: recibir título en lugar de ID.
        if (tituloTutorial != null && !tituloTutorial.isEmpty()) {
            GestionTutorial.eliminarTutorial(tituloTutorial);
            response.sendRedirect("index.jsp"); // Redirigir a la página que lista los tutoriales
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Título del tutorial no proporcionado");
        }
    }
}
 

