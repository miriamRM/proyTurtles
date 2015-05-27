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
            }
        </style>
    </head>
    <body>
        <!-- EN ESTA PAGINA SE DEBEN PRESENTAR TODAS LAS HISTORIAS QUE HAY -->
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
                </c:when>
            </c:choose>
            <p><a href="/NuevaHist">Nueva Historia<a><p>
        </div>

        <div class="center">
            <h2>Registro de Nuevo Usuario</h2>
            <p>Por favor llena toda la informaci&oacute;n que se te pide</p>

            <form method="post" action="/Registro">
                <table>
                    <tr><label for="story">Porque tan indeciso? Cuentanos tu historia :</label></tr>
                    <tr><textarea name="story" id="story" rows="5" cols="50" maxlength="512"> </textarea></tr>

                    <tr><label for="tema">Elige el tema :</label></tr>
                    <tr><select name='role'>
                        <option value="${seleccion}" selected>${selected}</option>
                        <c:forEach var="tema" items="${temas}">
                            <c:if test="${tema != selected}">
                                <option value="${tema}"> <c:out value="${role}"/> </option>
                            </c:if>
                        </c:forEach>
                        </select>
                    </tr>
                </table>
                <p> Animate y espera a las respuestas!</p>
                <input type="submit" value="Publicar!"/>
            </form>
        </div>
    </body>
</html>