<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="includes/header.jsp" />
<main class="product_page">
        <div class="container">
            <section class="product">
                <div class="row">
                    <div class="col-sm-4">
                        <div class="product_image">
                            <img src="<c:out value='${product.imageUrl}'/>" alt="" />
                        </div>
                    </div>
                    <div class="col-sm-5">
                        <div class="product_info">
                            <h3 class="product_name"><c:out value="${product.name}"/></h3>
                            <div class="product_description">
                                <c:out value="${product.description}"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-3">
                        <div class="product_order">
                            <form action="#" method="post">
                                <span class="product_price"><c:out value="${product.price}"/></span>
                                <span class="product_availability"><c:if test="${product.quantity > 0}">Есть в наличии</c:if></span>
                                <a href="<c:url value='/cart?action=add&id=${product.id}'/>" class="form_btn">В корзину</a>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />