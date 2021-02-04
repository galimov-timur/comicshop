<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<c:import url="includes/header.jsp"/>
    <main class="invoice_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="order.details"/> #<c:out value="${orderDetails.orderId}"/></h3>
                </div>
                ${message}
                <div class="invoice">
                    <p><fmt:message key="firstname"/>: <c:out value="${orderDetails.user.firstName}"/></p>
                    <p><fmt:message key="lastname"/>: <c:out value="${orderDetails.user.lastName}"/></p>
                    <p><fmt:message key="address1"/>: <c:out value="${orderDetails.user.address1}"/></p>
                    <p><fmt:message key="address2"/>: <c:out value="${orderDetails.user.address2}, ${orderDetails.user.zip}, ${orderDetails.user.city}, ${orderDetails.user.country}"/></p>
                    <p><fmt:message key="phone"/>: <c:out value="${orderDetails.user.phone}"/></p>
                    <table>
                        <tr>
                            <th><fmt:message key="product.name"/></th>
                            <th><fmt:message key="product.price"/></th>
                            <th><fmt:message key="quantity"/></th>
                        </tr>
                        <c:forEach var="orderItem" items="${orderDetails.orderItems}">
                        <tr>
                            <td><c:out value="${orderItem.product.name}"/></td>
                            <td><c:out value="${orderItem.product.getPriceInCurrency()}"/></td>
                            <td><c:out value="${orderItem.quantity}"/></td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td><fmt:message key="total"/></td>
                            <td><c:out value="${orderDetails.getTotal()}"/></td>
                            <td></td>
                        </tr>
                    </table>
                </div>
                <div class="cart_btns">
                    <form action="<c:url value='/admin/order?id=${orderDetails.orderId}'/>" method="post">
                        <input type="hidden" name="action" value="update">
                        <label for="orderStatus"><fmt:message key="order.status.change"/></label>
                        <select name="orderStatus">
                            <option value="0"><fmt:message key="order.new"/></option>
                            <option value="1"><fmt:message key="order.processing"/></option>
                            <option value="2"><fmt:message key="order.delivered"/></option>
                        </select>
                        <input type="submit" value="<fmt:message key='submit'/>">
                    </form>
                    <form action="<c:url value='/admin/order?id=${orderDetails.orderId}' />" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="submit" value="<fmt:message key='order.remove'/>">
                    </form>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>