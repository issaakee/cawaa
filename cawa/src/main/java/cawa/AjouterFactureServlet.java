package cawa;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AjouterFactureServlet")
public class AjouterFactureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Récupérer les paramètres du formulaire
        String client = request.getParameter("client");
        String numeroFacture = request.getParameter("numero_facture");
        String dateFacture = request.getParameter("date_facture");
        String modePaiement = request.getParameter("mode_paiement");

        // Connexion à la base de données
        JavaDBConnect dbConnect = new JavaDBConnect();
        try {
            // Préparer la requête SQL pour insérer la facture
            try (Connection con = dbConnect.getConnection()) {
                // Préparer la requête SQL pour insérer la facture
                String sql = "INSERT INTO facture (id_client, n_facture, date_facture, mode_paiement) VALUES (?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, client);
                statement.setString(2, numeroFacture);
                statement.setString(3, dateFacture);
                statement.setString(4, modePaiement);

                // Exécuter la requête SQL
                statement.executeUpdate();

                // Fermer la connexion à la base de données
                statement.close();
            }

            // Rediriger vers la page ajouterLigneFacture.jsp
            RequestDispatcher dispatcher = request.getRequestDispatcher("ajouterLigneFacture.jsp");
            dispatcher.forward(request, response);
            request.getSession().setAttribute("ajouterLigneFactureURL","ajouterLignefacture.jsp");
            
            
            } catch (ClassNotFoundException | SQLException e) {
            // Afficher un message d'erreur personnalisé
            String errorMessage = "Une erreur s'est produite lors de l'ajout de la facture : " + e.getMessage();
            request.setAttribute("errorMessage", errorMessage);
            RequestDispatcher dispatcher = request.getRequestDispatcher("error.jsp");
            dispatcher.forward(request, response);
            }
        response.sendRedirect("acceill.jsp");
    }
}
