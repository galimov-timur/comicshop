<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="includes/header.jsp" />
    <main class="login_page">
        <div class="container">
            <div class="page_heading">
                <h2>Вход</h3>
            </div>
            ${message}
            <section class="login-form">
                <form action="/comicshop/login" method="post">
                    <input type="hidden" name="action" value="signIn">

                    <label for="email"><b>Ваш email адрес</b></label>
                    <input type="text" placeholder="Пример: nurlan@mail.ru" name="email" required>

                    <label for="password"><b>Пароль</b></label>
                    <input type="password" placeholder="Ваш пароль" name="password" required>
                    
                    <button type="submit" class="form_btn">Войти</button>

                    <div class="signin">
                        <p>Не зарегистрированы на COMICSHOP? <a href="<c:url value='/signup' />">Регистрация</a></p>
                    </div>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />