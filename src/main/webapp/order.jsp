<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<c:import url="includes/header.jsp"/>
    <main class="orders_page">
        <div class="container">
            <section>
                <div class="page_heading">
                    <h3><fmt:message key="orders.my"/></h3>
                </div>
                ${message}
                <div class="invoice">
                    <table>
                       <tr>
                          <th><fmt:message key="order.number"/></th>
                          <th><fmt:message key="order.status"/></th>
                          <th><fmt:message key="date"/></th>
                          <th><fmt:message key="total"/></th>
                       </tr>
                          <c:forEach var="orderDetails" items="${orders}">
                          <tr>
                            <td><c:out value="${orderDetails.orderId}"/></td>
                            <td><c:out value="${orderDetails.displayStatus()}"/></td>
                            <td><c:out value="${orderDetails.getDate()}"/></td>
                            <td><c:out value="${orderDetails.getTotal()}"/></td>
                            <c:if test="${user.role == 1}">
                                <td><a href="<c:url value='/admin/order?id=${orderDetails.orderId}&action=edit'/>"><fmt:message key="edit"/></a></td>
                            </c:if>
                          </tr>
                          </c:forEach>
                    </table>
                </div>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp"/>