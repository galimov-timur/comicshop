<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="registration_page">
        <div class="container">
            <div class="page_heading">
                <h3><fmt:message key="signup"/></h3>
            </div>
            ${message}
            <section class="registration-form">
                <form action="/comicshop/signup" method="post">
                    <input type="hidden" name="action" value="add">
                    <label for="firstName"><b><fmt:message key="firstname"/></b></label>
                    <input type="text" placeholder="<fmt:message key='firstname.placeholder'/>" name="firstName" value="${form.getFirstName()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('firstName')}"/><p>
                    <label for="lastName"><b><fmt:message key="lastname"/></b></label>
                    <input type="text" placeholder="<fmt:message key='lastname.placeholder'/>" name="lastName" value="${form.getLastName()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('lastName')}"/><p>
                    <label for="email"><b><fmt:message key="email.label"/></b></label>
                    <input type="text" placeholder="<fmt:message key='email.placeholder'/>" name="email" value="${form.getEmail()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('email')}"/><p>
                    <label for="phone"><b><fmt:message key="phone"/></b></label>
                    <input type="text" placeholder="<fmt:message key='phone.placeholder'/>" value="${form.getPhone()}" name="phone" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('phone')}"/><p>
                    <label for="password"><b><fmt:message key="password.register"/></b></label>
                    <input type="password" placeholder="<fmt:message key='password.register.placeholder'/>" name="password" value="${form.getPassword()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('password')}"/><p>
                    <label for="passwordRepeat"><b><fmt:message key="password.repeat"/></b></label>
                    <input type="password" name="passwordRepeat" value="${form.getPasswordRepeat()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('passwordRepeat')}"/><p>
                    <button type="submit" class="form_btn"><fmt:message key="signup.action"/></button>
                    <div class="signin">
                        <p><fmt:message key="login.text"/><a href="<c:url value='/login'/>"><fmt:message key="login"/></a></p>
                    </div>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>