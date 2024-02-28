<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create Employee</title>
    <jsp:include page="/layout/css.jsp"/>
</head>
<body>
<jsp:include page="/layout/header.jsp"/>
<div class="container">
    <h1>Create Employee</h1>
    <!--  Table students -->
    <div class="row">
        <div class="col-6">
            <form method="post" action="create-employee">
                <div class="mb-3">
                    <label for="name" class="form-label">Full Name</label>
                    <input type="text" id="name" name="name" required  class="form-control" aria-describedby="nameHelp"/>
                </div>
                <div class="mb-3">
                    <label for="birthday" class="form-label">BirthDay</label>
                    <input type="date" id="birthday"  name="birthday" required class="form-control" aria-describedby="addressHelp"/>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" id="phone"  name="phone" required class="form-control" aria-describedby="phoneHelp"/>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email" required class="form-control"  aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
</body>
<jsp:include page="/layout/js.jsp"/>

</html>