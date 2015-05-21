<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title></title>
    </head>
    <body style="text-align:center;">
        <div style="font-size: 24">
        Hola <%= request.getParameter("name") %>! <br>
        Estas dentro de tu sesi&oacute;n.
        </div>
        <a href="/login-mvc-servlet">Regresar a la pagina de acceso.</a>
    </body>
</html>