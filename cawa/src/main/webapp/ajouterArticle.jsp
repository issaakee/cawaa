<%-- 
    Document   : ajouterArticle
    Created on : 2 juin 2023, 11:51:31
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
            input[type="number"] {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"] {
                background-color: #4CAF50;
                color: white;
                padding: 10px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

        </style>
    </head>
    <body>
         <form action="AjouterArticleServlet" method="post">
        <label for="ref">Ref-article:</label>
        <input type="text" id="ref" name="ref" required><br><br>
        
         <label for="nom">Nom-article:</label>
         <input type="text" id="nom" name="nom" required><br><br>
  
         <label for="quantite">Quantité:</label>
         <input type="number" id="quantite" name="quantite" required><br><br>
  
         <label for="prix">Prix-vente:</label>
         <input type="number" id="prix" name="prix" step="0.01" required><br><br>
  
          <input type="submit" value="Ajouter">
       </form>
    </body>
</html>
