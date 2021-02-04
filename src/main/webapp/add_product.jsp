<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="add_products_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="product.add"/></h3>
                </div>
                ${message}
                <div class="add_product_form">
                    <form action="/comicshop/admin/product/add" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="add">
                        <label for="productCategory"><fmt:message key="product.category"/></label>
                        <select name="productCategory">
                            <option><fmt:message key="product.category.choose"/></option>
                            <c:forEach var="category" items="${categories}">
                                <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <p class="form-message"><c:out value="${form.getErrorMessage('productCategory')}"/><p>
                        <label for="productName"><fmt:message key="product.name"/></label>
                        <input type="text" name="productName" value="${form.getProductName()}" required>
                        <p class="form-message"><c:out value="${form.getErrorMessage('productName')}"/><p>
                        <label for="productDescription"><fmt:message key="product.description"/></label>
                        <textarea name="productDescription" rows="10" cols="40"></textarea>
                        <p class="form-message"><c:out value="${form.getErrorMessage('productDescription')}"/><p>
                        <label for="productQuantity"><fmt:message key="product.quantity"/></label>
                        <input type="text" name="productQuantity" value="${form.getProductQuantity()}"required>
                        <p class="form-message"><c:out value="${form.getErrorMessage('productQuantity')}"/><p>
                        <label for="productPrice"><fmt:message key="product.price"/></label>
                        <input type="text" name="productPrice" value="${form.getProductPrice()}" required>
                        <p class="form-message"><c:out value="${form.getErrorMessage('productPrice')}"/><p>
                        <label for="productImage"><fmt:message key="product.image"/></label>
                        <input type="file" name="productImage">
                        <p class="form-message"><c:out value="${form.getErrorMessage('productImage')}"/><p>
                        <button type="submit" class="form_btn"><fmt:message key="product.add"/></button>
                    </form>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />