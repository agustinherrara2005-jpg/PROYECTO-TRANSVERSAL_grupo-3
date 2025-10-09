package Persistencia;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InscripcionData {
    private final Connection con;

    public InscripcionData(sbConexion conexion) {
        this.con = sbConexion.getConexion();
    }

    public boolean guardarInscripcion(Inscripcion ins) {
        String sql = "INSERT INTO inscripcion (idAlumno, idMateria, fecha, estado) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ins.getAlumno().getIdAlumno());
            ps.setInt(2, ins.getMateria().getIdMateria());
            ps.setDate(3, new java.sql.Date(ins.getFecha().getTime()));
            ps.setBoolean(4, ins.isEstado());
            int affected = ps.executeUpdate();
            if (affected == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        ins.setIdInscripcion(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("guardarInscripcion error: " + e.getMessage());
        }
        return false;
    }

    public List<Inscripcion> listarInscripciones() {
        List<Inscripcion> lista = new ArrayList<>();
        String sql = "SELECT i.idInscripcion, i.fecha, i.estado, " +
                     "a.idAlumno, a.nombre AS nomA, a.apellido AS apeA, a.dni AS dniA, " +
                     "m.idMateria, m.nombre AS nomM " +
                     "FROM inscripcion i " +
                     "JOIN alumno a ON i.idAlumno = a.idAlumno " +
                     "JOIN materia m ON i.idMateria = m.idMateria";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNombre(rs.getString("nomA"));
                a.setApellido(rs.getString("apeA"));
                a.setDni(rs.getString("dniA"));

                Materia m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nomM"));

                Inscripcion ins = new Inscripcion();
                ins.setIdInscripcion(rs.getInt("idInscripcion"));
                ins.setAlumno(a);
                ins.setMateria(m);
                ins.setFecha(rs.getDate("fecha"));
                ins.setEstado(rs.getBoolean("estado"));

                lista.add(ins);
            }
        } catch (SQLException e) {
            System.err.println("listarInscripciones error: " + e.getMessage());
        }
        return lista;
    }

    public boolean eliminarInscripcion(int id) {
        String sql = "UPDATE inscripcion SET estado = false WHERE idInscripcion = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("eliminarInscripcion error: " + e.getMessage());
        }
        return false;
    }
}
