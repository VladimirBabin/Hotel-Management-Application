<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 19.04.2023
  Time: 11:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Main Menu</title>
    <style>
        body {
            background-color: lightyellow;
        }
        h1 {
            font-size: 24px;
            margin: 20px;
        }
        button {
            background-color: green;
            color: white;
            font-size: 16px;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin: 10px;
        }
    </style>
</head>
<body>
<h1>Main Menu</h1>

<button onclick="location.href='clientMenu.jsp'">Client Management</button>
<button onclick="location.href='apartment-management.jsp'">Apartment Management</button>
<button onclick="location.href='utility-management.jsp'">Utility Management</button>
<button onclick="location.href='check-in.jsp'">Check-In</button>
<button onclick="location.href='check-out.jsp'">Check-Out</button>
<button onclick="location.href='current-price.jsp'">Get Current Price</button>

</body>
</html>

