<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<c:import url="includes/header.jsp"/>
    <main class="thanks_page">
        <div class="container">
            <section>
                <h3><fmt:message key="thanks.page.text"/> <c:out value="${orderId}"/></h3>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>