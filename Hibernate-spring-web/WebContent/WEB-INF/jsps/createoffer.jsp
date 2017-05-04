<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel='stylesheet' id='tm_clicktotweet-css'
	href='${pageContext.request.contextPath}/static/css/main.css'
	type='text/css' />
<script type="text/javascript">
	
	function onDeleteClick(event){
		var doDelete = confirm("Are you sure to delete this offer?"); 
		if(doDelete == false){
			event.preventDefault();
		}
	}
	function onReady(){
		$("#delete").click(onDeleteClick);
	}
	$(document).ready(onReady);
</script>
	Create Offer
	<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
	<sf:input name="id" path="id" type="hidden" />
		<table>
			<tr>
				<td>Name:</td>
				<td>
					<sf:errors path="username" cssStyle="color:red"></sf:errors><br/>
					<sf:input name="username" path="username" type="text"/></td>
			</tr>
			<tr>
				<td>Text:</td>
				<td>
					<sf:errors path="text" cssStyle="color:red"></sf:errors><br/>
					<sf:textarea rows="10" cols="10" name="text" path="text"></sf:textarea>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Create Offer" type="submit"/></td>
			</tr>
			<c:if test="${offer.id != 0}">
			<tr>
				<td>&nbsp;</td>
				<td><input value="Delete Offer" name="delete" type="submit" id="delete"/></td>
			</tr>
			</c:if>
		</table>
	</sf:form>
