<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="labels"/>
    <footer>
        <div class="text-center">
            <p>Powered by<a href="https://tomcat.apache.org/tomcat-8.0-doc/jndi-datasource-examples-howto.html">Tomcat</a>, &copy;2021 <fmt:message key="footer.text"/></p>
        </div>
    </footer>
</body>
</html>