<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<h1>Send Message</h1>
<sf:form id="details" method="post" commandName="message">

	<!-- Define the Flow Execution Key - where the current submit on the flow -->
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
	<input type="hidden" name="_eventId" value="send"/>

	<table>
		<tr>
			<td>Your Name:</td>
			<td><sf:errors path="name" cssStyle="color:red"></sf:errors><br />
				<sf:input path="name" type="text" value="${fromName}" /></td>
		</tr>
		<tr>
			<td>Your Email:</td>
			<td><sf:errors path="email" cssStyle="color:red"></sf:errors><br />
				<sf:input path="email" type="text" value="${fromEmail}" /></td>
		</tr>
		<tr>
			<td>Subject:</td>
			<td><sf:errors path="subject" cssStyle="color:red"></sf:errors><br />
				<sf:input path="subject" type="text" /></td>
		</tr>
		<tr>
			<td>Your message:</td>
			<td><sf:errors path="content" cssStyle="color:red"></sf:errors><br />
				<sf:textarea path="content" type="text" /></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Send message" type="submit" /></td>
		</tr>
	</table>
</sf:form>
