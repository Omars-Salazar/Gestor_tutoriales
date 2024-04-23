/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

/**
 *
 * @author Omar Salazar
 */
import clases.Tutorial;
import clases.GestionTutorial;
import static clases.GestionTutorial.establecerConexion;
import static clases.GestionTutorial.listarTutoriales;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvAgregar", urlPatterns = {"/SvAgregar"})
public class SvAgregar extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        List<Tutorial> tutoriales = listarTutoriales();

        // Generar la tabla HTML con los datos de los tutoriales
        StringBuilder htmlBuilder = new StringBuilder();
        htmlBuilder.append("<table border='1'><tr><th>ID</th><th>Título</th><th>URL</th><th>Estado</th><th>Prioridad</th><th>Categoría</th></tr>");
        for (Tutorial tutorial : tutoriales) {
            htmlBuilder.append("<tr>");
            htmlBuilder.append("<td>").append(tutorial.getIdTutorial()).append("</td>");
            htmlBuilder.append("<td>").append(tutorial.getTitulo()).append("</td>");
            htmlBuilder.append("<td>").append(tutorial.getUrl()).append("</td>");
            htmlBuilder.append("<td>").append(tutorial.getEstado()).append("</td>");
            htmlBuilder.append("<td>").append(tutorial.getPrioridad()).append("</td>");
            htmlBuilder.append("<td>").append(tutorial.getIdCategoria()).append("</td>");
            htmlBuilder.append("</tr>");
        }
        htmlBuilder.append("</table>");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(htmlBuilder.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String titulo = request.getParameter("titulo");
        int prioridad = Integer.parseInt(request.getParameter("prioridad"));
        String url = request.getParameter("url");
        String estado = request.getParameter("estado");
        int categoria = Integer.parseInt(request.getParameter("categoria"));

        agregarTutorial(titulo, url, estado, prioridad, categoria);

        response.sendRedirect("index.jsp"); // Asegúrate de que esta URL es correcta
    }

    private void agregarTutorial(String titulo, String url, String estado, int prioridad, int categoria) {
        try (Connection conexion = GestionTutorial.establecerConexion()) {
            String sql = "INSERT INTO tutorial (titulo, URL, estado, prioridad, idCategoria) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, titulo);
                pstmt.setString(2, url);
                pstmt.setString(3, estado);
                pstmt.setInt(4, prioridad);
                pstmt.setInt(5, categoria);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println("Error al agregar tutorial: " + ex.getMessage());
        }
    }

    private static List<Tutorial> listarTutoriales() {
        List<Tutorial> tutoriales = new ArrayList<>();
        try (Connection conexion = GestionTutorial.establecerConexion()) {
            String sql = "SELECT * FROM tutorial";
            try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Tutorial tutorial = new Tutorial(
                        resultSet.getInt("idTutorial"),
                        resultSet.getString("titulo"),
                        resultSet.getString("titulo"),
                        resultSet.getString("URL"),
                        resultSet.getString("estado"),
                        resultSet.getInt("prioridad"),
                        resultSet.getInt("idCategoria"),
                        resultSet.getInt("idCategoria")
                    );
                    tutoriales.add(tutorial);
                }
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar los tutoriales: " + ex.getMessage());
        }
        return tutoriales;
    }
}


   
