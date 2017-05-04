<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<%-- <p>
	<a href="${pageContext.request.contextPath}/offers">Show offers</a>
</p> --%>
<p>
	<a href="${pageContext.request.contextPath}/createoffer">Add offers</a>
</p>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value="/admin" />">Admin</a>
	</p>
</sec:authorize>

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit your
			offer</a>
	</c:when>
	<c:otherwise>
		<p>
			<a href="${pageContext.request.contextPath}/createoffer">Add your
				offer</a>
		</p>
	</c:otherwise>
</c:choose>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p>
		<a href="<c:url value="/admin" />">Admin</a>
	</p>
</sec:authorize>
&nbsp;
<sec:authorize access="isAuthenticated()">
	<p>
		<a href="<c:url value="/messages" />">Messages (<span
			id="numberMessages">0</span>)
		</a>
	</p>
</sec:authorize>


