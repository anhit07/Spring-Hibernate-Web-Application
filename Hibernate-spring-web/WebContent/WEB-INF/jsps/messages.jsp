<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div id="messages"></div>
<style>
.replyform {
	display: none;
}
</style>

<script type="text/javascript">
	var timer;

	//For CSRD token Start
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr, options) {
		xhr.setRequestHeader(header, token);
	});
	//For CSRD token End

	function showReply(i) {
		stopTimer();
		$("#form" + i).toggle();
	}

	function success(data) {
		$("#form" + data.target).toggle();
		$("#alert" + data.target).text("Message sent.");
		starTimer();
	}

	function error(data) {
		alert("error");
		console.log(data);
	}

	function sendMessage(i, name, email) {

		var text = $("#textbox" + i).val();
		$.ajax({
			"type" : 'POST',
			"url" : '<c:url value="/sendMessage" />',
			"data" : JSON.stringify({
				"target" : i,
				"text" : text,
				"name" : name,
				"email" : email
			}),
			"success" : success,
			"error" : error,
			"contentType" : "application/json",
			"dataType" : "json"
		});
	}
	function showMessage(data) {

		$("div#messages").html("");
		for (var i = 0; i < data.messages.length; i++) {
			var messages = data.messages[i];
			var messagesDiv = document.createElement("div");

			messagesDiv.setAttribute("class", "message");

			var subjectSpan = document.createElement("span");
			subjectSpan.setAttribute("class", "subject");
			subjectSpan.appendChild(document.createTextNode(messages.subject));

			var contentSpan = document.createElement("span");
			contentSpan.setAttribute("class", "messagebody");
			contentSpan.appendChild(document.createTextNode(messages.content));

			var nameSpan = document.createElement("span");
			nameSpan.setAttribute("class", "name");
			nameSpan.appendChild(document.createTextNode(messages.name + "("));

			var link = document.createElement("a");
			link.setAttribute("class", "replylink");
			link.setAttribute("href", "#");
			link.setAttribute("onclick", "showReply(" + i + ")");
			link.appendChild(document.createTextNode(messages.email));
			nameSpan.appendChild(link);
			nameSpan.appendChild(document.createTextNode(")"));

			var alertSpan = document.createElement("span");
			alertSpan.setAttribute("class", "alert");
			alertSpan.setAttribute("id", "alert" + i);

			var replyForm = document.createElement("form");
			replyForm.setAttribute("class", "replyform");
			replyForm.setAttribute("id", "form" + i);

			var textArea = document.createElement("textarea");
			textArea.setAttribute("type", "replyarea");
			textArea.setAttribute("id", "textbox" + i);

			var replyButton = document.createElement("input");
			replyButton.setAttribute("type", "button");
			replyButton.setAttribute("value", "Reply");
			replyButton.onclick = function(j, name, email) {
				return function() {
					sendMessage(j, name, email);
				};
			}(i, messages.name, messages.email);

			replyForm.appendChild(textArea);
			replyForm.appendChild(replyButton);

			messagesDiv.appendChild(subjectSpan);
			messagesDiv.appendChild(contentSpan);
			messagesDiv.appendChild(nameSpan);
			messagesDiv.appendChild(alertSpan);
			messagesDiv.appendChild(replyForm);
			$("div#messages").append(messagesDiv);
		}
	}
	function onLoad() {
		updatePage();
		//Set timer
		starTimer();
	}

	function starTimer() {
		timer = window.setInterval(updatePage, 10000);
	}

	function stopTimer() {
		window.clearInterval(timer);
	}
	function updatePage() {
		$.getJSON("<c:url value="/getmessages"/>", showMessage);
	}
	$(document).ready(onLoad);
</script>
