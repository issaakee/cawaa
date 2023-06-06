package model;

import java.sql.Connection;
import java.util.Date;

public class Facture {
    private int n_facture;
    private Date date_facture;
    private String mode_paiement;

    public Facture(int n_facture, Date date_facture, String mode_paiement) {
        this.n_facture = n_facture;
        this.date_facture = date_facture;
        this.mode_paiement = mode_paiement;
    }

    public int getNum() {
        return n_facture;
    }

    public Date getDate() {
        return date_facture;
    }

    public String getModePaiement() {
        return mode_paiement;
    }

    public void setNum(int n_facture) {
        this.n_facture = n_facture;
    }

    public void setDate(Date date_facture) {
        this.date_facture = date_facture;
    }

    public void setModePaiement(String mode_paiement) {
        this.mode_paiement = mode_paiement;
    }
}
    
    

   
    
    


