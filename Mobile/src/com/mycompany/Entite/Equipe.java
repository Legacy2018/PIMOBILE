/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;


/**
 *
 * @author admin
 */
public class Equipe {
    
    public Integer idEquipe ;
    public String pays;
   int etat; //0 pour eliminée 1 pour encore dans la competition
    String phase; //1/8 ou 1/4 ou 1/2 ou 3éme place ou finale
    String groupe; //A ou B ou C ou D ou E ou F ou G ou H 
    String selecteur;
    int point ;
    String drapeau;

    public String getDrapeau() {
        return drapeau;
    }

    public void setDrapeau(String drapeau) {
        this.drapeau = drapeau;
    }
    
    
   public String getSelecteur() {
        return selecteur;
    }

    public void setSelecteur(String selecteur) {
        this.selecteur = selecteur;
    }

    public Equipe(String pays, String groupe, String selecteur) {
        this.pays = pays;
        this.etat = 1;
        this.phase = "";
        this.groupe = "groupe";
        this.selecteur = selecteur;

    }

    public Equipe(int id ,int point) {
        
        this.idEquipe= id ; 
        this.point = point;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public Equipe(String pays, int etat, String phase, String groupe, String selecteur, int point, String drapeau) {
        this.pays = pays;
        this.etat = etat;
        this.phase = phase;
        this.groupe = groupe;
        this.selecteur = selecteur;
        this.point = point;
        this.drapeau = drapeau;
    }
    

    public Equipe(String pays, int etat, String phase, String groupe,  String selecteur) {
        this.pays = pays;
        this.etat = etat;
        this.phase = phase;
        this.groupe = groupe;
        this.selecteur = selecteur;
    }

    public Integer getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(Integer id_equipe) {
        this.idEquipe = id_equipe;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    @Override
    public String toString() {
        return "Equipe{" + "idEquipe=" + idEquipe + ", pays=" + pays + ", etat=" + etat + ", phase=" + phase + ", groupe=" + groupe + ", selecteur=" + selecteur + ", point=" + point + ", drapeau=" + drapeau + '}';
    }

    
   
    public Equipe() {
    }

    public Equipe(String pays) {
        this.pays = pays;
    }

   
    
 /*@Override
    public int compareTo(Equipe o) {
   return this.idEquipe -o.idEquipe;
    }*/

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
      
        if (obj instanceof Equipe && obj != null) {
            Equipe e = (Equipe) obj;
            if (e.getIdEquipe() == this.getIdEquipe() )
            return true;
        }
       
        return false;
    }
    
}
