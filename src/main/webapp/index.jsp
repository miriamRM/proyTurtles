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
            <h2>Lo mas reciente</h2>

            <!-- Mientras haya historias que mostrar, mostrarlas sino mostrar que no hay ninguna historia en la bd-->
            <c:forEach var="item" items="${historias}">
                    <p><c:out value="${item.getStory()}"/></p>
                    idStory : <c:out value="${item.getIdStory()}"/></br>
                    idUser : <c:out value="${item.getIdUser()}"/></br>
                    idTopic : <c:out value="${item.getIdTopic()}"/></br>
                    <c:choose>
                        <c:when test="${item.getVotes() != 0}">
                            A favor :<c:out value="${item.getUp() * 100 / item.getVotes()}"/> %</br>
                            En contra :<c:out value="${item.getDown() * 100 / item.getVotes()}"/> %</br>
                        </c:when>
                        <c:when test="${item.getVotes() == 0}">
                            A favor : 0 %</br>
                            En contra : 0 %</br>
                        </c:when>
                    </c:choose>
                    <p><a href="">seguir viendo...<a></p>
            </c:forEach>
            <c:forEach var="i" begin="0" end="${totPag}">
                <a href="?pag=${i+1}"> <c:out value="${i+1}"/> </a>
            </c:forEach>
        </div>

    </body>
</html>