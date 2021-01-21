<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="includes/header.jsp" />
    <main class="registration_page">
        <div class="container">
            <div class="page_heading">
                <h2>Регистрация</h3>
            </div>
            ${message}
            <section class="registration-form">
                <form action="/comicshop/signup" method="post">
                    <input type="hidden" name="action" value="registerUser">

                    <label for="firstName"><b>Ваше имя</b></label>
                    <input type="text" placeholder="Указывайте реальное имя" name="firstName" required>

                    <label for="lastName"><b>Ваша фамилия</b></label>
                    <input type="text" placeholder="Фамилия" name="lastName" required>

                    <label for="email"><b>Email адрес</b></label>
                    <input type="text" placeholder="Пример: nurlan@mail.ru" name="email" required>

                    <label for="phone"><b>Мобильный телефон</b></label>
                    <input type="text" placeholder="Пример: +7 (707) 123-45-46" name="phone" required>

                    <label for="password"><b>Придумайте пароль</b></label>
                    <input type="password" placeholder="От 4 до 32 знаков" name="password" required>

                    <label for="passwordRepeat"><b>Повторите пароль</b></label>
                    <input type="password" name="passwordRepeat" required>
                    
                    <button type="submit" class="form_btn">Зарегистрироваться</button>

                    <div class="signin">
                        <p>Уже зарегистрированы на COMICSHOP? <a href="login.html">Вход</a></p>
                    </div>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />