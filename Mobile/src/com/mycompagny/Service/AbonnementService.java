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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.mycompany.Entite.Abonnement;
import com.mycompany.Entite.Fos_User;
import com.mycompany.Entite.Ticket;

/**
 *
 * @author BSS
 */
public class AbonnementService {
      public void ajoutTask(Abonnement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/abonnement/newAb?type=" +ta.getType()+"&prix="+ta.getPrix()+"&categorie="+ ta.getCategorie()+"&nbrabonnement=" +ta.getNbrabonnement();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          public void reserverab(Abonnement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/reserverAb?id="+ta.getId()+"&nbrabonnement=" +ta.getNbrabonnement();
        con.setUrl(Url);

    

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Abonnement> getListAbonneent(String json) {

        ArrayList<Abonnement> listAbonnement = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Abonnement e = new Abonnement();
                
                
              int debut = obj.get("iduser").toString().indexOf("=");
                int fin = obj.get("iduser").toString().indexOf(",");
                String tmp = obj.get("iduser").toString().substring(debut + 1, fin );
                Abonnement tmpUser = new Abonnement((int) Float.parseFloat(tmp));
             /*   // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setCategorie(obj.get("state").toString());
              //  e.setNbrabonnement(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);*/
              float id = Float.parseFloat(obj.get("id").toString());
               float nbr = Float.parseFloat(obj.get("nbrabonnement").toString());
                          /*   float iduser = Float.parseFloat(obj.get("iduser").toString());
                             Abonnement aa=new Abonnement((int)iduser);
                            */

               Abonnement a = new Abonnement((int)id,obj.get("type").toString(),Float.parseFloat(obj.get("prix").toString()), obj.get("categorie").toString(), (int)nbr, tmpUser.getIduser());
                   listAbonnement.add(a);


            }

        } catch (IOException ex) {
        }
        return listAbonnement;

    }
    ArrayList<Abonnement> listTasks = new ArrayList<>();
    
    public ArrayList<Abonnement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8082/piWeb1/web/app_dev.php/api/afficherAb");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AbonnementService ser = new AbonnementService();
                listTasks = ser.getListAbonneent(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
     public void supprimerAb(Abonnement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/supprimerAb?id="+ta.getId();
        con.setUrl(Url);

    

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  public void ModifierAb(Abonnement ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/modifierAb?id="+ta.getId()+"&type=" +ta.getType()+"&prix="+ta.getPrix()+"&categorie="+ ta.getCategorie()+"&nbrabonnement=" +ta.getNbrabonnement();
        con.setUrl(Url);

    

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
//        
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
  
  /*
    public ArrayList<Fos_User> getListutilisateur(String json) {

        ArrayList<Fos_User> listUti = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Fos_User fo=new Fos_User();

             /*   // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setCategorie(obj.get("state").toString());
              //  e.setNbrabonnement(obj.get("name").toString());
                System.out.println(e);
                listEtudiants.add(e);
              float id = Float.parseFloat(obj.get("id").toString());
               float nbr = Float.parseFloat(obj.get("nbrabonnement").toString());
               Abonnement a = new Abonnement((int)id,obj.get("type").toString(),Float.parseFloat(obj.get("prix").toString()), obj.get("categorie").toString(), (int)nbr);
                   listAbonnement.add(a);


            }

        } catch (IOException ex) {
        }
        return listAbonnement;

    }
    ArrayList<Abonnement> listTasks = new ArrayList<>();
    
    public ArrayList<Abonnement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8082/piWeb1/web/app_dev.php/api/afficherAb");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AbonnementService ser = new AbonnementService();
                listTasks = ser.getListAbonneent(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }
*/
}
