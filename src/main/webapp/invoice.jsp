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
                    <h3><fmt:message key="order.details"/></h3>
                </div>
                <div class="invoice">
                    <p><fmt:message key="firstname"/>: <c:out value="${user.firstName}"/></p>
                    <p><fmt:message key="lastname"/>: <c:out value="${user.lastName}"/></p>
                    <p><fmt:message key="address1"/>: <c:out value="${user.address1}"/></p>
                    <p><fmt:message key="address2"/>: <c:out value="${user.address2}, ${user.zip}, ${user.city}, ${user.country}"/></p>
                    <p><fmt:message key="phone"/>: <c:out value="${user.phone}"/></p>
                    <table>
                        <tr>
                            <th><fmt:message key="quantity"/></th>
                            <th><fmt:message key="product.name"/></th>
                            <th><fmt:message key="product.price"/></th>
                        </tr>
                        <c:forEach var="item" items="${cart.cartProducts}">
                        <tr>
                            <td><c:out value="${item.quantity}"/></td>
                            <td><c:out value="${item.product.name}"/></td>
                            <td><c:out value="${item.product.getPriceInCurrency()}"/></td>
                        </tr>
                        </c:forEach>
                        <tr>
                            <td></td>
                            <td><b><fmt:message key="total"/></b></td>
                            <td><b><c:out value="${cart.getTotalInCurrency()}"/></b></td>
                        </tr>
                    </table>
                </div>
                <div class="cart_btns">
                    <form action="<c:url value='/checkout'/>" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="submit" value="<fmt:message key='order.confirm'/>">
                    </form>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>