<%@ page import="java.util.List" %>
<%@ page import="com.entities.Employee" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Employee</title>
    <jsp:include page="/layout/css.jsp"/>
</head>
<body>
<jsp:include page="/layout/header.jsp"/>
<div class="container">
    <h1>Edit Employee</h1>
    <!--  Table students -->
    <% Employee employee = (Employee) request.getAttribute("editEmployee"); %>
    <% if (employee !=null) { %>
    <div class="row">
        <div class="col-6">
            <form method="post" action="create-employee">
                <input type="hidden" name="id" value="<%=employee.getId() %>">
                <div class="mb-3">
                    <label for="name" class="form-label">Full Name</label>
                    <input type="text" id="name" name="name" required  class="form-control" value="<%=employee.getName() %>" aria-describedby="nameHelp"/>
                </div>
                <div class="mb-3">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" id="email" name="email" required class="form-control" value="<%=employee.getEmail() %>" aria-describedby="emailHelp">
                    <div id="emailHelp" class="form-text">We'll never share your email with anyone else.</div>
                </div>
                <div class="mb-3">
                    <label for="birthday" class="form-label">BirthDay</label>
                    <input type="date" id="birthday" name="birthday" required class="form-control" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(employee.getBirthday()) %>" aria-describedby="addressHelp"/>
                </div>
                <div class="mb-3">
                    <label for="phone" class="form-label">Phone</label>
                    <input type="text" id="phone"  name="phone" required class="form-control" value="<%=employee.getPhone() %>" aria-describedby="phoneHelp"/>
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    <% }else { %>
    <h1>Not Found</h1>
    <% } %>

</div>
</body>
<jsp:include page="/layout/js.jsp"/>

<script type="text/javascript">
    $( '#multiple-select-field' ).select2( {
        theme: "bootstrap-5",
        width: $( this ).data( 'width' ) ? $( this ).data( 'width' ) : $( this ).hasClass( 'w-100' ) ? '100%' : 'style',
        placeholder: $( this ).data( 'placeholder' ),
        closeOnSelect: false,
    } );
</script>
</html>