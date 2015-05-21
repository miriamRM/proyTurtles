<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <h2>P&aacute;gina de acceso</h2>

        <c:choose>
            <c:when test = "${message != null}">
                <p style="color: red"> <c:out value="${message}"/> <a href="/hello-mvc-servlet">aqui</a></p>
            </c:when>
            <c:when test = "${good != null}">
                <p style="color: blue"> <c:out value="${good}"/>
            </c:when>
        </c:choose>

        <p>Por favor ingresa tu nombre</p>

        <form method="post" action="/login-mvc-servlet">

            <label for="name">Nombre:</label>
            <input type="text" name="name" id="name" />

            <input type="submit"/>
        </form>
    </body>
</html>