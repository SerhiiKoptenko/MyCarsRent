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
<%--    <link rel="stylesheet" type="text/css" href="<c:url value="${pageContext.request.contextPath}/CSS/loginPage.css" />"/>--%>
    <title>Manager page</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <h2>Welcome, manager!</h2>
    </div>
</div>
<div class="container-fluid">
    <div class="row">

        <div class="col-md-4">
        </div>

        <div class="col-md-4">
        </div>

        <div class="col-md-4">
            <c:if test="${sessionScope.role>0}">
                <form action="${pageContext.request.contextPath}/logout" method="post">
                    <button type="submit" class="btn btn-secondary">Logout</button>
                </form>
            </c:if>
        </div>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
<%--        <c:if test="${sessionScope.role>0}">--%>
<%--            <form action="${pageContext.request.contextPath}/logout" method="post">--%>
<%--                <button type="submit" class="btn btn-secondary">Logout</button>--%>
<%--            </form>--%>
<%--        </c:if>--%>
        <div class="login-form">
            <hr>
            <table>
                <caption>Manager</caption>
                <tr>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerCars" method="post">
                            <input type="submit" value="all cars">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerClients" method="post">
                            <input type="submit" value="all users">
                        </form>
                    </th>
                    <th>
                        <form action="${pageContext.request.contextPath}/managerOrders" method="post">
                            <input type="submit" value="orders">
                        </form>
                    </th>
                </tr>
            </table>
            <hr>
        </div>
    </div>
</div>


<!-- Вариант 1: Bootstrap в связке с Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
        crossorigin="anonymous"></script>


</body>
</html>