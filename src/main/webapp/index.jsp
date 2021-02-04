<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="home_page">
        <div class="container">
            <section>
                <div class="row">
                    <div class="heading">
                        <h3>${name}</h3>
                    </div>
                    <c:forEach var="product" items="${products}">
                        <div class="col-sm-3">
                            <div class="card card--large">
                                <div class="card-image">
                                    <img src="<c:out value='${product.imageUrl}'/>" alt="">
                                </div>
                                <div class="card-title">
                                    <h4><a href="<c:url value='/product/show?id=${product.id}'/>"><c:out value="${product.name}"/></a></h4>
                                </div>
                                <p class="card-price"><c:out value="${product.getPriceInCurrency()}"/></p>
                                <a href="<c:url value='/cart?action=add&id=${product.id}'/>" class="form_btn"><fmt:message key="cart.add"/></a>
                            </div>
                        </div>
                    </c:forEach>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />