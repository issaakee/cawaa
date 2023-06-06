package cawa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ClientsServlet")
public class ClientsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JavaDBConnect dbConnect = new JavaDBConnect();

            // Obtention d'une connexion depuis la classe JavaDBConnect
            Connection con = dbConnect.getConnection();

            // Créer la requête SQL pour récupérer la liste des clients
            String query = "SELECT id_client, nom_client, telephone, email FROM client";

            // Créer un objet Statement pour exécuter la requête SQL
            Statement statement = con.createStatement();

            // Exécuter la requête SQL et obtenir le résultat dans un objet ResultSet
            ResultSet resultSet = statement.executeQuery(query);

            // Créer une chaîne HTML pour le tableau des clients
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
            htmlTable.append(".delete-button {");
            htmlTable.append("  color: red;");
            htmlTable.append("}");
            htmlTable.append("</style>");
            htmlTable.append("<table>");
            htmlTable.append("<tr><th>ID</th><th>Nom</th><th>Téléphone</th><th>Email</th><th>Action</th></tr>");

            // Parcourir le résultat et ajouter les lignes du tableau
            while (resultSet.next()) {
                int id = resultSet.getInt("id_client");
                String nom = resultSet.getString("nom_client");
                String telephone = resultSet.getString("telephone");
                String email = resultSet.getString("email");

                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(id).append("</td>");
                htmlTable.append("<td>").append(nom).append("</td>");
                htmlTable.append("<td>").append(telephone).append("</td>");
                htmlTable.append("<td>").append(email).append("</td>");
                htmlTable.append("<td>");
                htmlTable.append("<form method=\"post\" action=\"DeleteClientServlet\">");
                htmlTable.append("<input type=\"hidden\" name=\"id\" value=\"").append(id).append("\" />");
                htmlTable.append("<input type=\"submit\" value=\"Supprimer\" class=\"delete-button\" onclick=\"return confirm('Êtes-vous sûr de vouloir supprimer ce client ?');\" />");

                htmlTable.append("</form>");
                htmlTable.append("</td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table>");

            // Fermer la connexion, le Statement et le ResultSet
            resultSet.close();
            statement.close();
            con.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des clients</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des clients</h1>");
            out.println(htmlTable.toString());
            out.println("</body>");
            out.println("</html>");
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            PrintWriter out = response.getWriter();
            out.println("Une erreur s'est produite lors de la récupération des clients.");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientsServlet.class.getName()).log(Level.SEVERE, null, ex);
    }
}
}
