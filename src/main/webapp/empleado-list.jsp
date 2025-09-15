<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Lista de Empleados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</head>
<body>
<div class="container mt-4">
    <h2>Empleados</h2>
    <a href="empleado?action=nuevo" class="btn btn-success mb-3">Agregar Empleado</a>

    <!-- Formulario de filtro por departamento -->
    <form action="empleado" method="get" class="mb-3">
        <input type="hidden" name="action" value="listar" />
        <div class="input-group">
            <select name="departamentoId" class="form-select">
                <option value="">Todos los departamentos</option>
                <c:forEach var="depto" items="${listaDepartamentos}">
                    <option value="${depto.id}" ${filtroDepartamento == depto.id.toString() ? "selected" : ""}>
                            ${depto.nombre}
                    </option>
                </c:forEach>
            </select>
            <button type="submit" class="btn btn-primary ms-2">Filtrar</button>
        </div>
    </form>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Departamento ID</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="empleado" items="${listaEmpleados}">
            <tr>
                <td>${empleado.id}</td>
                <td>${empleado.nombre}</td>
                <td>${empleado.apellido}</td>
                <td>${empleado.email}</td>
                <td>${empleado.departamentoId}</td>
                <td>
                    <a href="empleado?action=editar&id=${empleado.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="empleado?action=eliminar&id=${empleado.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Está seguro de eliminar este empleado?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
