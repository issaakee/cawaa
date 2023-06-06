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

@WebServlet("/SupprimerFactureServlet")
public class SupprimerFactureServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String numeroFacture = request.getParameter("numero_facture");

        if (numeroFacture != null && !numeroFacture.isEmpty()) {
            try {
                JavaDBConnect dbConnect = new JavaDBConnect();
                Connection con = dbConnect.getConnection();

                // Créer la requête SQL pour supprimer la facture
                String query = "DELETE FROM facture WHERE n_facture = ?";

                // Préparer la requête avec le numéro de facture
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, numeroFacture);

                // Exécuter la requête
                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    // La suppression a réussi
                    response.sendRedirect("acceill.jsp");
                } else {
                    // Aucune facture n'a été supprimée
                    response.getWriter().println("La suppression de la facture a échoué.");
                }

                // Fermer la connexion et le PreparedStatement
                statement.close();
                con.close();

            } catch (SQLException ex) {
                // Gérer les erreurs liées à la base de données
                response.getWriter().println(ex);
                ex.printStackTrace();
            } catch (Exception ex) {
                // Gérer les autres exceptions
                response.getWriter().println(ex);
                ex.printStackTrace();
            }
        } else {
            response.getWriter().println("Le numéro de facture n'a pas été fourni.");
        }
    }
}
