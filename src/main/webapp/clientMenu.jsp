<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Client Management System</title>
    <style>
        body {
            background-color: lightyellow;
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
<button onclick="createClient()">Create Client</button>
<button onclick="showListOfClients()">Show List of Clients</button>
<button onclick="updateClient()">Update Client</button>
<button onclick="deleteClient()">Delete Client</button>
<button onclick="goBack()">Go Back</button>

<script>
    function createClient() {
        window.location.href = "createClient.jsp";
    }
    function showListOfClients() {
        window.location.href = "showListOfClients.jsp";
    }
    function updateClient() {
        window.location.href = "updateClient.jsp";
    }
    function deleteClient() {
        window.location.href = "deleteClient.jsp";
    }
    function goBack() {
        window.history.back();
    }
</script>
</body>
</html>

