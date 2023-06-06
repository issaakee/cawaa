
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import static jdk.nashorn.internal.runtime.Debug.id;

public class Article {
     int ref_article;
     String nom_article;
     int quantité;
     Double prix_vente;

    public Article() {
    }

    public Article(int ref_article, String nom_article, int quantité, Double prix_vente) {
        this.ref_article = ref_article;
        this.nom_article = nom_article;
        this.quantité = quantité;
        this.prix_vente = prix_vente;
    }

    public Article(String refArticle, String nomArticle, int quantite, double prixVente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getref_article() {
        return ref_article;
    }

    public String getnom_article() {
        return nom_article;
    }

    public int getquantité() {
        return quantité;
    }

    public Double getprix_vente() {
        return prix_vente;
    }

    public void setref_article(int ref_article) {
        this.ref_article = ref_article;
    }

    public void setnom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public void setquantité(int quantité) {
        this.quantité = quantité;
    }

    public void setprix_vente(Double prix_vente) {
        this.prix_vente = prix_vente;
    }
    // Méthode pour ajouter un nouvel article à la base de données
    public void ajouterArticle(Connection connection) throws SQLException {
        String sql = "INSERT INTO articles (ref_article, nom_article, prix_vente) VALUES (?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ref_article);
        statement.setString(2, nom_article);
        statement.setDouble(3, prix_vente);
        statement.executeUpdate();
        statement.close();
    }

    // Méthode pour supprimer un article de la base de données
    public void supprimerArticle(Connection connection) throws SQLException {
        String sql = "DELETE FROM articles WHERE ref_article = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, ref_article);
        statement.executeUpdate();
        statement.close();
    }
}
   
