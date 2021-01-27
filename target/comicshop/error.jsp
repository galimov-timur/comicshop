<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" type="text/css" href="<c:url value='/static/style/main.css'/>">
    <link rel="icon" type="image/x-icon" href="<c:url value='/static/images/favicon.ico'/>" />
    <title>COMICSHOP</title>
</head>
<body>
    <main class="error_page">
        <div class="container">
            <div class="page_heading">
                <h3>Произошла ошибка</h3>
            </div>
            <section class="error_message text-center">
                <p class="error_code">${errorCode}</p>
                <p>${message}</p>
                <a href="<c:url value='/' />" >Вернуться на главную страницу</a>
            </section>
        </div>
    </main>
</body>
</html>