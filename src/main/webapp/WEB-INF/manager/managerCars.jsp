<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style><%@include file="/CSS/loginPage.css"%></style>
<%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/CSS/loginPage.css"/>--%>
    <title>Cars</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Cars</h2>
        <%--        <h2><fmt:message key="Welcome"/></h2>--%>
        <%--        <p><fmt:message key="login_register"/></p>--%>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <c:if test="${sessionScope.role>0}">
            <form action="${pageContext.request.contextPath}/logout" method="post">
                <button type="submit" class="btn btn-secondary">Logout</button>
            </form>
        </c:if>





        <div class="login-form">
            <c:choose>
                <c:when test="${sessionScope.role==3}">
                    <form action="${pageContext.request.contextPath}/welcomeAdmin" method="post">
                        <input type="submit" value="back to menu" style="background-color: darkseagreen;border-width: medium;font-weight: bold">
                    </form>
                    <form action="${pageContext.request.contextPath}/adminaddcar" method="post">
                        <input type="submit" value="add car" style="background-color: coral;border-width: medium;font-weight: bold">
                    </form>
                </c:when>
                <c:when test="${sessionScope.role==2}">
                    <form action="${pageContext.request.contextPath}/welcomeManager" method="post">
                        <input type="submit" value="back to menu" style="background-color: darkseagreen;border-width: medium;font-weight: bold">
                    </form>
                </c:when>
            </c:choose>




<%--            <form action="${pageContext.request.contextPath}/adminaddcar" method="post">--%>
<%--                <input type="submit" value="add car" style="background-color: coral;border-width: medium;font-weight: bold">--%>
<%--            </form>--%>



            <hr>
            <c:forEach var="car" items="${requestScope.allCars}">
                <tr>
                    <td>
                        <ul>
                            <li>id: <c:out value="${car.id}"/></li>
                            <li>marque: <c:out value="${car.marque}"/></li>
                            <li>car class: <c:out value="${car.clazz}"/></li>
                            <li>model: <c:out value="${car.model}"/></li>
                            <li>price: <c:out value="${car.price}"/>
                                <form method="post" action="updatePrice">
                                    <input type="hidden" name="id" value="${car.id}">
                                    <input type="text" placeholder="price" name="price">
                                    <input type="submit" value="update">
                                </form>
                            </li>

                            <li>
<%--                                <table>--%>
<%--                                    <th>--%>
                                        status: <c:out value="${car.status}"/>
<%--                                    </th>--%>
<%--                                    <th>--%>
<%--                                        <form action=""--%>
<%--                                    </th>--%>
<%--                                </table>--%>





                            </li>

                        </ul>
                    </td>
                </tr>
                <c:if test="${sessionScope.role==3}">
                    <form action="${pageContext.request.contextPath}/adminDeleteCar">
                        <input type="hidden" name="carId" value="${car.id}">
                        <input type="submit" value="delete car">
                    </form>
                </c:if>

                <hr>
            </c:forEach>


        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>