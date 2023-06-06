<!DOCTYPE html>
<html lang="en">
<head>
  <title>HASH TECHIE OFFICIAL</title>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@500&display=swap');
    * {
      margin: 0;
      padding: 0;
      font-family: 'poppins', sans-serif;
    }
    section {
      display: flex;
      justify-content: center;
      align-items: center;
      min-height: 100vh;
      width: 100%;
      background: url('background6.jpg') no-repeat;
      background-position: center;
      background-size: cover;
    }
    .form-box {
      position: relative;
      width: 400px;
      height: 450px;
      background: transparent;
      border: 2px solid rgba(255, 255, 255, 0.5);
      border-radius: 20px;
      backdrop-filter: blur(15px);
      display: flex;
      justify-content: center;
      align-items: center;
    }
    h2 {
      font-size: 2em;
      color: #fff;
      text-align: center;
    }
    .inputbox {
      position: relative;
      margin: 30px 0;
      width: 310px;
      border-bottom: 2px solid #fff;
    }
    .inputbox label {
      position: absolute;
      top: 50%;
      left: 5px;
      transform: translateY(-50%);
      color: #fff;
      font-size: 1em;
      pointer-events: none;
      transition: 0.5s;
    }
    input:focus ~ label,
    input:valid ~ label {
      top: -5px;
    }
    .inputbox input {
      width: 100%;
      height: 50px;
      background: transparent;
      border: none;
      outline: none;
      font-size: 1em;
      padding: 0 35px 0 5px;
      color: #fff;
    }
    .inputbox ion-icon {
      position: absolute;
      right: 8px;
      color: #fff;
      font-size: 1.2em;
      top: 20px;
    }

    button {
      width: 100%;
      height: 40px;
      border-radius: 40px;
      background: #fff;
      border: none;
      outline: none;
      cursor: pointer;
      font-size: 1em;
      font-weight: 600;
    }
input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #B74770;
            color: #fff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        input[type="submit"]:hover 
        {
            background-color: #D0517F;
        }
  </style>
</head>
<body>
  <section>
    <div class="form-box">
      <div class="form-value">
        <form method="post" action="loginHandler">
          <h2>Login</h2>
          
          <div class="inputbox">
            <ion-icon name="mail-outline"></ion-icon>
        <input type="text" name="username" id="username">
            <label for="">Username</label>
          </div>
          <div class="inputbox">
            <ion-icon name="lock-closed-outline"></ion-icon>
        <input type="password" name="password" id="password">
                    <label for="">Password</label>
          </div>

        <input type="submit" value="Login">

        </form>
      </div>
    </div>
  </section>
</body>
</html>
