<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Menú Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>
<body>
<div class="container mt-4">
    <h1>Proyecto Practica01</h1>
    <p>Seleccione una opción para gestionar:</p>
    <ul class="list-group">
        <li class="list-group-item">
            <a href="empleado?action=listar" class="btn btn-primary">Gestión de Empleados</a>
        </li>
        <li class="list-group-item">
            <a href="departamento?action=listar" class="btn btn-primary">Gestión de Departamentos</a>
        </li>
    </ul>
</div>
</body>
</html>