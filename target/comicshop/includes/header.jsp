<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style/main.css'/>">
    <link rel="icon" type="image/x-icon" href="<c:url value='/static/images/favicon.ico'/>" />
    <title>COMICSHOP</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 padding-20">
                    <h1 class="logo"><a href="<c:url value='/'/>">COMIC<span>SHOP</span></a></h1>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="login col-sm-5">
                            <c:choose>
                                <c:when test="${user.role == 0}">
                                    <p>${user.lastName} ${user.firstName}</p>
                                    <div class="dropdown">
                                        <a class="dropbtn">Мой кабинет</a>
                                        <div class="dropdown-content">
                                            <a href="<c:url value='/order/show'/>">Мои заказы</a>
                                            <a href="<c:url value='/logout'/>">Выйти</a>
                                        </div>
                                    </div>
                                </c:when>
                                <c:when test="${user.role == 1}">
                                     <p>${user.lastName} ${user.firstName}</p>
                                     <div class="dropdown">
                                        <a class="dropbtn">Мой кабинет</a>
                                        <div class="dropdown-content">
                                            <a href="<c:url value='/admin/menu'/>">Администрирование</a>
                                            <a href="<c:url value='/logout'/>">Выйти</a>
                                        </div>
                                     </div>
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/login'/>">Войти</a>
                                    <a href="<c:url value='/signup'/>">Регистрация</a>
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <div class="shopping-cart col-sm-5">
                            <a href="<c:url value='/cart' />">Корзина</a>
                            <p>Товаров (<c:out value="${cart.getSize()}" default="0"/>)</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header><!-- End of header section -->
    <nav>
        <div class="container">
            <ul class="menu clearfix">
                <c:forEach var="category" items="${categories}">
                    <li class="menu_item"><a href="<c:url value='/category/show?id=${category.id}&name=${category.name}' />"><c:out value="${category.name}" /></a></li>
                </c:forEach>
            </ul>
        </div>
    </nav>