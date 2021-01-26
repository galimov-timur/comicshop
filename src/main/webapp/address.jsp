<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="includes/header.jsp" />
    <main class="registration_page">
        <div class="container">
            <div class="page_heading">
                <h3>Адрес доставки</h3>
            </div>
            ${message}
            <section class="registration-form">
                <form action="/comicshop/checkout" method="post">
                    <input type="hidden" name="action" value="update">

                    <label for="address1"><b>Адрес 1</b></label>
                    <input type="text" name="address1" value="${user.address1}" required>

                    <label for="address2"><b>Адрес 2</b></label>
                    <input type="text" name="address2" value="${user.address2}" >

                    <label for="city"><b>Город</b></label>
                    <input type="text"  name="city" value="${user.city}" required>

                    <label for="zip"><b>Почтовый индекс</b></label>
                    <input type="text" name="zip" value="${user.zip}"required>

                    <label for="country"><b>Страна</b></label>
                    <input type="text" name="country" value="${user.country}" required>

                    <button type="submit" class="form_btn">Добавить адрес</button>
                </form>
            </section>
        </div>
    </main>
<c:import url="includes/footer.jsp" />