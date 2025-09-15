
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Formulario Empleado</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
</head>
<body>
<div class="container mt-4">
    <h2>${empleado != null ? 'Editar Empleado' : 'Nuevo Empleado'}</h2>
    <form action="empleado" method="post">
        <input type="hidden" name="accion" value="${empleado != null ? 'actualizar' : 'guardar'}"/>
        <c:if test="${empleado != null}">
            <input type="hidden" name="id" value="${empleado.id}"/>
        </c:if>

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" name="nombre" id="nombre" class="form-control" required
                   value="${empleado != null ? empleado.nombre : ''}"/>
        </div>

        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido:</label>
            <input type="text" name="apellido" id="apellido" class="form-control" required
                   value="${empleado != null ? empleado.apellido : ''}"/>
        </div>

        <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" name="email" id="email" class="form-control"
                   value="${empleado != null ? empleado.email : ''}"/>
        </div>

        <div class="mb-3">
            <label for="departamentoId" class="form-label">Departamento ID:</label>
            <input type="number" name="departamentoId" id="departamentoId" class="form-control" required
                   value="${empleado != null ? empleado.departamentoId : ''}"/>
        </div>

        <button type="submit" class="btn btn-primary">${empleado != null ? 'Actualizar' : 'Guardar'}</button>
        <a href="empleado?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>

</body>
</html>
