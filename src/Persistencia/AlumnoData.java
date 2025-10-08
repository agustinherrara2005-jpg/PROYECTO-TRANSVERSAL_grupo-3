
package Persistencia;

import Modelo.Alumno;
import java.sql.*;
/**
 *
 * @author Admin
 */
public class AlumnoData {
    private final Connection con;

    public AlumnoData(sbConexion conexion) {
        this.con = sbConexion.getConexion();
    }

    public void guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (nombre, fechaNacimiento, activo) VALUES (?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alumno.getNombre());
            ps.setDate(2, Date.valueOf(alumno.getFechaDeNacimiento()));
            ps.setBoolean(3, alumno.isEstado());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) alumno.setIdAlumno(rs.getInt(1));
            System.out.println("Alumno guardado con ID: " + alumno.getIdAlumno());
        } catch (SQLException e) {
            System.out.println("Error al guardar alumno: " + e.getMessage());
        }
    }

    public Alumno buscarAlumno(int id) {
        Alumno a = null;
        String sql = "SELECT * FROM alumno WHERE id = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                a = new Alumno(
                    rs.getString("nombre"),
                    rs.getDate("fechaNacimiento").toLocalDate(),
                    rs.getBoolean("activo")
                );
                a.setIdAlumno(rs.getInt("id"));
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar alumno: " + e.getMessage());
        }
        return a;
    }
}