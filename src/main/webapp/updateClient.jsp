<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 19.04.2023
  Time: 11:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Client</title>
    <style>
        body {
            background-color: lightyellow;
        }
        label {
            display: block;
            margin: 10px;
            font-size: 16px;
        }
        input {
            font-size: 16px;
            padding: 10px;
            margin: 10px;
            border: 1px solid gray;
            border-radius: 5px;
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
<h1>Update Client</h1>

<form id="updateForm">
    <label for="clientId">Client ID:</label>
    <input type="text" id="clientId" name="clientId" required>

    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required>

    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required>

    <label for="phoneNumber">Phone Number:</label>
    <input type="text" id="phoneNumber" name="phoneNumber" required>

    <button type="submit">Update</button>
</form>

<button onclick="goBack()">Go Back</button>

<script>
    const updateForm = document.getElementById("updateForm");
    updateForm.addEventListener("submit", function(event) {
        event.preventDefault();
        const clientId = document.getElementById("clientId").value;
        const firstName = document.getElementById("firstName").value;
        const lastName = document.getElementById("lastName").value;
        const phoneNumber = document.getElementById("phoneNumber").value;
        // Use your own implementation to update the client data in the database
        console.log(`Updating client with ID ${clientId} to ${firstName} ${lastName}, ${phoneNumber}`);
        updateForm.reset();
    });
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>

