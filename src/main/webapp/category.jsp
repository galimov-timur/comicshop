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
                    <h3><fmt:message key="category.manage.heading"/></h3>
                </div>
                ${message}
                <div class="catalog_form">
                    <form action="<c:url value='/admin/category'/>" method="post">
                        <input type="hidden" name="action" value="add">
                        <label for="name"><fmt:message key="category.create"/></label>
                        <input type="text" name="name" required>
                        <input type="submit" placeholder="<fmt:message key='category.new.name'/>" value="<fmt:message key='create'/>">
                    </form>
                    <form action="<c:url value='/admin/category'/>" method="post">
                        <input type="hidden" name="action" value="remove">
                        <label for="id"><fmt:message key="category.remove.choose"/></label>
                        <select name="id">
                            <option><fmt:message key="categories"/></option>
                            <c:forEach var="category" items="${categories}">
                            <option value="<c:out value='${category.id}'/>"><c:out value="${category.name}"/></option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="<fmt:message key='remove'/>">
                    </form>
                </div>
            </section>
       </div>
    </main>
<c:import url="includes/footer.jsp" />