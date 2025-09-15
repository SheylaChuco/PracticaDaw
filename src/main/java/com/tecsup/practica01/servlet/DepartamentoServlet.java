package com.tecsup.practica01.servlet;
import com.tecsup.practica01.dao.DepartamentoDAO;
import com.tecsup.practica01.model.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;

@WebServlet("/departamento")
public class DepartamentoServlet extends HttpServlet {
    private DepartamentoDAO departamentoDAO = new DepartamentoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "listar":
                listarDepartamentos(request, response);
                break;
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarDepartamento(request, response);
                break;
            default:
                listarDepartamentos(request, response);
                break;
        }
    }

    private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Departamento> lista = departamentoDAO.listar();
        request.setAttribute("listaDepartamentos", lista);
        request.getRequestDispatcher("departamento-list.jsp").forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("departamento-form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Departamento departamento = departamentoDAO.buscarPorId(id);
        request.setAttribute("departamento", departamento);
        request.getRequestDispatcher("departamento-form.jsp").forward(request, response);
    }

    private void eliminarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departamentoDAO.eliminar(id);
        response.sendRedirect("departamento?action=listar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardarDepartamento(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarDepartamento(request, response);
        } else {
            response.sendRedirect("departamento?action=listar");
        }
    }

    private void guardarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");

        Departamento departamento = new Departamento();
        departamento.setNombre(nombre);

        departamentoDAO.insertar(departamento);
        response.sendRedirect("departamento?action=listar");
    }

    private void actualizarDepartamento(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");

        Departamento departamento = new Departamento();
        departamento.setId(id);
        departamento.setNombre(nombre);

        departamentoDAO.actualizar(departamento);
        response.sendRedirect("departamento?action=listar");
    }
}
