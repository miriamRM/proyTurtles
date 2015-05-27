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
                position: relative;
                left: 170px;
                margin-top: inherit;
                margin-right: inherit;
                margin-bottom: inherit;
                margin-left: inherit;
            }
        </style>
    </head>
    <body>
        <div>
            <h1>Tortugas y Dragones!</h1>
        </div>

        <div class="left">
            <p><a href="/Home">Inicio</a></p>
            <c:choose>
                <c:when test = "${userId == null}">
                    <p><a href="/Login">Login</a></p>
                    <p><a href="/Registro">Registro</a></p>
                </c:when>
                <c:when test="${userId != null}">
                    <h3>Bienvenido <h3><c:out value="${userName}"/>
                    <p><a href=""> Perfil </a></p>
                    <p><a href="/Home">Logout</a></p>
                    <p><a href="">Nueva Historia<a><p>
                </c:when>
            </c:choose>
        </div>

        <div class="center">
            <h2>Registro de Nuevo Usuario</h2>
            <p>Por favor llena toda la informaci&oacute;n que se te pide</p>

            <form method="post" action="/Registro">
                <table>
                    <tr>
                        <td><label for="name">Elige un username:</label></td>
                        <td><input type="text" name="userName" id="userName" /></td>
                        <c:if test="${!empty nombreUsado}">
                            <td><p style="color: red"> <c:out value="${nombreUsado}"/></p><td>
                        </c:if>
                    </tr>

                    <tr>
                        <td><label for="name">Escribe tu e-mail:</label></td>
                        <td><input type="email" name="email" id="email" /></td>
                        <c:if test="${!empty emailUsado}">
                            <td><p style="color: red"> <c:out value="${emailUsado}"/></p></td>
                        </c:if>
                    </tr>

                    <tr>
                        <td><label for="name">Escribe tu contrase&ntilde;a:</label></td>
                        <td><input type="password" name="pwd" id="pwd" /></td>
                        <td></td>
                    </tr>
                </table>
                <input type="submit" value="Reg&iacute;strame!"/>
            </form>
        </div>
    </body>
</html>