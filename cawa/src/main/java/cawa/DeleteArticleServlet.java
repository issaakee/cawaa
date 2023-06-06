package cawa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/DeleteArticleServlet")
public class DeleteArticleServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer la référence de l'article à supprimer
        String refArticle = request.getParameter("ref_article");

        if (refArticle != null && !refArticle.isEmpty()) {
            try {
                JavaDBConnect dbConnect = new JavaDBConnect();
                Connection con = dbConnect.getConnection();

                // Requête SQL pour supprimer l'article avec la référence donnée
                String query = "DELETE FROM article WHERE ref_article = ?";
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, refArticle);

                // Exécuter la requête de suppression
                int rowsDeleted = statement.executeUpdate();

                statement.close();
                con.close();

                if (rowsDeleted > 0) {
                    // La suppression a réussi, définir l'attribut dans la session
                    HttpSession session = request.getSession();
                    session.setAttribute("deletionSuccess", true);
                } else {
                    // Aucun article n'a été supprimé (référence invalide)
                    HttpSession session = request.getSession();
                    session.setAttribute("deletionSuccess", false);
                }
            } catch (SQLException ex) {
                // Gérer les erreurs liées à la base de données
                HttpSession session = request.getSession();
                session.setAttribute("deletionSuccess", false);
                session.setAttribute("errorMessage", ex.getMessage());
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
                // Gérer les exceptions liées à la classe JavaDBConnect
                HttpSession session = request.getSession();
                session.setAttribute("deletionSuccess", false);
                session.setAttribute("errorMessage", "Erreur lors de la suppression de l'article.");
                ex.printStackTrace();
            }
        } else {
            // Référence de l'article manquante ou vide
            HttpSession session = request.getSession();
            session.setAttribute("deletionSuccess", false);
            session.setAttribute("errorMessage", "Référence de l'article manquante ou invalide.");
        }

        // Rediriger vers la page d'accueil
        response.sendRedirect("acceill.jsp");
    }
}
