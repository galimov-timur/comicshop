<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<!DOCTYPE>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style/main.css'/>">
    <link rel="icon" type="image/x-icon" href="<c:url value='/static/images/favicon.ico'/>"/>
    <title>COMICSHOP</title>
</head>
<body>
    <header>
        <div class="container">
            <div class="row">
                <a class="lang first" href="<c:url value='/locale?lang=en'/>">En</a>
                <a class="lang" href="<c:url value='/locale?lang=ru'/>">Ru</a>
            </div>
            <div class="row">
                <div class="col-sm-6 padding-20">
                    <h1 class="logo"><a href="<c:url value='/'/>">COMIC<span>SHOP</span></a></h1>
                </div>
                <div class="col-sm-6">
                    <div class="row">
                        <div class="login col-sm-5"><c:choose><c:when test="${user.role == 0}">
                            <p>${user.lastName} ${user.firstName}</p>
                            <div class="dropdown">
                                <a class="dropbtn">
                                    <fmt:message key="account.my"/>
                                </a>
                                <div class="dropdown-content">
                                    <a href="<c:url value='/order/show'/>">
                                        <fmt:message key="orders.my"/>
                                    </a>
                                    <a href="<c:url value='/logout'/>">
                                        <fmt:message key="logout"/>
                                    </a>
                                </div>
                            </div></c:when><c:when test="${user.role == 1}">
                            <p>${user.lastName} ${user.firstName}</p>
                            <div class="dropdown">
                                <a class="dropbtn">
                                    <fmt:message key="account.my"/>
                                </a>
                                <div class="dropdown-content">
                                    <a href="<c:url value='/admin/menu'/>">
                                        <fmt:message key="admin"/>
                                    </a>
                                    <a href="<c:url value='/logout'/>">
                                        <fmt:message key="logout"/>
                                    </a>
                                </div>
                            </div></c:when><c:otherwise>
                            <a href="<c:url value='/login'/>">
                                <fmt:message key="login.action"/>
                            </a>
                            <a href="<c:url value='/signup'/>">
                                <fmt:message key="signup"/>
                            </a></c:otherwise></c:choose>
                        </div>
                        <div class="shopping-cart col-sm-5">
                            <a href="<c:url value='/cart'/>">
                                <fmt:message key="cart"/>
                            </a>
                            <p>
                               <fmt:message key="products.of"/>(<c:out value="${cart.getSize()}" default="0"/>)
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
    <!-- End of header section -->
    <nav>
        <div class="container">
            <ul class="menu clearfix"><c:forEach var="category" items="${categories}">
                <li class="menu_item">
                    <a href="<c:url value='/category/show?id=${category.id}&name=${category.name}'/>">
                        <c:out value="${category.name}"/>
                    </a>
                </li></c:forEach>
            </ul>
        </div>
    </nav>