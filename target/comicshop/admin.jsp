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
                <div class="row">
                    <div class="edit_products col-sm-4 text-center">
                        <h4 class="text-center">Товары</h4>
                        <a href="<c:url value='/admin/product/add'/>">Добавление товара</a>
                        <a href="<c:url value='/admin/product/delete'/>">Удаление товара</a>
                    </div>
                    <div class="edit_orders col-sm-4">
                        <h4 class="text-center">Заказы</h4>
                        <a href="<c:url value='/admin/order'/>">Управление заказами</a>
                    </div>
                    <div class="edit_categories col-sm-4">
                        <h4 class="text-center">Категории</h4>
                        <a href="<c:url value='/admin/category'/>">Управление категориями</a>
                    </div>
                </div>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />