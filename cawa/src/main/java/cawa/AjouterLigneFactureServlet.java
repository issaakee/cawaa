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

@WebServlet("/AjouterLigneFactureServlet")
public class AjouterLigneFactureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String numeroFacture = request.getParameter("numero_facture");
        String refArticle = request.getParameter("ref_article");
        int quantiteVendue = Integer.parseInt(request.getParameter("quantite_vendue"));

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Établir la connexion à la base de données
            JavaDBConnect dbConnect = new JavaDBConnect();
            conn = dbConnect.getConnection();

            // Préparer la requête d'insertion
            String query = "INSERT INTO ligne_facture (n_facture, ref_article, quantité_vendue) VALUES (?, ?, ?)";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, numeroFacture);
            stmt.setString(2, refArticle);
            stmt.setInt(3, quantiteVendue);

            // Exécuter la requête
            stmt.executeUpdate();

            // Rediriger vers une page de succès
            response.sendRedirect("acceill.jsp");
        } 
           
            catch (ClassNotFoundException | SQLException ex) {
            // Afficher un message d'erreur personnalisé
            String errorMessage = "Une erreur s'est produite lors de l'ajout de la ligne de facture : " + ex.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            request.getRequestDispatcher("error.jsp").forward(request, response);
       
        } finally {
            // Fermer les ressources
            if (stmt != null) {
                try {
                    stmt.close();}
                catch (SQLException ex) {
                    // Afficher un message d'erreur personnalisé
                    String errorMessage = "Une erreur s'est produite lors de la fermeture de la déclaration préparée : " + ex.getMessage();
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    // Afficher un message d'erreur personnalisé
                    String errorMessage = "Une erreur s'est produite lors de la fermeture de la connexion : " + ex.getMessage();
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            }
        }
    }
}
