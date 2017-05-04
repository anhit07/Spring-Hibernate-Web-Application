<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	Create New Account
	<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
		<table>
			<tr>
				<td>Username:</td>
				<td>
					<sf:errors path="username" cssStyle="color:red"></sf:errors><br/>
					<sf:input name="username" path="username" type="text"/></td>
			</tr>
			<tr>
				<td>Email:</td>
				<td>
					<sf:errors path="email" cssStyle="color:red"></sf:errors><br/>
					<sf:input name="email" path="email" type="text" />
				</td>
			</tr>
			<tr>
				<td>Password:</td>
				<td>
					<sf:errors path="password" cssStyle="color:red"></sf:errors><br/>
					<sf:input id="password" name="password" path="password" type="password" />
				</td>
			</tr>
			<tr>
				<td>Confirm Password:</td>
				<td>
					<input id="confirmpass" name="confirmpass  type="text" />
					<div id="matchpass" style="color: red"></div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input value="Create Offer" type="submit" /></td>
			</tr>
		</table>
	</sf:form>
