package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;




public class Client {
    int id_client ;
    String nom_client ; 
    int telephone ; 
    String email ; 

    public Client(int id_client, String nom_client, int telephone, String email) {
        this.id_client = id_client;
        this.nom_client = nom_client;
        this.telephone = telephone;
        this.email = email;
    }

    public Client() {
    }

    public Client(int id_client, String nom_client, String telephone, String email) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id_client;
    }

    public String getNom() {
        return nom_client;
    }

    public int getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public void setNom_client(String nom_client) {
        this.nom_client = nom_client;
    }

    public void setTelephone(int telephone) {
        this.telephone = telephone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}  
