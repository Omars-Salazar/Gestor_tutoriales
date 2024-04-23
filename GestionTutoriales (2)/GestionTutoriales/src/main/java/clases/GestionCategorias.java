/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Omar Salazar
 */


public class GestionCategorias {
    
    

    public List<Categoria> listarCategorias() {
    List<Categoria> categorias = new ArrayList<>();
    try (Connection conexion = new GestionTutorial().establecerConexion()) {
        conexion.prepareStatement("USE Tutorial;").execute();
        String sql = "SELECT * FROM categoria";
        try (PreparedStatement statement = conexion.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(resultSet.getInt("IdCategoria"));
                categoria.setNombreCategoria(resultSet.getString("Categoria"));
                categorias.add(categoria);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar las categorías: " + ex.getMessage());
    }
        return categorias;   
}
    // Método estático para agregar una nueva categoría
    public static void agregarCategoria(String nombreCategoria) {
        // Sentencia SQL para insertar una nueva categoría en la tabla 'categorias'
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection conexion = GestionTutorial.establecerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            // Establecer el valor del parámetro en la sentencia SQL
            pstmt.setString(1, nombreCategoria);

            // Ejecutar la sentencia SQL para insertar la nueva categoría
            pstmt.executeUpdate();

            System.out.println("¡Categoría agregada exitosamente!");

        } catch (SQLException ex) {
            System.err.println("Error al agregar la categoría: " + ex.getMessage());
        }
    }
    
    // Método estático para eliminar una categoría por su ID
    public static void eliminarCategoria(int idCategoria) {
        String sql = "DELETE FROM categoria WHERE idCategoria = ?";

        try (Connection conexion = GestionTutorial.establecerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(sql)) {

            // Establecer el valor del parámetro en la sentencia SQL
            pstmt.setInt(1, idCategoria);

            // Ejecutar la sentencia SQL
            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Categoría eliminada exitosamente.");
            } else {
                System.out.println("No se encontró la categoría con ID: " + idCategoria);
            }

        } catch (SQLException ex) {
            System.err.println("Error al eliminar la categoría: " + ex.getMessage());
        }
    }
    
   
// Método estático para editar una categoría existente por ID
public static void editarCategoria(int idCategoria, String nuevoNombre) throws SQLException {
    // Sentencia SQL para actualizar una categoría en la tabla 'categoria'
    String sql = "UPDATE categoria SET Categoria = ? WHERE idCategoria = ?";

    try (Connection conexion = GestionTutorial.establecerConexion();
         PreparedStatement pstmt = conexion.prepareStatement(sql)) {

        // Establecer los valores de los parámetros en la sentencia SQL
        pstmt.setString(1, nuevoNombre);
        pstmt.setInt(2, idCategoria);

        // Ejecutar la sentencia SQL
        int affectedRows = pstmt.executeUpdate();
        if (affectedRows == 0) {
            System.out.println("No se encontró la categoría con ID: " + idCategoria);
        } else {
            System.out.println("Categoría actualizada exitosamente.");
        }

    } catch (SQLException ex) {
        System.err.println("Error al actualizar la categoría: " + ex.getMessage());
        throw ex; // Propagar el error para manejarlo en el servlet
    }
}


}

