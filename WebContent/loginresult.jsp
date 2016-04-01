<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.insart.model.User"%>
<% User user = (User) request.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<c:set var="auth" value="${user.isAuntheticated()}" />
<title>
<c:if test="${auth eq true}">
Welcome.
</title>
</c:if>
<c:if test="${auth eq false}">
Wrong credentials.
</title>
</c:if>
</head>
<body>
<c:if test="${auth eq true}">
You are logged in now.
</c:if>
<c:if test="${auth eq false}">
Wrong password or login. Please check your credentials.
</c:if>
 <br>
 <a href="../index.html">Return</a>
</body>
</html>