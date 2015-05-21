<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <h2>Bienvenido!</h2>
        <Por favor llena toda la informaci&oacute;n que se te pide>

        <form method="post" action="/hello-mvc-servlet">
            <label for="name">Cu&aacute;l es tu nombre:</label>
            <input type="text" name="name" id="name" />

            <label for="name">Cu&aacute;l es tu apellido:</label>
            <input type="text" name="lastName" id="lastName" />

            <input type="submit"/>
        </form>
    </body>
</html>