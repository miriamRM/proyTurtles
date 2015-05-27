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
            .historia{
                border: 1px solid black;
                padding: 5px;
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
            <h2><a href="/Perfil?Uid=${idUser}"><c:out value="${user}"/></a> escribi&oacute; la siguiente historia:</h2>
            <table class="historia">
                <tr>
                    <td class="historia"> <h3> <p> <c:out value="${historia.getStory()}"/> </p> </h3> </td>
                </tr>
            </table>
            </br>Su historia es sobre <i><c:out value="${tema}"/></i></br>
            <p>Que opinas, que se anime o no? VOTA </p>
            <form method="post">
                <table style="width:50%">
                    <tr>
                        <td><c:out value="${contra}"/> %</td>
                        <td><input type="submit" name="downButton" value="Tortuga :s"/></td>
                        <td><input type="submit" name="upButton" value="Dragon :D"/></td>
                        <td><c:out value="${favor}"/> %</td>
                    </tr>
                </table>
            </form>
            <p></br></br></p>
            <div>
                <p>Agrega un comentario:</p>
                <form>
                    <textarea name="comentario" rows="5" cols="50" maxlength="256">
                    </textarea>

                </form>
            </div>
        </div>

    </body>
</html>