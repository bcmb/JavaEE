<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.insart.model.User"%>
<% User user = (User) request.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>
Registration.
</title>
</head>
<body>
<c:set var="confirmPassword" value='${requestScope["confirmPassword"]}' />
<c:set var="regResult" value="${user.isRegistered()}" />
<c:choose>
<c:when test="${confirmPassword eq true}">
Password and confirm password do not match.
<a href="../registration.html">Try again.</a>
</c:when>
<c:otherwise>
<c:if test="${regResult eq true}">
User <c:out value="${user.getUsername()}" /> added.
<a href="../index.html">Login.</a>
</c:if>
<c:if test="${regResult eq false}">
Something went wrong.
<a href="../register.html">Try again.</a>
</c:if>
</c:otherwise>
</c:choose>
</body>
</html>
