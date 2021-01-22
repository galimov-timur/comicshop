<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="includes/header.jsp" />
    <main class="orders_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3>Мои заказы</h3>
                </div>
                ${message}
                <div class="invoice">
                    <table>
                       <tr>
                          <th>Номер заказа</th>
                          <th>Статус</th>
                          <th>Дата</th>
                          <th>Общая стоимость</th>
                       </tr>
                          <c:forEach var="orderDetails" items="${orders}">
                             <tr>
                                <td><c:out value="${orderDetails.orderId}"/></td>
                                <td><c:out value="${orderDetails.displayStatus()}"/></td>
                                <td><c:out value="${orderDetails.getDate()}"/></td>
                                <td><c:out value="${orderDetails.totalAmount}"/></td>
                                <c:if test="${user.role == 1}">
                                   <td><a href="<c:url value='/admin/order?id=${orderDetails.orderId}&action=edit' />">Изменить</a></td>
                                </c:if>
                             </tr>
                          </c:forEach>
                    </table>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />