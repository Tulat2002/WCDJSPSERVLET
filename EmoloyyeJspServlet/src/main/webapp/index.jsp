<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Exam WCD</title>
    <jsp:include page="/layout/css.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/layout/header.jsp"/>
</header>

<div class="container">
    <button
            class="middle none rounded-lg bg-gray-900 py-3 px-6 text-center align-middle font-sans text-xs font-bold uppercase text-white shadow-md shadow-gray-900/10 transition-all hover:shadow-lg hover:shadow-gray-900/20 focus:opacity-[0.85] focus:shadow-none active:opacity-[0.85] active:shadow-none disabled:pointer-events-none disabled:opacity-50 disabled:shadow-none"
            data-ripple-light="true"
    >
        Button
    </button>
</div>

<jsp:include page="/layout/footer.jsp"/>
<jsp:include page="/layout/js.jsp"/>
</body>
</html>