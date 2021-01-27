<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
<main class="add_products_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Добавить товар</h3>
                </div>
                ${message}
                <div class="add_product_form">
                    <form action="/comicshop/admin/product/add" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="add">
                        <label for="productCategory">Категория товара</label>
                        <select name="productCategory">
                            <option>Выберите категорию</option>
                            <c:forEach var="category" items="${categories}">
                                <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <label for="productName">Название товара</label>
                        <input type="text" name="productName" required>
                        <label for="productDescription">Описание товара</label>
                        <textarea name="productDescription" rows="10" cols="40"></textarea>
                        <label for="productQuantity">Количество товара на складе</label>
                        <input type="text" name="productQuantity" required>
                        <label for="productPrice">Цена</label>
                        <input type="text" name="productPrice" required>
                        <label for="productImage">Загрузите изображение товара</label>
                        <input type="file" name="productImage">
                        <button type="submit" class="form_btn">Добавить товар</button>
                    </form>
                </div>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />