<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<c:import url="includes/header.jsp"/>
    <main class="login_page">
        <div class="container">
            <div class="page_heading">
                <h3><fmt:message key="login"/></h3>
            </div>
            ${message}
            <section class="login-form">
                <form action="/comicshop/login" method="post">
                    <input type="hidden" name="action" value="signIn">
                    <label for="email"><b><fmt:message key="email.label"/></b></label>
                    <input type="text" placeholder="<fmt:message key='email.placeholder'/>" name="email" required>
                    <label for="password"><b><fmt:message key="password.label" /></b></label>
                    <input type="password" placeholder="<fmt:message key='password.placeholder'/>" name="password" required>
                    <button type="submit" class="form_btn">
                        <fmt:message key="login.action"/>
                    </button>
                    <div class="signin">
                        <p><fmt:message key="signup.text"/>
                            <a href="<c:url value='/signup'/>"><fmt:message key="signup"/></a>
                        </p>
                    </div>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>