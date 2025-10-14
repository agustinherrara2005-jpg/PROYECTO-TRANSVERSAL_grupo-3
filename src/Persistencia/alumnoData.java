/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import modelo.alumno;
import modelo.conexion;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class alumnoData {

    public alumnoData() {}

    public int insertar(alumno alumno) {
        String sql = "INSERT INTO alumno (nombre, apellido, dni, fechaNacimiento, activo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getDni());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isActivo());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    int id = rs.getInt(1);
                    alumno.setIdAlumno(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar: " + e.getMessage());
        }
        return -1;
    }

    public List<alumno> obtenerTodos() {
        List<alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                alumno a = mapRowToAlumno(rs);
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener alumnos: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizar(alumno alumno) {
        String sql = "UPDATE alumno SET nombre=?, apellido=?, dni=?, fechaNacimiento=?, activo=? WHERE idAlumno=?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getDni());
            ps.setDate(4, Date.valueOf(alumno.getFechaNacimiento()));
            ps.setBoolean(5, alumno.isActivo());
            ps.setInt(6, alumno.getIdAlumno());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
        }
        return false;
    }

    public boolean bajaLogica(int id) {
        String sql = "UPDATE alumno SET activo = FALSE WHERE idAlumno = ?";
        try (Connection conn = conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println(" Error en baja lógica: " + e.getMessage());
        }
        return false;
    }

    private alumno mapRowToAlumno(ResultSet rs) throws SQLException {
        int id = rs.getInt("idAlumno");
        String nombre = rs.getString("nombre");
        String apellido = rs.getString("apellido");
        int dni = rs.getInt("dni");
        Date fecha = rs.getDate("fechaNacimiento");
        boolean activo = rs.getBoolean("activo");
        LocalDate fechaNac = (fecha != null) ? fecha.toLocalDate() : null;
        return new alumno(id, nombre, apellido, dni, fechaNac, activo);
    }
}
