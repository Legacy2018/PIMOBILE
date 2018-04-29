/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

<<<<<<< HEAD
//import java.util.Objects;
=======
>>>>>>> 6eb80bfd7f41e373b6d187e0868b410a3322f445
import com.mycompany.Entite.Ticket;
import com.mycompany.Entite.Fos_User;
/**
 *
 * @author BSS
 */
public class Commentaire {

     private int idCommentaire;
    private String description;
    private Ticket idticket;
    private Fos_User idUser;

    public Commentaire() {
    }

   



   
    public int getIdCommentaire() {
        return idCommentaire;
    }

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Ticket getIdticket() {
        return idticket;
    }

    public void setIdticket(Ticket idticket) {
        this.idticket = idticket;
    }

    public Fos_User getIdUser() {
        return idUser;
    }

    public void setIdUser(Fos_User idUser) {
        this.idUser = idUser;
    }

    
    
    


    @Override
    public int hashCode() {
        int hash = 5;
        
        return hash;
    }

<<<<<<< HEAD
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
        final Commentaire other = (Commentaire) obj;
        if (this.idCommentaire != other.idCommentaire) {
            return false;
        }
       /* if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.idticket, other.idticket)) {
            return false;
        }
        if (!Objects.equals(this.idUser, other.idUser)) {
            return false;
        }***/
        return true;
    }
=======
>>>>>>> 6eb80bfd7f41e373b6d187e0868b410a3322f445

    public Commentaire(int idCommentaire, String description, Ticket idticket, Fos_User idUser) {
        this.idCommentaire = idCommentaire;
        this.description = description;
        this.idticket = idticket;
        this.idUser = idUser;
    }

    public Commentaire(String description, Ticket idticket, Fos_User idUser) {
        this.description = description;
        this.idticket = idticket;
        this.idUser = idUser;
    }



  
}
