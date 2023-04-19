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
    <title>Client List</title>
    <style>
        body {
            background-color: lightyellow;
        }
        table {
            border-collapse: collapse;
            margin: 20px;
            width: 80%;
        }
        th, td {
            border: 1px solid gray;
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: green;
            color: white;
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
<h1>Client List</h1>
<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Phone Number</th>
    </tr>
    </thead>
    <tbody id="clientList">

    </tbody>
</table>

<button onclick="goBack()">Go Back</button>

<script>
    function showClientList() {
        const clientList = document.getElementById("clientList");
        // Use your own implementation to retrieve the client data from the database
        const clients = [
            { id: "1", firstName: "John", lastName: "Doe", phoneNumber: "555-1234" },
            { id: "2", firstName: "Jane", lastName: "Doe", phoneNumber: "555-5678" },
            { id: "3", firstName: "Bob", lastName: "Smith", phoneNumber: "555-9876" }
        ];
        clients.forEach((client) => {
            const row = document.createElement("tr");
            const idCell = document.createElement("td");
            const firstNameCell = document.createElement("td");
            const lastNameCell = document.createElement("td");
            const phoneNumberCell = document.createElement("td");
            idCell.textContent = client.id;
            firstNameCell.textContent = client.firstName;
            lastNameCell.textContent = client.lastName;
            phoneNumberCell.textContent = client.phoneNumber;
            row.appendChild(idCell);
            row.appendChild(firstNameCell);
            row.appendChild(lastNameCell);
            row.appendChild(phoneNumberCell);
            clientList.appendChild(row);
        });
    }
    function goBack() {
        window.history.back();
    }
    window.onload = showClientList;
</script>
</body>
</html>

