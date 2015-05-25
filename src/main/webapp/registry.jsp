<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <h2>Registro de Nuevo Usuario</h2>
        <Por favor llena toda la informaci&oacute;n que se te pide>

        <c:choose>
            <c:when test="${!empty nombreUsado}">

            </c:when>
        </c:choose>

        <form method="post" action="/Registro">
            <label for="name">Elige un username:</label>
            <input type="text" name="userName" id="userName" />
            <c:if test="${!empty nombreUsado}">
                <p style="color: red"> <c:out value="${nombreUsado}"/></p>
            </c:if>
            </br>

            <label for="name">Escribe tu e-mail:</label>
            <input type="email" name="email" id="email" />
            <c:if test="${!empty emailUsado}">
                <p style="color: red"> <c:out value="${emailUsado}"/></p>
            </c:if>
            </br>

            <label for="name">Escribe tu contraseña:</label>
            <input type="passwoerd" name="pwd" id="pwd" />
            </br>

            <input type="submit"/>
        </form>
    </body>
</html>