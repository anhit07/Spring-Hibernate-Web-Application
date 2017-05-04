<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">

	$(document).ready(onLoad);

	function onLoad(){
		$("#password").keyup(checkPasswordMatch);
		$("#confirmpass").keyup(checkPasswordMatch);
		$("#details").submit(canSubmit);
	}

	function canSubmit(){
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();
		if(password != confirmpass){
			alert("<fmt:message key='UnmatchedPasswords.user.password'/>");
			return false;
		}
		return true;
	}

	function checkPasswordMatch(){
		var password = $("#password").val();
		var confirmpass = $("#confirmpass").val();

		if(password.length > 0 & confirmpass.length > 0){
			if(password == confirmpass){
				$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>");
			} else {
				$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>");
			}
		}
	}
</script>