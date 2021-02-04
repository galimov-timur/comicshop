<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="edit_products_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="admin.panel"/></h3>
                </div>
                <div class="row">
                    <div class="edit_products col-sm-4 text-center">
                        <h4 class="text-center"><fmt:message key="products"/></h4>
                        <a href="<c:url value='/admin/product/add'/>"><fmt:message key="product.add"/></a>
                        <a href="<c:url value='/admin/product/delete'/>"><fmt:message key="product.remove"/></a>
                    </div>
                    <div class="edit_orders col-sm-4">
                        <h4 class="text-center"><fmt:message key="orders"/></h4>
                        <a href="<c:url value='/admin/order'/>"><fmt:message key="orders.manage"/></a>
                    </div>
                    <div class="edit_categories col-sm-4">
                        <h4 class="text-center"><fmt:message key="categories"/></h4>
                        <a href="<c:url value='/admin/category'/>"><fmt:message key="categories.manage"/></a>
                    </div>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />