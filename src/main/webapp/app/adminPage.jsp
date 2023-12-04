<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Entry Form</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
    
        body {
            margin: 0;
            padding: 0;
        }

        input {
            margin-right: 50px;
            width: 90%;
            height: 30px;
            border-radius: 5px;
            background-color: transparent;
        }

        li {
            margin-top: 40px;
            text-align: left;
        }

        .modal {
            display: none;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            justify-content: center;
            align-items: center;
        }

        .modal-content {
            background-color: #C2D7EB;
            padding: 20px;
            width: 450px;
            height: 100px;
            border-radius: 8px;
            text-align: center;
        }

        #confirmationModal button {
            margin: 0 10px;
            border-radius: 5px;
        }

    </style>
</head>
<body>
<div style="display: flex; height: 100vh;">
    <div style="display: flex; flex-direction: column; height: 100vh; width: 300px; background: #1A1D16; padding: 1.7rem 0rem; text-align: center;">
        <h2 style="margin: 0px 20px; text-decoration: none; font-size: 30px; cursor: pointer;">
            <span style="color: #E0588E;"><span style="font-size: 50px">C</span>ool</span></span>
            <span style="color: #49A3C8;"><span style="font-size: 50px">S</span>tuff</span>
        </h2>
        <h2 style="color: white;">ADMIN DASHBOARD</h2>
        <ul style="display: block; list-style-type: none;" class="navbar">
       
        <li class="navitem"><a href="./adminhome" style="color: white; text-decoration: none; padding: 14px 16px;">HOME</a></li>
         <li class="navitem"><a href="./viewUsers" style="color: white; text-decoration: none; padding: 14px 16px;">USERS</a></li>
             <li class="navitem"><a href="./orders" style="color: white; text-decoration: none; padding: 14px 16px;">ORDERS</a></li>
            <li class="navitem"><a href="./viewAll" style="color: white; text-decoration: none; padding: 14px 16px;">PRODUCTS</a></li>
        </ul>
        <a href="./logout" style="text-align: left; margin-left: 40px; text-decoration: none; color: white; padding: 14px 16px;">LOGOUT</a>
    </div>
    <div style="padding-left: 140px; width: 100%; height: auto; background: #EFF7FB;">

       

${requestScope.content}

    </div>
    <div id="confirmationModal" class="modal">
    <div class="modal-content" style="box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.9); padding: 20px; border-radius: 8px;">
        <p>Continue to delete this item? This is Irreversible..</p>
        <button id="confirmDelete" style="background: #E0588E;">Yes</button>
        <button id="cancelDelete" style="background: #49A3C8;">Cancel</button>
    </div>
</div>


</div>

<script src="js/deleteFromDb.js"></script>
<script src="js/updateItem.js"></script>
</body>
</html>
