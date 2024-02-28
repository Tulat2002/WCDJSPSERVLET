<%@ page import="com.entities.Employee" %>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>Employee List</title>
    <jsp:include page="/layout/css.jsp"/>
</head>
<body>
<header>
    <jsp:include page="/layout/header.jsp"/>
</header>
<section>
    <div class="container">
        <h1>List Employee Demo  <%= request.getAttribute("search")%></h1>
        <div class="d-flex justify-content-between">
            <button class="btn btn-outline-primary" type="button">
                <a href="create-employee">Create</a>
            </button>

            <form class="d-flex" method="get" action="list-employee">
                <input class="form-control me-2" name="search" required type="text" placeholder="Search by Name or Id" aria-label="Search" value="<%= request.getAttribute("search")!= null ? request.getAttribute("search"): "" %>">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
        <%--        <!--  Table Employees -->--%>
        <%
            List<Employee> employee =  (List<Employee>) request.getAttribute("employee");
        %>
        <% if (!employee.isEmpty()) { %>
        <table class="table">
            <thead>
            <th>Id</th>
            <th>Name</th>
            <th>Email</th>
            <th>Birthday</th>
            <th>Phone</th>
            <th></th>
            </thead>
            <tbody>
            <% for (Employee s : employee) { %>
            <tr>
                <td><%= s.getId()  %></td>
                <td><%= s.getName()  %></td>
                <td><%= s.getEmail()  %></td>
                <td><%= s.getBirthday()  %></td>
                <td><%= s.getPhone()  %></td>
                <td><a href="edit-employee?id=<%= s.getId() %>">Edit</a> </td>
                <td>
                    <a class="text-danger" onclick="deleteEmployee(<%= s.getId() %>)" href="javascript:void(0);">Delete</a>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
        <% } else { %>
        <h1>Empty</h1>
        <% } %>

    </div>
</section>

<footer>
    <jsp:include page="/layout/footer.jsp"/>
</footer>
<jsp:include page="/layout/js.jsp"/>
<script type="text/javascript">
    function deleteEmployee(id) {
        if(confirm("Are you sure?")) {
            var url = `list-employee?id=` + id;
            fetch(url, {
                method: 'DELETE'
                // body: formData
            }).then(rs => {
                window.location.reload();
            }).error(err => {
                alert(err)
            })
        }
    }
</script>
</body>
</html>