<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
    <main class="invoice_page">
            <div class="container">
                <section>
                    <div class="page_heading">
                        <h3>Заказ #<c:out value="${orderDetails.orderId}" /></h3>
                    </div>
                    ${message}
                    <div class="invoice">
                        <p>Имя: <c:out value="${orderDetails.user.firstName}" /></p>
                        <p>Фамилия: <c:out value="${orderDetails.user.lastName}" /></p>
                        <p>Аддрес: <c:out value="${orderDetails.user.address1}" /> </p>
                        <p>Аддрес: <c:out value="${orderDetails.user.address2}, ${orderDetails.user.zip}, ${orderDetails.user.city}, ${orderDetails.user.country}" /></p>
                        <p>Телефон: <c:out value="${orderDetails.user.phone}" /></p>
                        <table>
                            <tr>
                                <th>Наименование</th>
                                <th>Цена</th>
                                <th>Количество</th>
                            </tr>
                            <c:forEach var="orderItem" items="${orderDetails.orderItems}">
                                <tr>
                                    <td><c:out value="${orderItem.product.name}"/></td>
                                    <td><c:out value="${orderItem.product.price}"/></td>
                                    <td><c:out value="${orderItem.quantity}"/></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td>Итого</td>
                                <td><c:out value="${orderDetails.getTotal()}" /></td>
                            </tr>
                        </table>
                    </div>
                    <div class="cart_btns">
                         <form action="<c:url value='/admin/order?id=${orderDetails.orderId}' />" method="post">
                              <input type="hidden" name="action" value="updateStatus">
                              <label for="orderStatus">Изменить статус заказа</label>
                                 <select name="orderStatus">
                                    <option value="0">Новый заказ</option>
                                    <option value="1">В обработке</option>
                                    <option value="2">Выполнен</option>
                                 </select>
                              <input type="submit" value="Применить">
                         </form>
                         <form action="<c:url value='/admin/order?id=${orderDetails.orderId}' />" method="post">
                            <input type="hidden" name="action" value="deleteOrder">
                            <input type="submit" value="Удалить заказ">
                         </form>
                    </div>
                </section>
            </div>
        </main>
<c:import url="includes/footer.jsp" />