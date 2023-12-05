<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%@page import="java.text.*"%>
<%@page import ="java.io.FileOutputStream" %>    
<%@page import=" java.io.ObjectOutputStream" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
          crossorigin="anonymous">
    <title>Document</title>
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <!-- Navbar content -->
</nav><br>
<div class="container-fluid">
    <a style="margin: 20px 0" class="btn btn-primary" href="/user/products">Add Product</a><br>
    <table class="table">
        <thead>
        <tr>
            <th scope="col">id</th>
            <th scope="col">Product Name</th>
            <th scope="col">Price</th>
            <th scope="col">Description</th>
            <th scope="col">Delete</th>
        </tr>
        </thead>
        <tbody>
        <%
            try {
                Cookie[] cookies = request.getCookies();
                int customerId = 0;
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("cutomerid")) {
                        customerId = Integer.parseInt(cookie.getValue());
                        break;
                    }
                }
                String url = "jdbc:mysql://localhost:3306/ecommjava";
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(url, "root", "Arfasara1624928");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT product_id, name, price, description FROM product WHERE product_id IN (SELECT product_id FROM cart_product WHERE cart_id IN (SELECT cart_id FROM cart WHERE customer_id = " + customerId + "))");
                while (rs.next()) {
        %>
        <tr>
            <td><%= rs.getInt(1) %></td>
            <td><%= rs.getString(2) %></td>
            <td><%= rs.getString(3) %></td>
            <td><%= rs.getString(4) %></td>
            <td>
                <form action="/cart/delete" method="post">
                    <input type="hidden" name="id" value="<%= rs.getInt(1) %>">
                    <button type="submit" class="btn btn-danger">Delete</button>
                </form>
            </td>
        </tr>
        <%
                }
            } catch (Exception ex) {
                out.println("Exception Occurred:: " + ex.getMessage());
            }
        %>
        </tbody>
    </table>
</div>
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>
