package cawa;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = {"/AjouterClientServlet"})
public class AjouterClientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            JavaDBConnect dbConnect = new JavaDBConnect();
            
            // Récupérer les valeurs saisies dans le formulaire
            String nom = request.getParameter("nom");
            String telephone = request.getParameter("telephone");
            String email = request.getParameter("email");
            
            // Créer la requête SQL avec des paramètres
            try (Connection con = dbConnect.getConnection();
                 PreparedStatement ps = con.prepareStatement("INSERT INTO client (nom_client, telephone, email) VALUES (?, ?, ?)")) {
                
                // Définir les valeurs des paramètres
                ps.setString(1, nom);
                ps.setString(2, telephone);
                ps.setString(3, email);
                
                // Exécuter la requête
                ps.executeUpdate();
                
                // Ajouter le message de succès dans la page d'accueil
                String message = "Le client a été ajouté avec succès.";
                request.setAttribute("message", message);
                request.getRequestDispatcher("acceill.jsp").forward(request, response);
            }
        } catch (SQLException ex) {
            // Gérer les erreurs liées à la base de données
            PrintWriter out = response.getWriter();
            out.print("<h1>Une erreur s'est produite lors de l'ajout du client.</h1>");
            ex.printStackTrace();
        } catch (Exception ex) {
            // Gérer les autres exceptions
            PrintWriter out = response.getWriter();
            out.print("<h1>Une erreur s'est produite lors de l'ajout du client.</h1>");
            ex.printStackTrace();
        }
        // Rediriger vers la page d'accueil
        response.sendRedirect("ClientServlet");
    }
}
