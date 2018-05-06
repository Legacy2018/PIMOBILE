package com.mycompany.Entite;

import com.mycompany.Entite.Fos_User;
import java.util.Date;
import com.mycompany.Entite.match;
//import java.util.Objects;
import com.mycompany.Entite.match;
import java.util.Date;


public class Ticket {

    private int idTicket;
    private int nbrTicket;
    private String categories;
    private Float prix;
    private Fos_User idUser;
    private int idMatch;
    private Date heurAjout;
    private float moyenne;

    public Date getHeurAjout() {
        return heurAjout;
    }

    public Ticket(int idTicket, int nbrTicket) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
    }

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
    }
    

    public float getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(float moyenne) {
        this.moyenne = moyenne;
    }
    

    public void setHeurAjout(Date heurAjout) {
        this.heurAjout = heurAjout;
    }

    public Ticket(int idTicket) {
        this.idTicket = idTicket;
    }

 

 

   

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, Fos_User idUser, int idMatch, Date heurAjout) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idUser = idUser;
        this.idMatch = idMatch;
        this.heurAjout = heurAjout;
    }

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, Fos_User idUser, int idMatch) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idUser = idUser;
        this.idMatch = idMatch;
    }

    public Ticket() {
    }


    public Ticket(String categories, Float prix) {
        this.categories = categories;
        this.prix = prix;
    }

    public Ticket(int idTicket, int nbrTicket, Float prix, int idMatch) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.prix = prix;
        this.idMatch = idMatch;
    }
    

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public int getNbrTicket() {
        return nbrTicket;
    }

    public void setNbrTicket(int nbrTicket) {
        this.nbrTicket = nbrTicket;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public Float getPrix() {
        return prix;
    }

    public void setPrix(Float prix) {
        this.prix = prix;
    }

    public Fos_User getIdUser() {
        return idUser;
    }

    public void setIdUser(Fos_User idUser) {
        this.idUser = idUser;
    }

    public Ticket(int nbrTicket, String categories, Float prix, int idMatch, float moyenne) {
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idMatch = idMatch;
        this.moyenne = moyenne;
    }

   




  
    public int getIdMatch() {
        return idMatch;
    }

    public void setIdMatch(int idMatch) {
        this.idMatch = idMatch;
    }

    @Override
    public String toString() {
        return "Ticket{" + "idTicket=" + idTicket + ", nbrTicket=" + nbrTicket + ", categories=" + categories + ", prix=" + prix + ", idUser=" + idUser + ", idMatch=" + idMatch + ", heurAjout=" + heurAjout + ", moyenne=" + moyenne + '}';
    }

  
   
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.idTicket;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ticket other = (Ticket) obj;
        if (this.idTicket != other.idTicket) {
            return false;
        }
        return true;
    }
      /* public String AffIdMatch() {
        return idMatch.getDateMatch()+"";
    }*/

    public Ticket(int nbrTicket, String categories, Float prix) {
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
    }

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, int idMatch, float moyenne) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idMatch = idMatch;
        this.moyenne = moyenne;
    }

    public Ticket(int idTicket, int nbrTicket, String categories, Float prix, int idMatch) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.categories = categories;
        this.prix = prix;
        this.idMatch = idMatch;
    }

    public Ticket(int idTicket, int nbrTicket, float moyenne) {
        this.idTicket = idTicket;
        this.nbrTicket = nbrTicket;
        this.moyenne = moyenne;
    }

    

   

}
