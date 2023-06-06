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

@WebServlet("/LignesFactureServlet")
public class LignesFactureServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroFacture = request.getParameter("numero_facture");

        try {
            JavaDBConnect dbConnect = new JavaDBConnect();

            // Obtention d'une connexion depuis la classe JavaDBConnect
            Connection con = dbConnect.getConnection();

            // Créer la requête SQL pour récupérer les lignes de facture associées au numéro de facture
            String query = "SELECT * FROM ligne_facture WHERE n_facture = ?";

            // Créer un objet PreparedStatement pour exécuter la requête SQL
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, numeroFacture);

            // Exécuter la requête SQL et obtenir le résultat
            ResultSet resultSet = statement.executeQuery();

            // Créer une chaîne HTML pour générer le tableau des lignes de facture
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
            htmlTable.append("<tr><th>Ref Article</th><th>N°-facture</th><th>Quantité</th></tr>");

            // Parcourir les résultats de la requête et générer les lignes du tableau
            while (resultSet.next()) {
                String refArticle = resultSet.getString("ref_article");
                String nFacture = resultSet.getString("n_facture");
                int quantite = resultSet.getInt("quantité_vendue");

                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(refArticle).append("</td>");
                htmlTable.append("<td>").append(nFacture).append("</td>");
                htmlTable.append("<td>").append(quantite).append("</td>");
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
            out.println("<title>Lignes de Facture</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Ligne de Facture</h1>");
            out.println(htmlTable.toString());
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            PrintWriter out = response.getWriter();
            out.println(ex);
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gérer les autres exceptions
            PrintWriter out = response.getWriter();
            out.println(ex);
            ex.printStackTrace();
        }
    }
}
