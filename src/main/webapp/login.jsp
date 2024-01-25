<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/cake.css" />
<link rel="stylesheet" href="css/login.css" />
    <title>Rev Charmant</title>
    <script
      src="https://kit.fontawesome.com/42e062e6e4.js"
      crossorigin="anonymous"
    ></script>

</head>
<body>

	<div id="page">
      <nav class="navbar">
        <div class="navbar_logo">
          <i class="fa-solid fa-cake-candles"></i>
          <a href="">Rev Charmant</a>
        </div>

        <ul class="navbar_menu">
          <li><a href="">HOME</a></li>
          <li><a href="">ABOUT US</a></li>
          <li><a href="">MENU</a></li>
          <li><a href="">NOTICE</a></li>
          <li><a href="">CONTACT</a></li>
          <li><a href="">FAQ</a></li>
        </ul>
        <ul class="navbar_icons">
          <li>
            <i class="fa-solid fa-cart-shopping"></i>
          </li>
          <li>
           <a href="login.jsp"> <i class="fa-solid fa-user"></i></a>
          </li>
        </ul>
      </nav>
      
    </div>
    
     <section>
        <form>
            <h1>Login</h1>
            <div class="inputbox">
                <ion-icon name="mail-outline"></ion-icon>
                <input type="email" required>
                <label for="">Id</label>
            </div>
            <div class="inputbox">
                <ion-icon name="lock-closed-outline"></ion-icon>
                <input type="password" required>
                <label for="">Password</label>
            </div>
            <div class="forget">
                <label for=""><input type="checkbox">Remember Me</label>
              <a href="#">Forget Password</a>
            </div>
            <button>Log in</button>
            <button>Register</button>
           
        </form>
    </section>
</body>
    
    
    
</body>
</html>