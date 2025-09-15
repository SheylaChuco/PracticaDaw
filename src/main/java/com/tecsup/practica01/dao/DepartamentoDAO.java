package com.tecsup.practica01.dao;
import com.tecsup.practica01.model.Departamento;
import com.tecsup.practica01.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class DepartamentoDAO {
    // Método para listar todos los departamentos
    public List<Departamento> listar() {
        List<Departamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM departamentos";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Departamento d = new Departamento();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                lista.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    // Insertar un nuevo departamento
    public boolean insertar(Departamento departamento) {
        String sql = "INSERT INTO departamentos(nombre) VALUES (?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departamento.getNombre());
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Actualizar departamento existente
    public boolean actualizar(Departamento departamento) {
        String sql = "UPDATE departamentos SET nombre = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, departamento.getNombre());
            pstmt.setInt(2, departamento.getId());
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Eliminar departamento por id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM departamentos WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar departamento por id
    public Departamento buscarPorId(int id) {
        String sql = "SELECT * FROM departamentos WHERE id = ?";
        Departamento departamento = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    departamento = new Departamento();
                    departamento.setId(rs.getInt("id"));
                    departamento.setNombre(rs.getString("nombre"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return departamento;
    }


}
