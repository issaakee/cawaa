<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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

        /* Styles spécifiques au formulaire des lignes de la facture */
        form {
            margin: 0 auto;
            margin-top: 40px;
            width: 300px;
        }
    </style>
</head>
<body>
    <h1>Ajouter une nouvelle ligne de facture </h1>
    <h2>Lignes de la facture</h2>
    <form action="AjouterLigneFactureServlet" method="post">
        <label for="numero_facture">N° de facture:</label>
        <select id="numero_facture" name="numero_facture">
            <sql:setDataSource var="dataSource" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/gestionn" user="root" password="ledwatch" />

            <sql:query dataSource="${dataSource}" var="facturesQuery">
                SELECT n_facture FROM facture
            </sql:query>

            <c:forEach var="facture" items="${facturesQuery.rows}">
                <option value="${facture.n_facture}">${facture.n_facture}</option>
            </c:forEach>
        </select>

        <label for="ref_article">Réf. article:</label>
        <select id="ref_article" name="ref_article">
            <sql:query dataSource="${dataSource}" var="articlesQuery">
                SELECT ref_article FROM article
            </sql:query>

            <c:forEach var="article" items="${articlesQuery.rows}">
                <option value="${article.ref_article}">${article.ref_article}</option>
            </c:forEach>
        </select>

        <label for="quantite_vendue">Quantité vendue:</label>
        <input type="number" id="quantite_vendue" name="quantite_vendue" required><br>

        <input type="submit" value="Ajouter">
    </form>
</body>
</html>
