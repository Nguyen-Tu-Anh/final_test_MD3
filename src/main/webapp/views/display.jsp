<%--
  Created by IntelliJ IDEA.
  User: thaojuice
  Date: 31/12/2021
  Time: 10:25
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product List</title>
</head>
<body>
<html lang="en">
<head>
    <title>Bootstrap Example</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<div class="container">
    <h2>Student List</h2>

    <form class="example" action="/products?action=search" method="post">
        <input type="text" placeholder="Search.." name="search">
        <button type="submit"><i class="fa fa-search"></i></button>
    </form>

    <a href="/products?action=create">
        <button type="button" class="btn btn-success">Add Product</button>
    </a>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>#</th>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Color</th>
            <th>Description</th>
            <th>Category</th>
            <th>Action</th>

        </tr>
        </thead>
        <c:forEach items='${requestScope["productList"]}' var="product" varStatus="loop">
            <tr>
                <td>${loop.count}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.quantity}</td>
                <td>${product.color}</td>
                <td>${product.description}</td>
                <td>${product.categoryName}</td>

                <td><a href="/products?action=edit&&id=${product.getId()}">
                    <button type="button" class="btn btn-warning">Edit</button>
                </a>
                    <a href="/products?action=delete&&id=${product.getId()}">
                        <button type="button" class="btn btn-danger">Delete</button>
                    </a>
                </td>
            </tr>
        </c:forEach>

    </table>
</div>
</body>
</html>
