<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" 
		+ request.getServerPort() + path + "/"; 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>注册失败界面</title>
	<base href="<%=basePath%>">
</head>

<body>
	<h3>注册失败</h3>
	<ol>
		<jsp:useBean id="errorList" scope="request" type="java.util.List"/>
		<c:forEach var="error" items="${errorList}">
			<li><c:out value="${error}"/><br></li>
		</c:forEach>
	</ol>
	<a href="user/user/signIn.jsp">返回注册界面</a>
</body>
</html>