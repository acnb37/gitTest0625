<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1.0">
<title></title>
</head>
<body>
	<h2 align="center">${errorMsg}</h2>
	
	<div align="center">
		<button type="button" onclick="history.go(-1);">이번 페이지로 이동</button>
		<button type="button" onclick="location.href='${pageContext.servletContext.contextPath}';">메인 페이지로 이동</button>
	</div>
</body>
</html>