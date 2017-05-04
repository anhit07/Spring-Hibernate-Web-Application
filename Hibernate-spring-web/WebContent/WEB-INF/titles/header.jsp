<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!-- For CSRF token Start -->
<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<!-- For CSRF token End -->

<a class="title" href="<c:url value='/'/>">Offers</a>

<sec:authorize access="!isAuthenticated()">
<a class="login" href="<c:url value='/login'/>">Log in</a>
</sec:authorize>
<%-- <sec:authorize access="isAuthenticated()">
<p><a href="<c:url value="/logout" />">Logout</a></p>
</sec:authorize> --%>

<sec:authorize access="isAuthenticated()">
<a class="login" href="<c:url value='/logout'/>">Log out</a>
</sec:authorize>
<%-- 	<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" class="form-control" /> --%>	