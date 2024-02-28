<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Layout Page</title>
    <jsp:include page="/layout/css.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/layout/header.jsp"/>
</header>

<div class="container">
    <h1>Layout Page</h1>
</div>
<jsp:include page="/layout/footer.jsp"/>

<jsp:include page="/layout/js.jsp"/>
</body>
</html>