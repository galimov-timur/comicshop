<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<c:import url="includes/header.jsp" />
    <main class="registration_page">
        <div class="container">
            <div class="page_heading">
                <h3><fmt:message key="address.delivery"/></h3>
            </div>
            ${message}
            <section class="registration-form">
                <form action="/comicshop/checkout" method="post">
                    <input type="hidden" name="action" value="update">
                    <label for="address1"><b><fmt:message key="address1"/></b></label>
                    <input type="text" name="address1" value="${user.address1}" required>
                    <label for="address2"><b><fmt:message key="address2"/></b></label>
                    <input type="text" name="address2" value="${user.address2}" >
                    <label for="city"><b><fmt:message key="city"/></b></label>
                    <input type="text" name="city" value="${user.city}" required>
                    <label for="zip"><b><fmt:message key="zip"/></b></label>
                    <input type="text" name="zip" value="${user.zip}"required>
                    <label for="country"><b><fmt:message key="country"/></b></label>
                    <input type="text" name="country" value="${user.country}" required>
                    <button type="submit" class="form_btn"><fmt:message key="address.add"/></button>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />