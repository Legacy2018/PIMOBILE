/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import java.util.Collections;

/**
 *
 * @author Emel
 */
public class Joueur implements Comparable<Joueur>{

    int idJoueur;
    String nomJoueur;
    String nationalite;
    int nbrBut;
    String position;
    int cartr;
    int cartj;
    String img;

    public Joueur(String nomJoueur, String nationalite, int nbrBut, String position, int cartr, int cartj, String img, Equipe idEquipe) {
        this.nomJoueur = nomJoueur;
        this.nationalite = nationalite;
        this.nbrBut = nbrBut;
        this.position = position;
        this.cartr = cartr;
        this.cartj = cartj;
        this.img = img;
        this.idEquipe = idEquipe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(int idJoueur) {
        this.idJoueur = idJoueur;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public int getNbrBut() {
        return nbrBut;
    }

    public void setNbrBut(int nbrBut) {
        this.nbrBut = nbrBut;
    }

    public int getCartr() {
        return cartr;
    }

    public void setCartr(int cartr) {
        this.cartr = cartr;
    }

    public int getCartj() {
        return cartj;
    }

    public void setCartj(int cartj) {
        this.cartj = cartj;
    }

    public Equipe getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Equipe idEquipe) {
        this.idEquipe = idEquipe;
    }
   
    Equipe idEquipe;
     
    public Joueur(String nom_joueur, String nationalite, int nbr_but, String position, Equipe id_equipe) {
        this.nomJoueur = nom_joueur;
        this.nationalite = nationalite;
        this.nbrBut = nbr_but;
        this.position = position;
        this.idEquipe = id_equipe;
          // this.pays = pays;
    }

  /*  public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }
    
    */
    public Joueur(int id_joueur, String nom_joueur, String nationalite, int nbr_but, String position, Equipe id_equipe ) {
        this.idJoueur = id_joueur;
        this.nomJoueur = nom_joueur;
        this.nationalite = nationalite;
        this.nbrBut = nbr_but;
        this.position = position;
        this.idEquipe = id_equipe;
//         this.pays = pays;
    }
    

    public int getId_joueur() {
        return idJoueur;
    }

    public void setId_joueur(int id_joueur) {
        this.idJoueur = id_joueur;
    }

   
    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public int getNbr_but() {
        return nbrBut;
    }

    public void setNbr_but(int nbr_but) {
        this.nbrBut = nbr_but;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Equipe getId_equipe() {
        return idEquipe;
    }

    public void setId_equipe(Equipe id_equipe) {
        this.idEquipe = id_equipe;
    }

    @Override
    public String toString() {
        return "Joueur{" + "idJoueur=" + idJoueur + ", nomJoueur=" + nomJoueur + 
                ", nationalite=" + nationalite + ", nbrBut=" + nbrBut +
                ", position=" + position + ", idEquipe=" + idEquipe + '}';
    }

   

   
    public Joueur() {
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    
 
  
    @Override  
    public boolean equals(Object obj) {
    if(obj instanceof Joueur && obj != null){
        
        Joueur j = (Joueur) obj;
        if(j.getId_joueur() == this.idJoueur)
            return true;
    }    
    return false;
    }
    
    
    @Override
    public int compareTo(Joueur o) {
        return this.nbrBut - o.nbrBut;
    }
    
    
}