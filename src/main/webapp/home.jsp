<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title></title>
    </head>
    <body style="text-align:center;">
        <c:if test="${session != null}">
            <div style="font-size: 24">
                Hola <%= request.getAttribute("name") %>! <br>
                Estas dentro de tu sesi&oacute;n.
                Session ID = <c:out value="${session}"/>
                Session idUser = <c:out value="${userId}"/>
            </div>
        </c:if>
        <a href="/login.jsp">Regresar a la pagina de acceso.</a>

    </body>
</html>