/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Emel
 */
public class ServiceJoueur {
 public ArrayList<Joueur> getList2(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiWeb1/web/app_dev.php/api/JoueurParEquipe/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceJoueur ser = new ServiceJoueur();
                listJoueur = ser.getList(new String(con.getResponseData()));
                System.out.println("lise        !!" + listJoueur);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJoueur;
    }
    ArrayList<Joueur> listJoueur = new ArrayList<>();

    public ArrayList<Joueur> getList(String json) {

        ArrayList<Joueur> listJ = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> joueur= j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(joueur);

            List<Map<String, Object>> list = (List<Map<String, Object>>) joueur.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println("liste      !!");
                Joueur jo = new Joueur();
                int ind = obj.get("idJoueur").toString().indexOf(".");
                String id = obj.get("idJoueur").toString().substring(0, ind);
                System.out.println(" idJoueur  :" + id);
                jo.setIdJoueur(Integer.valueOf(id));
                jo.setPosition(obj.get("position").toString());
                jo.setImg(obj.get("lien").toString());
// int ind1 = obj.get("idJoueur").toString().indexOf(".");
 //int ind2 = obj.get("idJoueur").toString().indexOf(".");
                jo.setNomJoueur(obj.get("nom").toString());
     //           jo.setCartj(obj.get("phase").toString());
                //       e.setEtat((int) obj.get("etat"));
       //         jo.setCartr(obj.get("phase").toString());
         //       jo.setNbrBut((String) obj.get("liendrapeau"));
                System.out.println(jo);
                listJ.add(jo);

            }

        } catch (IOException ex) {
        }
        System.out.println(listJ);
        return listJ;
    }
    ///
    public ArrayList<Joueur> getList2All() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiWeb1/web/app_dev.php/api/showallj");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {

                ServiceJoueur ser = new ServiceJoueur();
                listJoueurAll = ser.getListAll(new String(con.getResponseData()));
                System.out.println("lise        !!" + listJoueurAll);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listJoueurAll;
    }
    ArrayList<Joueur> listJoueurAll = new ArrayList<>();

    public ArrayList<Joueur> getListAll(String json) {

        ArrayList<Joueur> listJ = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> joueur= j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(joueur);

            List<Map<String, Object>> list = (List<Map<String, Object>>) joueur.get("root");

            for (Map<String, Object> obj : list) {
                System.out.println("liste      !!");
                Joueur jo = new Joueur();
                int ind = obj.get("idJoueur").toString().indexOf(".");
                String id = obj.get("idJoueur").toString().substring(0, ind);
                System.out.println(" idJoueur  :" + id);
                jo.setIdJoueur(Integer.valueOf(id));
                jo.setPosition(obj.get("position").toString());
                jo.setImg(obj.get("lien").toString());
// int ind1 = obj.get("idJoueur").toString().indexOf(".");
 //int ind2 = obj.get("idJoueur").toString().indexOf(".");
                jo.setNomJoueur(obj.get("nom").toString());
     //           jo.setCartj(obj.get("phase").toString());
                //       e.setEtat((int) obj.get("etat"));
       //         jo.setCartr(obj.get("phase").toString());
         //       jo.setNbrBut((String) obj.get("liendrapeau"));
                System.out.println(jo);
                listJ.add(jo);

            }

        } catch (IOException ex) {
        }
        System.out.println(listJ);
        return listJ;
    }

 
}
