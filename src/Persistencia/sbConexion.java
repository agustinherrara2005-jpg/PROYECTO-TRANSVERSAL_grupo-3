package Persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase utilitaria para obtener la conexión a MySQL.
 * Configurada para:
 *   - BD: universdadulp_3
 *   - Host: localhost
 *   - Puerto: 3306
 *   - Usuario: root
 *   - Contraseña: ""
 *
 * Cambiar credenciales si corresponde.
 */
public class sbConexion {
    private static final String URL = "jdbc:mysql://localhost:3306/gp3_nombre_base?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = ""; // cambiar si corresponde

    private static Connection conexion = null;

    public sbConexion() {
        // constructor privado
    }

    public static Connection getConexion() {
        if (conexion == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexion = DriverManager.getConnection(URL, USER, PASS);
            } catch (ClassNotFoundException e) {
                System.err.println("Driver JDBC no encontrado: " + e.getMessage());
            } catch (SQLException e) {
                System.err.println("Error conectando a la DB: " + e.getMessage());
            }
        }
        return conexion;
    }

    public static void cerrarConexion() {
        if (conexion != null) {
            try {
                if (!conexion.isClosed()) {
                    conexion.close();
                }
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            } finally {
                conexion = null;
            }
        }
    }
}
