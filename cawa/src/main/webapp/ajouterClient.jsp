<%-- 
    Document   : AjouterClient
    Created on : 2 juin 2023, 11:49:27
    Author     : PCNET
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style> 
            form {
                width: 300px;
                margin: 0 auto;
            }

            label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }

            input[type="text"],
            input[type="number"],
            input[type="email"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="email"] {
                width: 100%;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                margin-top: 10px;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

        </style>

    </head>
    <body>
        <form method="post" action="AjouterClientServlet">
            <label for="nom">Nom </label>
            <input type="text" id="nom" name="nom" required><br>
  
            <label for="telephone">Téléphone </label>
            <input type="text" id="telephone" name="telephone" required><br>
  
            <label for="email">Email </label>
            <input type="email" id="email" name="email" class="email-field" required><br>
  
            <input type="submit" value="Ajouter">
        </form>
    </body>
</html>
