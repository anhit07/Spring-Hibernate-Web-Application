<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script language="JavaScript" type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery-3.1.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style type="text/css">
table, tr, td {
   border: 1px solid black;
}
table {
    border-collapse: collapse;
}
</style>
<title><tiles:insertAttribute name="title"></tiles:insertAttribute></title>
<%-- <tiles:insertAttribute name="includes"></tiles:insertAttribute> --%>
</head>
<body>
	<div><tiles:insertAttribute name="header"></tiles:insertAttribute></div>
	<div><tiles:insertAttribute name="toolbar"></tiles:insertAttribute></div>
	<div><tiles:insertAttribute name="content"></tiles:insertAttribute></div>
	<div><tiles:insertAttribute name="footer"></tiles:insertAttribute></div>
</body>
</html>