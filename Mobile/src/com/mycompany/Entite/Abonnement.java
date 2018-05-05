/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author BSS
 */
public class Abonnement {
    private int id;
    private String type;
    private float prix;
    private String categorie;
    private int nbrabonnement;
    private int iduser; 

    public Abonnement() {
    }

    public Abonnement(int iduser) {
        this.iduser = iduser;
    }

  

   

    public Abonnement(String type, float prix, String categorie, int nbrabonnement) {
        this.type = type;
        this.prix = prix;
        this.categorie = categorie;
        this.nbrabonnement = nbrabonnement;
    }

    public Abonnement(int id, String type, float prix, String categorie, int nbrabonnement) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.categorie = categorie;
        this.nbrabonnement = nbrabonnement;
    }

    public Abonnement(int id, String type, float prix, String categorie, int nbrabonnement, int iduser) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.categorie = categorie;
        this.nbrabonnement = nbrabonnement;
        this.iduser = iduser;
    }

    public Abonnement(String type, float prix, String categorie, int nbrabonnement, int iduser) {
        this.type = type;
        this.prix = prix;
        this.categorie = categorie;
        this.nbrabonnement = nbrabonnement;
        this.iduser = iduser;
    }

  
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getNbrabonnement() {
        return nbrabonnement;
    }

    public void setNbrabonnement(int nbrabonnement) {
        this.nbrabonnement = nbrabonnement;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    @Override
    public String toString() {
        return "Abonnement{" + "id=" + id + ", type=" + type + ", prix=" + prix + ", categorie=" + categorie + ", nbrabonnement=" + nbrabonnement + ", iduser=" + iduser + '}';
    }

   
    
  
}
