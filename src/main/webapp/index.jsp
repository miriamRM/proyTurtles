<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <h1>MIRIAAAAM!!!! INDEEEX!</h1>
        <!-- EN ESTA PAGINA SE DEBEN PRESENTAR TODAS LAS HISTORIAS QUE HAY -->
        <div>
            HISTORIAS
        </div>
        <!-- Mientras haya historias que mostrar, mostrarlas -->
        <div>
            <c:out value="${Historia}"/>
            <form method="post" >
                <input type="submit"> <c:out value="${porcientoUp}"/>
                <input type="submit"> <c:out value="${porcentajeDown}">
            </form>
        </div>
    </body>
</html>