<%--
  Created by IntelliJ IDEA.
  User: thaojuice
  Date: 31/12/2021
  Time: 13:42
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Edit Product</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<div class="container">
    <h2>Add New Product</h2>
    <form action="/products?action=edit" method="post">
        <div class="form-group" hidden>
            <label for="id">Id:</label>
            <input type="text" name="id" class="form-control" id="id" value="${product.getId()}">
        </div>

        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" name="name" class="form-control" id="name" value="${product.getName()}">
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="text" name="price" class="form-control" id="price" value="${product.getPrice()}">
        </div>
        <div class="form-group">
            <label for="quantity">Quantity:</label>
            <input type="text" name="quantity" class="form-control" id="quantity" value="${product.getQuantity()}">
        </div>
        <div class="form-group">
            <label for="color">Color:</label>
            <input type="text" name="color" class="form-control" id="color" value="${product.getColor()}">
        </div>
        <div class="form-group">
            <label for="description">Description:</label>
            <input type="text" name="description" class="form-control" id="description"
                   value="${product.getDescription()}">
        </div>
        <div class="form-group">
            <label for="class-room">Category:</label>
            <select name="idCategory" class="form-control" id="class-room">
                <c:forEach items='${requestScope["categories"]}' var="category">
                    <option value="${category.id}">${category.name}</option>
                </c:forEach>
            </select>
        </div>
        <button type="submit" class="btn btn-success">Edit Product</button>
    </form>
</div>
</body>
</html>



