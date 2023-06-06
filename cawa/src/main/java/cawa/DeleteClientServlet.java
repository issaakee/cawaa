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
import javax.servlet.RequestDispatcher;

@WebServlet("/DeleteClientServlet")
public class DeleteClientServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer l'ID du client à supprimer depuis les paramètres de requête
        String clientId = request.getParameter("id");

        if (clientId != null && !clientId.isEmpty()) {
            try {
                JavaDBConnect dbConnect = new JavaDBConnect();

                // Obtention d'une connexion depuis la classe JavaDBConnect
                Connection con = dbConnect.getConnection();

                // Créer la requête SQL pour supprimer le client avec l'ID spécifié
                String query = "DELETE FROM client WHERE id_client = ?";

                // Créer un objet PreparedStatement pour exécuter la requête SQL avec l'ID du client
                PreparedStatement statement = con.prepareStatement(query);
                statement.setInt(1, Integer.parseInt(clientId));

                // Exécuter la requête SQL pour supprimer le client
                int rowsDeleted = statement.executeUpdate();

                // Fermer la connexion et le PreparedStatement
                statement.close();
                con.close();

                if (rowsDeleted > 0) {
                    // La suppression a réussi, définir l'attribut dans la session
                    HttpSession session = request.getSession();
                    session.setAttribute("deletionSuccess", true);
                } else {
                    // Aucun client n'a été supprimé (ID invalide)
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
                HttpSession session = request.getSession();
                session.setAttribute("deletionSuccess", false);
                session.setAttribute("errorMessage", "Erreur lors de la suppression du client.");
                ex.printStackTrace();
            }
        } else {
            // L'ID du client est manquant ou vide
            HttpSession session = request.getSession();
            session.setAttribute("deletionSuccess", false);
            session.setAttribute("errorMessage", "ID du client manquant ou invalide.");
        }

        // Inclure le contenu de la même page dans la réponse
        RequestDispatcher dispatcher = request.getRequestDispatcher("acceill.jsp");
        dispatcher.forward(request, response);
    }
}
