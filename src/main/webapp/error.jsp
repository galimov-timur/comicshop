<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
<!DOCTYPE>
<html lang="${sessionScope.lang}">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style/main.css'/>">
    <link rel="icon" type="image/x-icon" href="<c:url value='/static/images/favicon.ico'/>"/>
    <title>Error</title>
</head>
<body>
    <main class="error_page">
        <div class="container">
            <div class="page_heading">
                <h3><fmt:message key="error"/></h3>
            </div>
            <section class="error_message text-center">
                <p class="error_code">${errorCode}</p>
                <p>${message}</p>
                <a href="<c:url value='/'/>" ><fmt:message key="page.main.back"/></a>
            </section>
        </div>
    </main>
</body>
</html>