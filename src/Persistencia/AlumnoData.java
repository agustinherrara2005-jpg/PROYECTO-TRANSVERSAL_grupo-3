package Persistencia;

import Modelo.Alumno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class AlumnoData {
    private final Connection con;

    public AlumnoData(sbConexion conexion) {
        this.con = sbConexion.getConexion();
    }

    public boolean guardarAlumno(Alumno alumno) {
        String sql = "INSERT INTO alumno (`dni`, `nombre`, `apellido`, `fechaNacimiento`, `estado`) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, alumno.getDni());
            ps.setString(2, alumno.getNombre());
            ps.setString(3, alumno.getApellido());
             ps.setDate(4, new java.sql.Date(alumno.getFecha().getTime()));
            ps.setBoolean(5, alumno.isEstado());
            int affected = ps.executeUpdate();
            if (affected == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        alumno.setIdAlumno(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("guardarAlumno error: " + e.getMessage());
        }
        return false;
    }

    public Alumno buscarAlumno(int id) {
        String sql = "SELECT * FROM alumno WHERE idAlumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno a = new Alumno();
                    a.setIdAlumno(rs.getInt("idAlumno"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellido(rs.getString("apellido"));
                    a.setDni(rs.getString("dni"));
                    a.setEstado(rs.getBoolean("estado"));
                    return a;
                }
            }
        } catch (SQLException e) {
            System.err.println("buscarAlumno error: " + e.getMessage());
        }
        return null;
    }

    public List<Alumno> listarAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alumno";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                a.setDni(rs.getString("dni"));
                a.setEstado(rs.getBoolean("estado"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("listarAlumnos error: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET nombre = ?, apellido = ?, dni = ?, estado = ? WHERE idAlumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getDni());
            ps.setBoolean(4, alumno.isEstado());
            ps.setInt(5, alumno.getIdAlumno());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("actualizarAlumno error: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarAlumno(int id) {
        // soft-delete
        String sql = "UPDATE alumno SET estado = false WHERE idAlumno = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("eliminarAlumno error: " + e.getMessage());
        }
        return false;
    }
}
