<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="includes/header.jsp" />
    <main class="invoice_page">
            <div class="container">
                <section>
                    <div class="page_heading">
                        <h3>Заказ</h3>
                    </div>
                    <div class="invoice">
                        <p>Имя: <c:out value="${user.firstName}" /></p>
                        <p>Фамилия: <c:out value="${user.lastName}" /></p>
                        <p>Аддрес: <c:out value="${user.address1}" /> </p>
                        <p>Аддрес: <c:out value="${user.address2}, ${user.zip}, ${user.city}, ${user.country}" /></p>
                        <p>Телефон: <c:out value="${user.phone}" /></p>
                        <table>
                            <tr>
                                <th>Количество</th>
                                <th>Наименование</th>
                                <th>Цена</th>
                            </tr>
                            <c:forEach var="item" items="${cart.cartProducts}">
                                <tr>
                                    <td><c:out value="${item.quantity}"/></td>
                                    <td><c:out value="${item.product.name}"/></td>
                                    <td><c:out value="${item.product.price}"/></td>
                                </tr>
                            </c:forEach>
                            <tr>
                                <td></td>
                                <td><b>Итого</b></td>
                                <td><b><c:out value="${cart.getTotal()}"/></b></td>
                            </tr>
                        </table>
                    </div>
                    <div class="cart_btns">
                         <form action="<c:url value='/checkout' />" method="post">
                              <input type="hidden" name="action" value="saveOrder">
                              <input type="submit" value="Подтвердить заказ">
                         </form>
                    </div>
                </section>
            </div>
        </main>
<c:import url="includes/footer.jsp" />