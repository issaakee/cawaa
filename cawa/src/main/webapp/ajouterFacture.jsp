<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Styles CSS */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 20px;
            }

            h1 {
                color: #333;
                font-size: 24px;
            }

            h2 {
                color: #666;
                font-size: 18px;
                margin-top: 30px;
            }

            label {
                display: block;
                margin-top: 10px;
            }

            input[type="text"],
            input[type="date"],
            input[type="number"],
            select {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }

            input[type="submit"] {
                padding: 10px 20px;
                background-color: #4CAF50;
                color: #fff;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type="submit"]:hover {
                background-color: #45a049;
            }

            /* Styles spécifiques au formulaire de la facture */
            form {
                margin: 0 auto;
                margin-bottom: 40px;
                width: 300px;
            }
        </style>
    </head>
    <body>
        <h1>Ajouter une nouvelle facture</h1>

        <h2>Informations sur la facture</h2>
        <form action="AjouterFactureServlet" method="post">
            <label for="client">Client:</label>
            <select id="client" name="client" required>
                <sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/gestionn" user="root" password="ledwatch" />

                <sql:query dataSource="${dataSource}" var="clientsQuery">
                    SELECT id_client FROM client
                </sql:query>

                <c:forEach var="client" items="${clientsQuery.rows}">
                    <option value="${client.id_client}">${client.id_client}</option>
                </c:forEach>
            </select><br>
            <label for="numero_facture">N° de facture:</label>
            <input type="text" id="numero_facture" name="numero_facture" required><br>

            <label for="date_facture">Date de facture:</label>
            <input type="date" id="date_facture" name="date_facture" required><br>

            <label for="mode_paiement">Mode de paiement:</label>
            <select id="mode_paiement" name="mode_paiement" required>
                <option value="especes">Espèces</option>
                <option value="carte">Carte bancaire</option>
                <option value="cheque">Chèque</option>
            </select><br>

            <input type="submit" value="Ajouter">
        </form>

        <div class="item3"></div><!-- Element pour afficher le contenu de ajouterLigneFacture.jsp -->

        <script>
            document.querySelector('form').addEventListener('submit', function(event) {
                event.preventDefault();
                chargerContenuServlet('ajouterLigneFacture.jsp');
            });

            function chargerContenuServlet(servlet) {
                var xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function() {
                    if (this.readyState == 4 && this.status == 200) {
                        document.querySelector('.item3').innerHTML = this.responseText;
                    }
                };
                xhttp.open("GET", servlet, true);
                xhttp.send();
            }
        </script>

    </body>
</html>
