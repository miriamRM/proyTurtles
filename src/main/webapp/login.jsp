<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <h2>P&aacute;gina de acceso a tu cuenta</h2>

        <form method="post" action="/Login">

            <label for="email">email:</label>
            <input type="email" name="email" id="email" />
            </br>

            <label for="pass">contrase&ntilde;a</label>
            <input type="password" name="pass" id="pass">
            </br>

            <input type="submit"/>
        </form>
        <c:choose>
            <c:when test = "${message != null}">
                <p style="color: red"> <c:out value="${message}"/> <a href="/Registro">aqui</a></p>
            </c:when>
            <c:when test = "${good != null}">
                <p style="color: blue"> <c:out value="${good}"/>
            </c:when>
            <c:when test = "${malPass != null}">
                <p style="color: red"> <c:out value="${malPass}"/></p>
            </c:when>
        </c:choose>
    </body>
</html>