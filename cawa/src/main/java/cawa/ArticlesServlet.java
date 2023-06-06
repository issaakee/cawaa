package cawa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ArticlesServlet")
public class ArticlesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JavaDBConnect dbConnect = new JavaDBConnect();
            Connection con = dbConnect.getConnection();

            String query = "SELECT * FROM article";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

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
            htmlTable.append("<tr><th>Ref-article</th><th>Nom-article</th><th>Quantité</th><th>Prix-vente</th><th>Action</th></tr>");

            while (resultSet.next()) {
                String refArticle = resultSet.getString("ref_article");
                String nomArticle = resultSet.getString("nom_article");
                int quantite = resultSet.getInt("quantité");
                double prixVente = resultSet.getDouble("prix_vente");
                htmlTable.append("<tr>");
                htmlTable.append("<td>").append(refArticle).append("</td>");
                htmlTable.append("<td>").append(nomArticle).append("</td>");
                htmlTable.append("<td>").append(quantite).append("</td>");
                htmlTable.append("<td>").append(prixVente).append("</td>");
                htmlTable.append("<td>");
                htmlTable.append("<form method=\"post\" action=\"DeleteArticleServlet\">");
                htmlTable.append("<input type=\"hidden\" name=\"ref_article\" value=\"").append(refArticle).append("\" />");
                htmlTable.append("<input type=\"submit\" value=\"Supprimer\" class=\"delete-button\" onclick=\"return confirm('Êtes-vous sûr de vouloir supprimer cet article ?');\" />");
                htmlTable.append("</form>");
                htmlTable.append("</td>");
                htmlTable.append("</tr>");
            }

            htmlTable.append("</table>");

            resultSet.close();
            statement.close();
            con.close();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Liste des articles</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Liste des articles</h1>");
            out.println(htmlTable.toString());
            out.println("</body>");
            out.println("</html>");

        } catch (SQLException ex) {
            PrintWriter out = response.getWriter();
            out.println("Une erreur s'est produite lors de la récupération des articles.");
            ex.printStackTrace();
        } catch (Exception ex) {
            PrintWriter out = response.getWriter();
            out.println("Une erreur s'est produite lors de la récupération des articles.");
            ex.printStackTrace();
        }
    }
}
