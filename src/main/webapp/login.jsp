<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <style>
            .left {
                position: relative;
                top: 50px;
                width: 150px;
                padding-left: 5px;
            }
            .center{
                margin-left: 170px;
                left: 170px;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Tortugas y Dragones!</h1>
        </div>

        <div class="left">
            <p><a href="/HomeE">Inicio</a></p>
            <c:choose>
                <c:when test = "${userId == null}">
                    <p><a href="/Login">Login</a></p>
                    <p><a href="/Registro">Registro</a></p>
                </c:when>
                <c:when test="${userId != null}">
                    <h3>Bienvenido <h3><c:out value="${userName}"/>
                    <p><a href=""> Perfil </a></p>
                    <p><a href="/Home">Logout</a></p>
                </c:when>
            </c:choose>
            <p><a href="/NuevaHist">Nueva Historia<a><p>
        </div>
        <div class="center">
            <h2>P&aacute;gina de acceso a tu cuenta</h2>

            <form method="post" action="/Login">

                <label for="email">Email:</label>
                <input type="email" name="email" id="email" />
                </br>

                <label for="pass">Contrase&ntilde;a</label>
                <input type="password" name="pass" id="pass">
                </br>

                <input type="submit" value="Acceder"/>
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
        </div>
    </body>
</html>