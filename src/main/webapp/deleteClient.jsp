<%--
  Created by IntelliJ IDEA.
  User: Maryana
  Date: 19.04.2023
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Delete Client</title>
    <style>
        body {
            background-color: lightyellow;
        }
        label {
            display: block;
            font-size: 16px;
            margin: 10px;
        }
        input[type="text"] {
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
<h1>Delete Client</h1>
<form onsubmit="deleteClient(event)">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required>

    <button type="submit">Delete</button>
</form>

<button onclick="goBack()">Go Back</button>

<script>
    function deleteClient(event) {
        event.preventDefault();
        const id = document.getElementById("id").value;
        console.log('Client with ID', id, 'will be deleted.');

        alert('Client with ID ' + id + ' has been deleted successfully!');
        event.target.reset();
    }
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>

