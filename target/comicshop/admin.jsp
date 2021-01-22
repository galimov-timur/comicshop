<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
<main class="edit_products_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Панель Администратора</h3>
                </div>
                <div class="edit_products">
                    <a href="<c:url value='/admin/add_product'/>">Добавление товара</a>
                    <a href="<c:url value='/admin/delete_product'/>">Удаление товара</a>
                    <a href="<c:url value='/admin/order'/>">Управление заказами</a>
                </div>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />