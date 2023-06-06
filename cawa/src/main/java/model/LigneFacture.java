

package model;


public class LigneFacture {
 int ref_article;
 int n_facture;
 int quantité_vendue ; 
 
  public LigneFacture() {
    }
    public LigneFacture(int ref_article, int n_facture, int quantité_vendue) {
        this.ref_article = ref_article;
        this.n_facture = n_facture;
        this.quantité_vendue = quantité_vendue;
    }

  

    public int getref_article() {
        return ref_article;
    }

    public int getNum_facture() {
        return n_facture;
    }

    public int getquantité_vendue() {
        return quantité_vendue;
    }

    public void setref_article(int ref_article) {
        this.ref_article = ref_article;
    }

    public void setNum_facture(int n_facture) {
        this.n_facture = n_facture;
    }

    public void setquantité_vendue(int quantité_vendue) {
        this.quantité_vendue = quantité_vendue;
    }
 
 
    
}

