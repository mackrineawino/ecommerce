<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>CoolStuff</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap" rel="stylesheet">
    <style>
        body {
            font-family: 'Fira Sans', sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-position: center;
            background-size: cover;
            backdrop-filter: blur(2px);
            background-image: url(http://www.centreequestre-lugere.fr/lugere/fille/jordan-spizike-femme-pas-cher---1.jpg);
        }

        .container {
            width: 400px;
            padding: 20px;
            border: 1px solid #ccc;
            background-color: white;
            text-align: center;
            margin: 0 auto;
            border-radius: 5px;
        }

        .container label {
            text-align: left;
            display: block;
        }

        .container input {
            border-radius: 5px;
            border-color: #9FA6AE;
        }

        input[type="text"],
        input[type="password"] {
            width: 100%;
            padding: 12px 20px;
            margin: 8px 0;
            box-sizing: border-box;
        }

        button {
            background-color: #49A3C8;
            color: white;
            padding: 14px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 100%;
            margin-bottom: 5px;
            border-radius: 5px;
            font-size: 15px;
        }

        .container input::placeholder {
            color: #CECFD4;
        }

        button:hover {
            background-color: #E0588E;
        }

        .container h2 {}

    </style>
</head>
<body>
    <div class="container">
        <h2 style="color: #49A3C8; font-size: 30px">Welcome to <span style="color: #E0588E;"><span style="font-size: 50px">C</span>ool</span><span style="font-size: 50px">S</span>tuff</h2>
        <h3 style="color: #9FA6AE;">Please SignUp</h3>
        <c:if test="${not empty registrationSuccess}">
            <h3 style="color: green">${registrationSuccess}</h3>
        </c:if>
        <form action="./user" method="post">
            <label for="username" style="font-size: 14px">Username</label>
            <input type="text" id="username" name="username" placeholder="kwach" required>

              <label for="email" style="font-size: 14px">Email</label>
            <input type="text" id="email" name="email" placeholder="kwach@test.com" required>

            <label for="password" style="font-size: 14px">Password</label>
            <input type="password" id="password" name="password" placeholder="  *****" required>

            <label for="confirmPassword" style="font-size: 14px">Confirm Password</label>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="  *****" required>

            <div style="text-align: left; display: flex; justify-content: space-between;">
                <div style="display: flex;">
                    <label for="adminRadio">ADMIN</label>
                    <input type="radio" id="adminRadio" name="userType" value="ADMIN" required>
                </div>
                <div style="display: flex">
                    <label for="normalUserRadio">NORMAL USER</label>
                    <input type="radio" id="normalUserRadio" name="userType" value="NORMAL_USER" required>
                </div>
            </div>

            <button type="submit">Signup</button>
            <h5>Already have an account? <a href="./login">Login</a></h5>
        </form>
    </div>
</body>
</html>
