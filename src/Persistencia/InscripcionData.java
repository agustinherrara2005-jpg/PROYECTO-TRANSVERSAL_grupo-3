package Persistencia;

import Modelo.Alumno;
import Modelo.Materia;
import Modelo.Inscripcion;
import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class InscripcionData {
    private final Connection con;

    public InscripcionData(sbConexion conexion) {
        this.con = sbConexion.getConexion();
    }

    public boolean guardarInscripcion(Inscripcion ins) {
        String sql = "INSERT INTO inscripcion (idInscripcion, nota, idAlumno, idMateria) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ins.getIdInscripcion());
            ps.setInt(2, ins.getNota());
            ps.setInt(3, ins.getAlumno().getIdAlumno());
            ps.setInt(4, ins.getMateria().getIdMateria());
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
