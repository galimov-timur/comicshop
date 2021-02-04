<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="catalog_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="product.remove"/></h3>
                </div>
                ${message}
                <div class="catalog_form">
                    <form action="<c:url value='/admin/product/delete'/>" method="post">
                        <input type="hidden" name="action" value="show">
                        <label for="categoryId"><fmt:message key="product.category.choose"/></label>
                        <select name="categoryId">
                            <c:forEach var="category" items="${categories}">
                            <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key='submit'/>">
                    </form>
                    <c:if test="${products != null}">
                        <table>
                        <tr>
                            <th><fmt:message key="product.id"/></th>
                            <th><fmt:message key="product.name"/></th>
                            <th><fmt:message key="product.price"/></th>
                            <th><fmt:message key="quantity"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="item" items="${products}">
                        <tr>
                            <td><c:out value="${item.id}"/></td>
                            <td><c:out value="${item.name}"/></td>
                            <td><c:out value="${item.price}"/></td>
                            <td><c:out value="${item.quantity}"/></td>
                            <td><a href="<c:url value='/admin/product/delete?id=${item.id}&action=remove'/>"><fmt:message key="remove"/></a></td>
                        </tr>
                        </c:forEach>
                        </table>
                    </c:if>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>