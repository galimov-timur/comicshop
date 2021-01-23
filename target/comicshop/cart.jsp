<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
<main class="cart_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Корзина</h3>
                </div>
                <div class="cart_items">
                    <c:if test="${cart != null && cart.getSize() != 0}">
                    <table>
                        <tr>
                            <th>Количество</th>
                            <th>Наименование</th>
                            <th>Цена</th>
                            <th>Итого</th>
                            <th></th>
                        </tr>
                        <c:forEach var="item" items="${cart.cartProducts}">
                            <tr>
                                <td><c:out value="${item.quantity}"/></td>
                                <td><c:out value="${item.product.name}"/></td>
                                <td><c:out value="${item.product.price}"/></td>
                                <td><c:out value="${item.getTotal()}"/></td>
                                <td>
                                    <form action="<c:url value='/cart' />" method="post">
                                        <input type="hidden" name="action" value="remove">
                                        <input type="hidden" name="id" value="<c:out value='${item.product.id}'/>">
                                        <input type="submit" value="Удалить">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    </c:if>
                    <c:if test="${cart == null || cart.getSize() == 0}">
                        <p class="text-center">Корзина пуста</p>
                    </c:if>
                    <div class="cart_btns">
                        <form action="<c:url value='/' />" method="post">
                            <input type="hidden" name="action" value="shop">
                            <input type="submit" value="Продолжить">
                        </form>
                        <c:if test="${cart != null && cart.getSize() != 0}">
                        <form action="<c:url value='/checkout' />" method="post">
                             <input type="hidden" name="action" value="checkout">
                             <input type="submit" value="Оформить заказ">
                        </form>
                        </c:if>
                    </div>
                </div>
            </section>
        </div>
</main>
<c:import url="includes/footer.jsp" />