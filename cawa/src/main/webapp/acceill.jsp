<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
    <title>TODO supply a title</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <style>
        * {
            margin: 0;
            padding: 0;
        }

        html,
        body,
        .container {
            height: 100%;
        }

        .container {
            display: grid;
            grid-template-columns: 150px 1fr;
            grid-template-rows: 70px 1fr;
            grid-row-gap: 0px;
            grid-column-gap: 0px;
        }

        .box {}

        .item2 {
            background-color: #2d406f;
            grid-column-start: 1;
            grid-column-end: 2;
            grid-row-start: 1;
            grid-row-end: 3;
            text-align: center;
            padding-top: 70px;
            display: flex;
            flex-direction: column;
            border: 1px solid white;
        }

        .item2 a {
            margin-top: 40px;
        }

        .item1 {
            background-color: #7a2c4a;
            grid-column-start: 2;
            grid-column-end: 3;
            height: 50px;
            display: flex;
            flex-direction: row;
            align-items: center;
            justify-content: space-between;
            padding: 10px;
            border: 1px solid white;
        }

        .item1 a:not(:last-child) {
            margin-right: 5px;
        }

        .item1 .link.active {
            border: 2px solid white;
            border-radius: 5px;
            animation: borderAnimation 0.5s ease;
        }

        @keyframes borderAnimation {
            0% {
                transform: scale(1);
            }
            50% {
                transform: scale(1.2);
            }
            100% {
                transform: scale(1);
            }
        }

        .item3 {
            background-color: azure;
            grid-column-start: 2;
            grid-column-end: 3;
            margin-top: 3px;
            overflow: auto;
        }
    </style>
</head>

<body>
    <div class="container">
        <div class="box item1">
            <a href="#" style="color: white;" onclick="chargerContenuServlet('ClientsServlet')" class="link">clients</a>
            <a>|</a>
            <a href="#" style="color: white;" onclick="chargerContenuServlet('ArticlesServlet')" class="link">articles</a>
            <a>|</a>
            <a href="#" style="color: white;" onclick="chargerContenuServlet('FacturesServlet')" class="link">factures</a>
            <a href="login.jsp" style="color: white;">se déconnecter</a>
        </div>
        <div class="box item2">
            <a href="#" style="color: white;" onclick="chargerContenu('ajouterClient')">ajouter client</a>
            <a href="#" style="color: white;" onclick="chargerContenu('ajouterArticle')">ajouter article</a>
            <a href="#" style="color: white;" onclick="chargerContenu('ajouterFacture')">ajouter facture</a>
        </div>
        <div class="box item3">
            <div id="contentContainer">
                <%-- Content goes here --%>
            </div>
        </div>
    </div>

    <script>
        function showDetails(numFacture) {
            console.log('showDetails() function called.');
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    var detailsContainer = document.getElementById('detailsContainer');
                    detailsContainer.innerHTML = this.responseText;
                }
            };
            xhttp.open('GET', 'LignesFactureServlet?numero_facture=' + numFacture, true);
            xhttp.send();
        }

        function chargerContenu(page) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("contentContainer").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", page + ".jsp", true);
            xhttp.send();
        }

        function chargerContenuServlet(servlet) {
            var xhttp = new XMLHttpRequest();
            xhttp.onreadystatechange = function() {
                if (this.readyState == 4 && this.status == 200) {
                    document.getElementById("contentContainer").innerHTML = this.responseText;
                }
            };
            xhttp.open("GET", servlet, true);
            xhttp.send();

            var links = document.querySelectorAll('.item1 .link');
            links.forEach(function(link) {
                link.classList.remove('active');
            });
            event.target.classList.add('active');
        }

        var item3 = document.querySelector('.item3');
        item3.style.width = "100%";
        item3.style.height = "100%";
    </script>
</body>

</html>
