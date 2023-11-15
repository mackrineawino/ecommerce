<%@ page isELIgnored="false" %>
<%@ page import="com.ecom.app.model.view.navbar.NavBar"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Fira+Sans:wght@500&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
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

              .user-container {
            position: relative;
            cursor: pointer;
        }
         .user-menu {
        display: none;
        }

        .user-icon {
            font-size: 30px;
            color: #49A3C8;
            padding-right: 10px;
        }

        .user-menu {
            display: none;
            position: absolute;
            top: 100px;
            right: 0;
            background-color: #f1f1f1;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 10px;
        }

        .user-container:hover .user-menu {
            display: block;
        }
    </style>
</head>
<body>
    <div style=" display: flex; justify-content: space-between; background-color: #f1f1f1; border-radius: 5px; z-index: 100; position: sticky; top: 0;">
        <div style="margin-top: 11px; margin-left: 20px;">
            <h2><a href="./home" style="text-decoration: none; font-size: 30px; cursor: pointer;"><span style="color: #E0588E;"><span style="font-size: 50px">C</span>ool</span><span style="color: #49A3C8;"><span style="font-size: 50px">S</span>tuff</span></a></h2>
        </div>
        <div class="navbar" style="margin-top: 12px;">
         <jsp:useBean id="navBarMenu" class="com.ecom.app.model.view.navbar.NavBar" />
        <jsp:setProperty name="navBarMenu" property="activeLink" value='${requestScope.activeMenu}' />
        ${navBarMenu.menu}
        </div>
        <div style="display: flex;">
    <a href="./addToCart" style="margin: 40px 0px; margin-left: 70px; position: relative;">
    <i class="fa fa-cart-plus" aria-hidden="true" style="font-size: 30px; color: #49A3C8; cursor: pointer; margin-right: 30px;">
        <span id="cart-counter" style="position: absolute; top: -5px; right: 15px; background-color: #E0588E; color: white; border-radius: 50%; padding: 2px 6px; font-size: 12px; display: none;"></span>
    </i>
</a>


    <div class="user-container">
        <i class="fas fa-user user-icon" onclick="toggleUserMenu()" style="margin: 40px 0px; margin-right: 20px;"></i>
        <div class="user-menu" style="display: none;">
            <a href="./profile" style="text-decoration: none; font: 20px; color: #49A3C8;">Profile</a>
            <a href="./logout" onclick="logout()"style="text-decoration: none; font: 20px; color: #49A3C8;">Logout</a>
        </div>
    </div>
</div>

    </div>
        ${requestScope.content}

    <script src="js/toggleUserMenu.js"></script>
    <script src="js/AddToCart.js"></script>
    <script src="js/DeleteFromCart.js"></script>
</body>
</html>
