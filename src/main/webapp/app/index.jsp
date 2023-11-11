<%@ page import="com.ecom.app.model.view.navbar.NavBar"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap" rel="stylesheet">
    <style>
    /* styles.css */

.topnav {
    overflow: hidden;
}

.topnav a {
    margin: 43px 30px;
    float: left;
    color: #49A3C8;
    text-align: center;
    text-decoration: none;
    font-size: 20px;
}

.topnav a:hover {
    color: #E0588E;
}

.topnav a.active {
    color: #E0588E;
}

.navbar {
     display: flex;
      align-items: center;
     }
.navbar .logout-link {
     text-decoration: none;
      color: #49A3C8; font-size:
       20px; cursor: pointer;
        margin-left: 20px;
        }
        body {
            margin: 0;
            padding: 0px 50px;
             font-family: 'Fira Sans', sans-serif;
              background: black; }
    </style>
</head>
<body>
    <div style=" display: flex; justify-content: space-between; background-color: #f1f1f1; border-radius: 5px; z-index: 100; position: sticky; top: 0;">
        <div style="margin-top: 12px; margin-left: 20px;">
            <h2><a href="./home" style="text-decoration: none; font-size: 30px; cursor: pointer;">
                <span style="color: #E0588E;">
                    <span style="font-size: 50px">C</span>ool
                </span>
                <span style="color: #49A3C8;">
                    <span style="font-size: 50px">S</span>tuff
                </span>
            </a></h2>
        </div>
        <div class="navbar">
           <%= new NavBar().menu((int)request.getAttribute("activeMenu"))%>
            <a href="./logout" class="logout-link">LOGOUT</a>
            <a href="./addToCart"><img style="width: 50px; color: #49A3C8; cursor: pointer; margin: 40px 0px; margin-left: 20px;" src="https://github.com/mackrineawino/images/blob/main/image-removebg-preview%20(3).png?raw=true"></a>
        </div>
    </div>
     <%= request.getAttribute("content")%>
    <script src="js/AddToCart.js"></script>
    <script src="js/DeleteFromCart.js"></script>
</body>
</html>
