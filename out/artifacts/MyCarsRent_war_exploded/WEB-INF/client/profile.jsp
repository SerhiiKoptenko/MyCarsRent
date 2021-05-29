<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
          crossorigin="anonymous">

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <style>
        <%@include file="/CSS/loginPage.css" %>
    </style>
    <%--    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}CSS/loginPage.css"/>--%>
    <title>Profile</title>

</head>
<body>

<div class="sidenav">
    <div class="login-main-text">
        <form action="${pageContext.request.contextPath}/menu" method="post">
            <input type="submit" value="back to menu"
                   style="background-color: darkseagreen;border-width: medium;font-weight: bold">
        </form>

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
<!-- ================================================= -->
<div class="container-fluid">
    <div class="row">
        <div class="col-md-3">
        </div>
        <div class="col-md-7">
            <c:choose>
                <c:when test="${requestScope.orders.size()>0}">
                    <c:set var="counter" value="0" scope="page"/>
                    <table class="fl-table">
                        <tr>
                            <th>Order ID</th>
                            <th>Car marque</th>
                            <th>Car model</th>
                            <th>Driver</th>
                            <th>Term</th>
                            <th>OrderStatus</th>
                            <th>comment</th>
                            <th>Rent cost</th>
                            <th>Penalty</th>
                            <th>Total cost</th>
                            <th></th>
                        </tr>
                        <c:forEach var="order" items="${requestScope.orders}">
                            <tr>
                                <td><c:out value="${order.id}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).marque}"/></td>
                                <td><c:out value="${requestScope.cars.get(counter).model}"/></td>
                                <td><c:out value="${order.driver}"/></td>
                                <td><c:out value="${order.term}"/></td>
                                <td><c:out value="${order.confirmed}"/></td>
                                <td><c:out value="${order.comment}"/></td>
                                <td><c:out value="${order.rent_cost}"/></td>
                                <td><c:out value="${order.penalty}"/></td>
                                <td><c:out value="${order.total_cost}"/></td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.confirmed.equals('CONFIRMED')}">
                                            <form>
                                                <button type="submit" class="btn btn-secondary">Pay</button>
                                            </form>
                                        </c:when>
                                        <c:when test="${order.confirmed.equals('ON CHECK')}">
                                            <form action="${pageContext.request.contextPath}/cancelOrder" method="post">
                                                <input type="hidden" name="orderId" value="${order.id}">
                                                <button type="submit" class="btn btn-secondary">cancel</button>
                                            </form>
                                        </c:when>
                                    </c:choose>
                                </td>
                            </tr>
                            <c:set var="counter" value="${counter+1}" scope="page"/>
                        </c:forEach>
                    </table>
                </c:when>
                <c:otherwise>
                    <p class="redText">Sorry, no available cars</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="col-md-2">
    </div>
</div>
<!-- ================================================= -->
<div class="main">
    <div class="container" style="position: static; bottom: 30%">
        <table>
            <c:set var="i" value="1" scope="page"/>
            <c:forEach begin="1" end="${requestScope.numPages}">
                <th style="font-size: medium">
                    <form action="${pageContext.request.contextPath}/profile" method="post">
                        <input type="hidden" name="car_class" value="${requestScope.car_class}">
                        <input type="hidden" name="page" value="${i}">
                        <input type="submit" value="${i}">
                    </form>
                    <c:set var="i" value="${i+1}" scope="page"/>
                </th>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6 col-sm-12">
    </div>
    <!-- Вариант 1: Bootstrap в связке с Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
            crossorigin="anonymous"></script>

</body>
</html>