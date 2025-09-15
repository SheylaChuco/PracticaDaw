
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Departamentos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>
<body>
<div class="container mt-4">
    <h2>Departamentos</h2>
    <a href="departamento?action=nuevo" class="btn btn-success mb-3">Agregar Departamento</a>

    <table class="table table-bordered table-striped">
        <thead class="table-dark">
        <tr>
            <th>ID</th>
            <th>Nombre</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="departamento" items="${listaDepartamentos}">
            <tr>
                <td>${departamento.id}</td>
                <td>${departamento.nombre}</td>
                <td>
                    <a href="departamento?action=editar&id=${departamento.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="departamento?action=eliminar&id=${departamento.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Está seguro de eliminar este departamento?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
