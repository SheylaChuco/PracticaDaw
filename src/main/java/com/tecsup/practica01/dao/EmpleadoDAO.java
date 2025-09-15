package com.tecsup.practica01.dao;
import com.tecsup.practica01.model.Empleado;
import com.tecsup.practica01.util.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    // Listar todos los empleados
    public List<Empleado> listar() {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Empleado e = new Empleado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                e.setApellido(rs.getString("apellido"));
                e.setEmail(rs.getString("email"));
                e.setDepartamentoId(rs.getInt("departamento_id"));
                lista.add(e);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("Empleados encontrados: " + lista.size());

        return lista;
    }

    // Insertar un nuevo empleado
    public boolean insertar(Empleado empleado) {
        String sql = "INSERT INTO empleados(nombre, apellido, email, departamento_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setString(3, empleado.getEmail());
            pstmt.setInt(4, empleado.getDepartamentoId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Actualizar empleado existente
    public boolean actualizar(Empleado empleado) {
        String sql = "UPDATE empleados SET nombre = ?, apellido = ?, email = ?, departamento_id = ? WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setString(3, empleado.getEmail());
            pstmt.setInt(4, empleado.getDepartamentoId());
            pstmt.setInt(5, empleado.getId());

            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Eliminar empleado por id
    public boolean eliminar(int id) {
        String sql = "DELETE FROM empleados WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            return rows > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    // Buscar empleado por id
    public Empleado buscarPorId(int id) {
        String sql = "SELECT * FROM empleados WHERE id = ?";
        Empleado empleado = null;

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    empleado = new Empleado();
                    empleado.setId(rs.getInt("id"));
                    empleado.setNombre(rs.getString("nombre"));
                    empleado.setApellido(rs.getString("apellido"));
                    empleado.setEmail(rs.getString("email"));
                    empleado.setDepartamentoId(rs.getInt("departamento_id"));
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return empleado;
    }
    public List<Empleado> listarPorDepartamento(int departamentoId) {
        List<Empleado> lista = new ArrayList<>();
        String sql = "SELECT * FROM empleados WHERE departamento_id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, departamentoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Empleado e = new Empleado();
                    e.setId(rs.getInt("id"));
                    e.setNombre(rs.getString("nombre"));
                    e.setApellido(rs.getString("apellido"));
                    e.setEmail(rs.getString("email"));
                    e.setDepartamentoId(rs.getInt("departamento_id"));
                    lista.add(e);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return lista;
    }



}
