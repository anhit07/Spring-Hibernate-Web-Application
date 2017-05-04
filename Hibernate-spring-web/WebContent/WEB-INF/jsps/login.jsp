<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="java.util.*" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function(){
		document.username.focus();
	});
</script>
	<h3>Login with Username and Password</h3>
	<c:if test="${param.error != null}">
		<p style="color:red">Login failed. Incorrect username and password</p>
	</c:if>
	<%-- <form name='f' action='${pageContext.request.contextPath}/login'
		method='POST'> --%>
	<form name='f' action='${pageContext.request.contextPath}/j_spring_security_check' method='POST'>
		<table>
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' value=''></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
		<!-- 	<tr>
				<td>Remember me:</td>
				<td><input type="checkbox" name="remember-me" checked="checked"/>
				<input type='checkbox' name='_spring_security_remember_me'
				checked="checked" /></td>
			</tr> -->
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" class="form-control" />
	</form>
	<p><a href="${pageContext.request.contextPath}/newaccount">Create new account</a></p>