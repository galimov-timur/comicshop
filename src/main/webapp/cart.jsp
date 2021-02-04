<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:import url="includes/header.jsp"/>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <main class="cart_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="cart"/></h3>
                </div>
                <div class="cart_items">
                    <c:if test="${cart != null && cart.getSize() != 0}">
                    <table>
                        <tr>
                            <th><fmt:message key="quantity"/></th>
                            <th><fmt:message key="product.name"/></th>
                            <th><fmt:message key="product.price"/></th>
                            <th><fmt:message key="total"/></th>
                            <th></th>
                        </tr>
                        <c:forEach var="item" items="${cart.cartProducts}">
                            <tr>
                                <td><c:out value="${item.quantity}"/></td>
                                <td><c:out value="${item.product.name}"/></td>
                                <td><c:out value="${item.product.getPriceInCurrency()}"/></td>
                                <td><c:out value="${item.getTotalInCurrency()}"/></td>
                                <td>
                                    <form action="<c:url value='/cart' />" method="post">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="id" value="<c:out value='${item.product.id}'/>">
                                        <input type="submit" value="<fmt:message key='remove'/>">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    </c:if>
                    <c:if test="${cart == null || cart.getSize() == 0}">
                        <p class="text-center"><fmt:message key="cart.empty"/></p>
                    </c:if>
                    <div class="cart_btns">
                        <form action="<c:url value='/' />" method="post">
                            <input type="hidden" name="action" value="shop">
                            <input type="submit" value="<fmt:message key='continue'/>">
                        </form>
                        <c:if test="${cart != null && cart.getSize() != 0}">
                        <form action="<c:url value='/checkout' />" method="post">
                             <input type="hidden" name="action" value="checkout">
                             <input type="submit" value="<fmt:message key='checkout'/>">
                        </form>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>