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
            .noLogin{
                color: red;
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
            <h2>Nueva historia</h2>
            <p></p>

            <form method="post" action="/NuevaHist">
                <table>
                    <tr>
                        <td><label for="story">Porque tan indeciso? Cuentanos tu historia :</label></td>
                    </tr>
                    <tr>
                        <td><textarea name="story" id="story" rows="5" cols="50" maxlength="512"> </textarea></td>
                        <c:if test="${!empty histVacio}">
                            <td><c:out value="${histVacio}"/></td>
                        </c:if>
                    </tr>

                    <tr>
                        <td><label for="tema">Elige el tema :</label></td>
                    </tr>
                    <tr>
                        <td><select name='tema' id="tema">
                            <option value="${selec.getIdTopic()}" selected> <c:out value="${selec.getTopic()}"/></option>
                            <c:forEach var="tema" items="${temas}">
                                <c:if test="${tema.getIdTopic() != selec.getIdTopic()}">
                                    <option value="${tema.getIdTopic()}"> <c:out value="${tema.getTopic()}"/> </option>
                                </c:if>
                            </c:forEach>
                            </select>
                        </td>
                    </tr>
                </table>

                <p> Animate y espera a las respuestas!</p>
                <input type="submit" value="Publicar!"/>

            </form>

            <c:if test="${!empty noLogin}">
                <h2 class="noLogin"><a href="/Login">aqui</a></h2>
            </c:if>

        </div>
    </body>
</html>