<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
    <main class="catalog_page">
       <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Удаление товара</h3>
                </div>
                ${message}
                <div class="catalog_form">
                    <form action="<c:url value='/admin/product/delete' />" method="post">
                        <input type="hidden" name="action" value="show">
                        <label for="categoryId">Выберите категорию товара</label>
                        <select name="categoryId">
                            <c:forEach var="category" items="${categories}">
                            <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Применить">
                    </form>
                    <c:if test="${products != null}">
                        <table>
                        <tr>
                            <th>ID</th>
                            <th>Название</th>
                            <th>Цена</th>
                            <th>Количество</th>
                            <th></th>
                        </tr>
                        <c:forEach var="item" items="${products}">
                        <tr>
                            <td><c:out value="${item.id}"/></td>
                            <td><c:out value="${item.name}"/></td>
                            <td><c:out value="${item.price}"/></td>
                            <td><c:out value="${item.quantity}"/></td>
                            <td><a href="<c:url value='/admin/product/delete?id=${item.id}&action=remove' />">Удалить</a></td>
                        </tr>
                        </c:forEach>
                        </table>
                    </c:if>
                </div>
            </section>
       </div>
    </main>
<c:import url="includes/footer.jsp" />