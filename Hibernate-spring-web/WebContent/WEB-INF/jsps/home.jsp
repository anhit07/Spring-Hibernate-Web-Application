<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
	
	<table>
		<tr>
			<td>Name</td>
			<td>Email</td>
			<td>Offer</td>
  		</tr>
		<c:forEach var="offer" items="${offers}">
		<tr>
			<td><c:out value="${offer.user.username}"></c:out></td>
			<td><a href="<c:url value='/message?uid=${offer.username}'/>">Contact</a></td>
			<td><c:out value="${offer.text}"></c:out></td>
		</tr>
		</c:forEach>
	</table>
	

<script type="text/javascript">
	function updateMessageLink(data){
		$("#numberMessages").text(data.number);
	}
	function onLoad(){
		updatePage();
		//Set timer
		window.setInterval(updatePage, 5000);
		
	}
	function updatePage(){
		$.getJSON("<c:url value="/getmessages"/>", updateMessageLink);
	}
	$(document).ready(onLoad);
</script>