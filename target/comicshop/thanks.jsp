<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
<main class="thanks_page">
        <div class="container">
            <section>
                <h3>Спасибо за покупку, номер вашего заказа <c:out value="${orderId}" /></h3>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />