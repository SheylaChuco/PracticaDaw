
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Formulario Departamento</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>

</head>
<body>
<div class="container mt-4">
    <h2>${departamento != null ? 'Editar Departamento' : 'Nuevo Departamento'}</h2>
    <form action="departamento" method="post">
        <input type="hidden" name="accion" value="${departamento != null ? 'actualizar' : 'guardar'}"/>
        <c:if test="${departamento != null}">
            <input type="hidden" name="id" value="${departamento.id}"/>
        </c:if>

        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" name="nombre" id="nombre" class="form-control" required
                   value="${departamento != null ? departamento.nombre : ''}"/>
        </div>

        <button type="submit" class="btn btn-primary">${departamento != null ? 'Actualizar' : 'Guardar'}</button>
        <a href="departamento?action=listar" class="btn btn-secondary">Cancelar</a>
    </form>
</div>
</body>
</html>
