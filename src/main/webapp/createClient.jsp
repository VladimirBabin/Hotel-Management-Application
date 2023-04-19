<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 19.04.2023
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Client</title>
    <style>
        body {
            background-color: lightyellow;
        }
        label {
            display: block;
            font-size: 16px;
            margin: 10px;
        }
        input[type="text"], input[type="number"] {
            font-size: 16px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid gray;
            margin: 10px;
            width: 300px;
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
<h1>Create Client</h1>
<form onsubmit="createClient(event)">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required>

    <label for="phoneNumber">Phone Number:</label>
    <input type="number" id="phoneNumber" name="phoneNumber" required>

    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required>

    <button type="submit">Create</button>
</form>

<button onclick="goBack()">Go Back</button>

<script>
    function createClient(event) {
        event.preventDefault();
        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        const id = document.getElementById("id").value;
        const newClient = new Client(firstName, lastName, phoneNumber, id);
        console.log('New client created:', newClient);

        alert('New client created successfully!');
        event.target.reset();
    }
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>


