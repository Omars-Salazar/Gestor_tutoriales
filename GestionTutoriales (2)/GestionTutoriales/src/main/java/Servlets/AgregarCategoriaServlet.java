/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import clases.Categoria;
import clases.GestionTutorial;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Omar Salazar
 */


@WebServlet(name = "AgregarCategoriaServlet", urlPatterns = {"/AgregarCategoria"})
public class AgregarCategoriaServlet extends HttpServlet {

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    List<Categoria> categorias = listarCategorias();
    request.setAttribute("categorias", categorias); // No es necesario si generas HTML aquí
    StringBuilder htmlBuilder = new StringBuilder();
    for (Categoria categoria : categorias) {
        htmlBuilder.append("<tr>");
        htmlBuilder.append("<td>").append(categoria.getIdCategoria()).append("</td>");
        htmlBuilder.append("<td>").append(categoria.getNombreCategoria()).append("</td>");
        htmlBuilder.append("</tr>");
    }
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    out.print(htmlBuilder.toString()); // Imprimir solo las filas de la tabla
}

    private List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT idCategoria, Categoria FROM categoria";
        
        try (Connection conexion = GestionTutorial.establecerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                int idCategoria = rs.getInt("idCategoria");
                String nombreCategoria = rs.getString("Categoria");
                categorias.add(new Categoria(idCategoria, nombreCategoria));
            }
        } catch (SQLException ex) {
            System.err.println("Error al listar las categorías: " + ex.getMessage());
            // Aquí podrías manejar la excepción más específicamente o re-lanzarla.
        }
        
        return categorias;
    }
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 

        String nombreCategoria = request.getParameter("nombreCategoria");
        if (nombreCategoria != null && !nombreCategoria.isEmpty()) {
            agregarCategoria(nombreCategoria); // Usamos un método separado para agregar la categoría
            response.sendRedirect("Categorias.jsp"); // Redirigir de vuelta al JSP para ver la lista actualizada
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "El nombre de la categoría no puede estar vacío");
        }
    }

    private void agregarCategoria(String nombreCategoria) {
        try (Connection conexion = GestionTutorial.establecerConexion()) {
            String sql = "INSERT INTO categoria (Categoria) VALUES (?)";
            try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
                pstmt.setString(1, nombreCategoria);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println("Error al agregar categoría: " + ex.getMessage());
            // Podrías considerar propagar el error o manejarlo de manera más específica aquí
        }
    }




    
    /*private static List<Categoria> listarCategorias() {
    List<Categoria> categorias = new ArrayList<>();
    try (Connection conexion = GestionTutorial.establecerConexion()) {
    String sql = "SELECT idCategoria, nombreCategoria FROM categoria";  // Asegúrate de que las columnas se llamen así
    try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
    while (resultSet.next()) {
    int idCategoria = resultSet.getInt("idCategoria");
    String nombreCategoria = resultSet.getString("nombreCategoria");
    
    Categoria categoria = new Categoria(idCategoria, nombreCategoria);
    categorias.add(categoria);
    }
    }
    } catch (SQLException ex) {
    System.err.println("Error al listar las categorías: " + ex.getMessage());
    }
    return categorias;
    }
    */

}

