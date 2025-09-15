package com.tecsup.practica01.servlet;
import com.tecsup.practica01.dao.DepartamentoDAO;
import com.tecsup.practica01.dao.EmpleadoDAO;
import com.tecsup.practica01.model.Departamento;
import com.tecsup.practica01.model.Empleado;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.List;
@WebServlet("/empleado")
public class EmpleadoServlet extends HttpServlet{
    private EmpleadoDAO empleadoDAO = new EmpleadoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "listar";

        switch (action) {
            case "nuevo":
                mostrarFormularioNuevo(request, response);
                break;
            case "editar":
                mostrarFormularioEditar(request, response);
                break;
            case "eliminar":
                eliminarEmpleado(request, response);
                break;
            default:
                listarEmpleados(request, response);
                break;
        }
    }


    private void listarEmpleados(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Parámetros de filtro
        String filtroDepartamentoStr = request.getParameter("departamentoId");
        Integer filtroDepartamento = null;

        if (filtroDepartamentoStr != null && !filtroDepartamentoStr.isEmpty()) {
            try {
                filtroDepartamento = Integer.parseInt(filtroDepartamentoStr);
            } catch (NumberFormatException e) {
                filtroDepartamento = null;
            }
        }

        // Obtener lista de empleados filtrando por departamento si aplica
        List<Empleado> listaEmpleados;
        if (filtroDepartamento != null && filtroDepartamento > 0) {
            listaEmpleados = empleadoDAO.listarPorDepartamento(filtroDepartamento);
        } else {
            listaEmpleados = empleadoDAO.listar();
        }

        // Cargar lista de departamentos para el desplegable
        DepartamentoDAO departamentoDAO = new DepartamentoDAO();
        List<Departamento> listaDepartamentos = departamentoDAO.listar();

        // Pasar datos a la JSP
        request.setAttribute("listaEmpleados", listaEmpleados);
        request.setAttribute("listaDepartamentos", listaDepartamentos);
        request.setAttribute("filtroDepartamento", filtroDepartamentoStr);

        request.getRequestDispatcher("empleado-list.jsp").forward(request, response);
    }



    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("empleado-form.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Empleado empleado = empleadoDAO.buscarPorId(id);
        request.setAttribute("empleado", empleado);
        request.getRequestDispatcher("empleado-form.jsp").forward(request, response);
    }

    private void eliminarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        empleadoDAO.eliminar(id);
        response.sendRedirect("empleado?action=listar");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("guardar".equals(accion)) {
            guardarEmpleado(request, response);
        } else if ("actualizar".equals(accion)) {
            actualizarEmpleado(request, response);
        } else {
            response.sendRedirect("empleado?action=listar");
        }
    }

    private void guardarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        int departamentoId = Integer.parseInt(request.getParameter("departamentoId"));

        Empleado empleado = new Empleado();
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEmail(email);
        empleado.setDepartamentoId(departamentoId);

        empleadoDAO.insertar(empleado);
        response.sendRedirect("empleado?action=listar");
    }

    private void actualizarEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        int departamentoId = Integer.parseInt(request.getParameter("departamentoId"));

        Empleado empleado = new Empleado();
        empleado.setId(id);
        empleado.setNombre(nombre);
        empleado.setApellido(apellido);
        empleado.setEmail(email);
        empleado.setDepartamentoId(departamentoId);

        empleadoDAO.actualizar(empleado);
        response.sendRedirect("empleado?action=listar");
    }
}
