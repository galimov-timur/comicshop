<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
    <main class="catalog_page">
       <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Создание и удаление категорий</h3>
                </div>
                ${message}
                <div class="catalog_form">
                    <form action="<c:url value='/admin/category' />" method="post">
                        <input type="hidden" name="action" value="add">
                        <label for="name">Создать новую категорию</label>
                        <input type="text" name="name" required>
                        <input type="submit" placeholder="Название новой категории" value="Создать">
                    </form>
                    <form action="<c:url value='/admin/category' />" method="post">
                        <input type="hidden" name="action" value="remove">
                        <label for="id">Выберите категорию для удаления</label>
                        <select name="id">
                            <option>Категории</option>
                            <c:forEach var="category" items="${categories}">
                            <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Удалить">
                    </form>
                </div>
            </section>
       </div>
    </main>
<c:import url="includes/footer.jsp" />