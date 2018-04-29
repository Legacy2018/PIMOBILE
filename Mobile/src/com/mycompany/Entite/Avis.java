/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

/**
 *
 * @author Katouchi
 */
public class Avis {
    private int id;
    private int id_user;
    private int  idEquipe;
    private int note;
    private String Avis;

    public Avis() {
    }

    public Avis(int id_user, int idEquipe, int note, String Avis) {
        this.id_user = id_user;
        this.idEquipe = idEquipe;
        this.note = note;
        this.Avis = Avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getIdEquipe() {
        return idEquipe;
    }

    public void setIdEquipe(int idEquipe) {
        this.idEquipe = idEquipe;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public String getAvis() {
        return Avis;
    }

    public void setAvis(String Avis) {
        this.Avis = Avis;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", id_user=" + id_user + ", idEquipe=" + idEquipe + ", note=" + note + ", Avis=" + Avis + '}';
    }

  
}
