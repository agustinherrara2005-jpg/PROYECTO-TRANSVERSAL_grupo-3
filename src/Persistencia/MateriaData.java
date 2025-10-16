package Persistencia;

import Modelo.Materia;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MateriaData {
    private final Connection con;

    public MateriaData(sbConexion conexion) {
        this.con = sbConexion.getConexion();
    }

    public boolean guardarMateria(Materia m) {
        String sql = "INSERT INTO materia (nombre,anio, estado) VALUES (?,?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, m.getNombre());
            ps.setInt(2, m.getAnio());
            ps.setBoolean(3, m.isEstado());
            int affected = ps.executeUpdate();
            if (affected == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        m.setIdMateria(rs.getInt(1));
                    }
                }
                return true;
            }
        } catch (SQLException e) {
            System.err.println("guardarMateria error: " + e.getMessage());
        }
        return false;
    }

    public Materia buscarMateria(int id) {
        String sql = "SELECT * FROM materia WHERE idMateria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Materia m = new Materia();
                    m.setIdMateria(rs.getInt("idMateria"));
                    m.setNombre(rs.getString("nombre"));
                    m.setEstado(rs.getBoolean("estado"));
                    return m;
                }
            }
        } catch (SQLException e) {
            System.err.println("buscarMateria error: " + e.getMessage());
        }
        return null;
    }

    public List<Materia> listarMaterias() {
        List<Materia> lista = new ArrayList<>();
        String sql = "SELECT * FROM materia";
        try (PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Materia m = new Materia();
                m.setIdMateria(rs.getInt("idMateria"));
                m.setNombre(rs.getString("nombre"));
                m.setEstado(rs.getBoolean("estado"));
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("listarMaterias error: " + e.getMessage());
        }
        return lista;
    }

    public boolean actualizarMateria(Materia m) {
        String sql = "UPDATE materia SET nombre = ?, estado = ? WHERE idMateria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, m.getNombre());
            ps.setBoolean(2, m.isEstado());
            ps.setInt(3, m.getIdMateria());
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("actualizarMateria error: " + e.getMessage());
        }
        return false;
    }

    public boolean eliminarMateria(int id) {
        String sql = "UPDATE materia SET estado = false WHERE idMateria = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            System.err.println("eliminarMateria error: " + e.getMessage());
        }
        return false;
    }
}
