<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
    <main class="home_page">
        <div class="container">
            <section>
                <div class="row">
                    <div class="heading">
                        <h3>${categoryName}</h3>
                    </div>
                    <c:forEach var="product" items="${products}">
                        <div class="col-sm-3">
                            <div class="card card--large">
                                <div class="card-image">
                                    <img src="<c:out value='${product.imageUrl}'/>" alt="">
                                </div>
                                <div class="card-title">
                                    <h4><a href="<c:url value='/product/show?productId=${product.id}' />"><c:out value="${product.name}"/></a></h4>
                                </div>
                                <p class="card-price"><c:out value="${product.price}"/></p>
                                <a href="<c:url value='/cart?action=add&id=${product.id}'/>" class="form_btn">В корзину</a>
                            </div>
                        </div>
                    </c:forEach>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />