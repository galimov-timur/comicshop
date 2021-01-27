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
                    <input type="hidden" name="action" value="add">

                    <label for="firstName"><b>Ваше имя</b></label>
                    <input type="text" placeholder="Указывайте реальное имя" name="firstName" value="${form.getFirstName()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('firstName')}" /><p>

                    <label for="lastName"><b>Ваша фамилия</b></label>
                    <input type="text" placeholder="Фамилия" name="lastName" value="${form.getLastName()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('lastName')}" /><p>

                    <label for="email"><b>Email адрес</b></label>
                    <input type="text" placeholder="Пример: nurlan@mail.ru" name="email" value="${form.getEmail()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('email')}" /><p>

                    <label for="phone"><b>Мобильный телефон</b></label>
                    <input type="text" placeholder="Пример: +7(707)123-45-46" value="${form.getPhone()}" name="phone" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('phone')}" /><p>

                    <label for="password"><b>Придумайте пароль</b></label>
                    <input type="password" placeholder="От 4 до 32 знаков" name="password" value="${form.getPassword()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('password')}" /><p>

                    <label for="passwordRepeat"><b>Повторите пароль</b></label>
                    <input type="password" name="passwordRepeat" value="${form.getPasswordRepeat()}" required>
                    <p class="form-message"><c:out value="${form.getErrorMessage('passwordRepeat')}" /><p>

                    <button type="submit" class="form_btn">Зарегистрироваться</button>

                    <div class="signin">
                        <p>Уже зарегистрированы на COMICSHOP? <a href="<c:url value='/login' />">Вход</a></p>
                    </div>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />