/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clases;


import static clases.GestionTutorial.establecerConexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omar Salazar
 */
public class GestionTutorial {

    public static Connection establecerConexion() {
        String url = "jdbc:mysql://localhost:3306/Tutoriales?serverTimeZone=utc";
        String user = "root";
        String password = "admin";
        Connection conn = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            if (conn != null) {
                System.out.println("Conexion exitosa");
            }
        } catch (Exception e) {
            System.out.println("Error de conexion" + e.getMessage());
        }
        return conn;
    }

   

    public void AgregarTutorial(String Nombre, String URL, String Estado, int Prioridad, int idcategoria, Connection establecerConexion) {
        if (establecerConexion != null) {
            try {
                establecerConexion.prepareStatement("USE Tutoriales;").execute();

                CallableStatement stmt = establecerConexion.prepareCall("{call NuevoTutorial(?, ?, ?, ?, ?)}");

                stmt.setString(1, Nombre);
                stmt.setString(2, URL);
                stmt.setString(3, Estado);
                stmt.setInt(4, Prioridad);
                stmt.setInt(5, idcategoria);

                stmt.execute();

                establecerConexion.close();

                System.out.println("Conexion exitosa te amo rey ");

            } catch (SQLException e) {

                System.err.println("Error no se agrego" + e.getMessage());
            }
        } else {
            System.err.println("No se pudo establecer conecion con base de datos");
        }
    }
   public static List<Tutorial> listarTutoriales() {
    List<Tutorial> tutoriales = new ArrayList<>();
    try (Connection conexion = GestionTutorial.establecerConexion()) {
        conexion.prepareStatement("USE Tutoriales;").execute();
        String sql = "SELECT * FROM tutorial";  // Asegúrate de que la tabla se llama 'tutorial' en tu base de datos
        try (PreparedStatement statement = conexion.prepareStatement(sql); ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Tutorial tutorial = new Tutorial(
                    resultSet.getInt("idTutorial"),
                    resultSet.getString("tutorial"),  // Cambiado de 'nombre' a 'titulo'
                    resultSet.getString("titulo"),  // Asumiendo que quieres usar 'titulo' para el campo 'tutorial' también
                    resultSet.getString("URL"),     // 'URL' debe coincidir con la definición en la base de datos
                    resultSet.getString("estado"),
                    resultSet.getInt("prioridad"),
                    resultSet.getInt("Categoria"),  // Asumiendo que 'Categoria' y 'idCategoria' son lo mismo
                    resultSet.getInt("idCategoria")  // Duplicado para cumplir con la cantidad de parámetros
                );
                tutoriales.add(tutorial);
            }
        }
    } catch (SQLException ex) {
        System.out.println("Error al listar los tutoriales: " + ex.getMessage());
    }
    return tutoriales;
}
// Método estático para eliminar un tutorial por título
public static void eliminarTutorial(String titulo) {
    try (Connection conexion = establecerConexion()) {
        String sql = "DELETE FROM tutorial WHERE titulo = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setString(1, titulo); // Cambio aquí: usar setString para un título.
            pstmt.executeUpdate();
        }
    } catch (SQLException ex) {
        System.err.println("Error al eliminar tutorial: " + ex.getMessage());
    }
}

   public static void editarTutorial(int idTutorial, String titulo, String url, String estado, int prioridad, int idCategoria) throws SQLException {
        // Obtener la conexión a la base de datos.
        try (Connection conexion = GestionTutorial.establecerConexion();
             PreparedStatement pstmt = conexion.prepareStatement(
                 "UPDATE tutorial SET titulo = ?, URL = ?, estado = ?, prioridad = ?, idCategoria = ? WHERE idTutorial = ?")) {

            // Configurar los parámetros del PreparedStatement.
            pstmt.setString(1, titulo);
            pstmt.setString(2, url);
            pstmt.setString(3, estado);
            pstmt.setInt(4, prioridad);
            pstmt.setInt(5, idCategoria);
            pstmt.setInt(6, idTutorial);

            // Ejecutar la actualización.
            int affectedRows = pstmt.executeUpdate();

            // Verificar si la actualización fue exitosa.
            if (affectedRows == 0) {
                throw new SQLException("Actualizar el tutorial falló, no se afectaron filas.");
            }
        } catch (SQLException ex) {
            System.err.println("Error al editar tutorial: " + ex.getMessage());
            throw ex; // Re-lanzar la excepción para permitir un manejo más específico en niveles superiores.
        }
    }


}


