package cawa;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FacturesServlet")
public class FacturesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            JavaDBConnect dbConnect = new JavaDBConnect();

            // Obtention d'une connexion depuis la classe JavaDBConnect
            Connection con = dbConnect.getConnection();

            // Créer la requête SQL pour récupérer la liste des factures
            String query = "SELECT id_client, n_facture, date_facture, mode_paiement FROM facture";

            // Créer un objet PreparedStatement pour exécuter la requête SQL
            PreparedStatement statement = con.prepareStatement(query);

            // Exécuter la requête SQL et obtenir le résultat
            ResultSet resultSet = statement.executeQuery();

            // Créer une chaîne HTML pour générer le tableau des factures
            StringBuilder htmlTable = new StringBuilder();
            htmlTable.append("<style>");
            htmlTable.append("table {");
            htmlTable.append("  width: 80%;");
            htmlTable.append("  margin: 0 auto;");
            htmlTable.append("  border-collapse: collapse;");
            htmlTable.append("}");
            htmlTable.append("th, td {");
            htmlTable.append("  padding: 8px;");
            htmlTable.append("  border: 1px solid #ddd;");
            htmlTable.append("}");
            htmlTable.append("th {");
            htmlTable.append("  background-color: #f2f2f2;");
            htmlTable.append("}");
            htmlTable.append("</style>");
            htmlTable.append("<table>");
            htmlTable.append(
                    "<tr><th>ID Client</th><th>N°-facture</th><th>Date-facture</th><th>Mode-paiement</th><th>Détails</th><th>Supprimer</th></tr>");

            // Parcourir les résultats de la requête et générer les lignes du tableau
            while (resultSet.next()) {
                String idClient = resultSet.getString("id_client");
                String numFacture = resultSet.getString("n_facture");
                String dateFacture = resultSet.getString("date_facture");
                String modePaiement = resultSet.getString("mode_paiement");

                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(idClient).append("</td>");
                htmlTable.append("<td>").append(numFacture).append("</td>");
                htmlTable.append("<td>").append(dateFacture).append("</td>");
                htmlTable.append("<td>").append(modePaiement).append("</td>");
                htmlTable.append("<td><button type=\"button\" onclick=\"showDetails('").append(numFacture).append("');\">Détails</button></td>");
                htmlTable.append("<td><form method=\"post\" action=\"SupprimerFactureServlet\" onsubmit=\"return confirm('Êtes-vous sûr de vouloir supprimer cette facture ?');\">");
                htmlTable.append("<input type=\"hidden\" name=\"numero_facture\" value=\"").append(numFacture)
                        .append("\" />");
                htmlTable.append("<button type=\"submit\" style=\"color: red;\">Supprimer</button>");
                htmlTable.append("</form></td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table>");

            // Fermer la connexion, le Statement et le ResultSet
            resultSet.close();
            statement.close();
            con.close();

            // Envoyer la réponse HTTP avec le contenu du tableau
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des Factures</title>");
            out.println("<script>");
            out.println("function showDetails(numFacture) {");
            out.println("    console.log('showDetails() function called.');"); // Added console log statement
            out.println("    // Effectuer une requête AJAX pour récupérer les lignes de facture associées au numéro de facture");
            out.println("    var xhttp = new XMLHttpRequest();");
            out.println("    xhttp.onreadystatechange = function() {");
            out.println("        if (this.readyState == 4 && this.status == 200) {");
            out.println("            // Afficher le tableau des lignes de facture dans le conteneur dédié");
            out.println("            var detailsContainer = document.getElementById('detailsContainer');");
            out.println("            detailsContainer.innerHTML = this.responseText;");
            out.println("        }");
            out.println("    };");
            out.println("    xhttp.open('GET', 'LignesFactureServlet?numero_facture=' + numFacture, true);");
            out.println("    xhttp.send();");
            out.println("}");
            out.println("</script>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des Factures</h1>");
            out.println(htmlTable.toString());
            out.println("<div id=\"detailsContainer\"></div>");
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            PrintWriter out = response.getWriter();
            out.println("Erreur lors de l'accès à la base de données : " + ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gérer les autres exceptions
            PrintWriter out = response.getWriter();
            out.println("Une erreur s'est produite : " + ex.getMessage());
            ex.printStackTrace();
        }
     
    }
}
