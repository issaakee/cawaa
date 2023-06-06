package cawa;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/AjouterArticleServlet"})

public class AjouterArticleServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JavaDBConnect dbConnect = new JavaDBConnect();
            
            // Récupérer les valeurs saisies dans le formulaire
            String refArticle = request.getParameter("ref");
            String nomArticle = request.getParameter("nom");
            int quantite = Integer.parseInt(request.getParameter("quantite"));
            double prixVente = Double.parseDouble(request.getParameter("prix"));
            
            // Obtention d'une connexion depuis la classe JavaDBConnect
            Connection con = dbConnect.getConnection();
            
            // Créer la requête SQL avec des paramètres
            String query = "INSERT INTO article (ref_article, nom_article, quantité, prix_vente) VALUES (?, ?, ?, ?)";
            
            // Créer un objet PreparedStatement avec la requête SQL
            PreparedStatement ps = con.prepareStatement(query);
            
            // Définir les valeurs des paramètres
            ps.setString(1, refArticle);
            ps.setString(2, nomArticle);
            ps.setInt(3, quantite);
            ps.setDouble(4, prixVente);
            
            // Exécuter la requête
            ps.executeUpdate();
            
            // Ajouter le message de succès dans la page d'accueil
                String message = "L'article a été ajouté avec succès.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("acceill.jsp").forward(request, response);
            
            
            // Fermer la connexion et le PreparedStatement
            ps.close();
            con.close();
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            PrintWriter out = response.getWriter();
            out.print("Une erreur s'est produite lors de l'ajout de l'article. Veuillez réessayer.");
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gérer les autres exceptions
            PrintWriter out = response.getWriter();
            out.print("Une erreur s'est produite lors de l'ajout de l'article. Veuillez réessayer.");
            ex.printStackTrace();
        }
        // Rediriger vers la page d'accueil
        response.sendRedirect("acceill.jsp");
    }
}
