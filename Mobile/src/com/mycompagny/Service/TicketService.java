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
import com.mycompany.Entite.Ticket;
import com.mycompany.Entite.Utilisateur;
import com.mycompany.Entite.match;
import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

/**
 *
 * @author BSS
 */
public class TicketService {

    public ArrayList<Ticket> getListTicket(String json) {

        ArrayList<Ticket> listTicket = new ArrayList<>();

        try {
            //System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> tickets = j.parseJSON(new CharArrayReader(json.toCharArray()));
            Map<String, Object> match = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println("aaaaaaaa"+tickets);

            List<Map<String, Object>> list = (List<Map<String, Object>>) tickets.get("root");

            for (Map<String, Object> obj : list) {
                /* Ticket e = new Ticket();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idTicket").toString());
                
              //  Date datedajout=java.sql.Date(obj.get("").toString());
                //System.out.println(id);
                e.setIdTicket((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setCategories(obj.get("categories").toString());
                  float prix= Float.parseFloat(obj.get("prix").toString());
                e.setPrix(prix);
                float nbr = Float.parseFloat(obj.get("nbticket").toString());
                e.setNbrTicket((int) nbr);*/

                // e.setIdMatch(new match(idmatch));
                // annonces.add(new Annonce(Integer.parseInt(ann.get("id_annonce").toString()),formatter.parse(ann.get("trip_date").toString()),formatter.parse(ann.get("annonce_date").toString()),ann.get("lieu_depart").toString(),ann.get("lieu_arrive").toString(),Integer.parseInt(ann.get("nbr_personne").toString()),Float.parseFloat(ann.get("prix").toString()),ann.get("critere").toString(),ann.get("distance").toString(),new User(Integer.parseInt(ann.get("id_user").toString())),ann.get("photo_profil").toString()));
                //  float  idmatchs= Float.parseFloat(obj.get("idmatch").toString());
                //String ss=match.get("nomEquipe1").toString();
                // e.setIdMatch((int)idmatchs);
                //System.out.println(e);
                int debut = obj.get("idmatch").toString().indexOf("=");
                int fin = obj.get("idmatch").toString().indexOf(",");
                String tmp = obj.get("idmatch").toString().substring(debut + 1, fin );
                int debut1 = obj.get("idAnnonceur").toString().indexOf("=");
                int fin1 = obj.get("idAnnonceur").toString().indexOf(",");
                String tmp1 = obj.get("idAnnonceur").toString().substring(debut1 + 1, fin1 );
                match tmpMatch = new match((int) Float.parseFloat(tmp));
                Utilisateur uti=new Utilisateur((int) Float.parseFloat(tmp1));
            /*  int debut1 = obj.get("idmatch").toString().indexOf("=", 42);
                int fin1 = obj.get("idmatch").toString().indexOf(",", 42);
                String tmp1 = obj.get("idmatch").toString().substring(debut1 + 1, (fin1) );
                int debut2 = obj.get("idmatch").toString().indexOf("=", 66);
                int fin2 = obj.get("idmatch").toString().indexOf(",", 66);
                String tmp2 = obj.get("idmatch").toString().substring(debut2 + 1, (fin2) );
                tmpMatch.setEquipe1(tmp1);
                tmpMatch.setEquipe2(tmp2);*/
                                float nbr = Float.parseFloat(obj.get("nbticket").toString());
    float id = Float.parseFloat(obj.get("idTicket").toString());
                  // match mt=new match(obj.get("idmatch").toString());
                // obj.get("date").toString();
                listTicket.add(new Ticket((int)id,(int) nbr,obj.get("categories").toString(), Float.parseFloat(obj.get("prix").toString()),uti.getId(),tmpMatch.getIdMatch() ,Float.parseFloat(obj.get("moyenne").toString())));

            }

        } catch (IOException ex) {
        }
       // System.out.println("-_______________________________-");
       // System.out.println(listTicket);
        return listTicket;

    }
    ArrayList<Ticket> listTasks = new ArrayList<>();

    public ArrayList<Ticket> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8082/piWeb1/web/app_dev.php/api/afficher");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketService ser = new TicketService();
                listTasks = ser.getListTicket(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTasks;
    }

    public ArrayList<match> getListmatch(String json) {

        ArrayList<match> listmatch = new ArrayList<>();

        try {
           // System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> matchs = j.parseJSON(new CharArrayReader(json.toCharArray()));
           // System.out.println(matchs);

            List<Map<String, Object>> list = (List<Map<String, Object>>) matchs.get("root");

            for (Map<String, Object> obj : list) {
                /* Ticket e = new Ticket();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("idTicket").toString());
                
              //  Date datedajout=java.sql.Date(obj.get("").toString());
                //System.out.println(id);
                e.setIdTicket((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setCategories(obj.get("categories").toString());
                  float prix= Float.parseFloat(obj.get("prix").toString());
                e.setPrix(prix);
                float nbr = Float.parseFloat(obj.get("nbticket").toString());
                e.setNbrTicket((int) nbr);*/

                // e.setIdMatch(new match(idmatch));
                // annonces.add(new Annonce(Integer.parseInt(ann.get("id_annonce").toString()),formatter.parse(ann.get("trip_date").toString()),formatter.parse(ann.get("annonce_date").toString()),ann.get("lieu_depart").toString(),ann.get("lieu_arrive").toString(),Integer.parseInt(ann.get("nbr_personne").toString()),Float.parseFloat(ann.get("prix").toString()),ann.get("critere").toString(),ann.get("distance").toString(),new User(Integer.parseInt(ann.get("id_user").toString())),ann.get("photo_profil").toString()));
                //  float  idmatchs= Float.parseFloat(obj.get("idmatch").toString());
                //String ss=match.get("nomEquipe1").toString();
                // e.setIdMatch((int)idmatchs);
                //System.out.println(e);
                // float nbr = Float.parseFloat(obj.get("nbticket").toString());
                // System.out.println("foufou"+(obj.get("idmatch").toString()));
              //  System.out.println("bibi" + obj.get("idMatch").toString());
              //  System.out.println("mimou" + obj);
               
      float id = Float.parseFloat(obj.get("idMatch").toString());

                listmatch.add(new match((int)id, obj.get("nomEquipe1").toString(),obj.get("nomEquipe2").toString()));
            }

        } catch (IOException ex) {
        }
      //  System.out.println(listmatch);
        return listmatch;

    }
    ArrayList<match> listTaskss = new ArrayList<>();

    public ArrayList<match> getList22() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost:8082/piWeb1/web/app_dev.php/api/afficher1");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                TicketService ser = new TicketService();
                listTaskss = ser.getListmatch(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTaskss;
    }
    
    
    
    
    
      public void ajoutTask(Ticket ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/ticket/new" + ta.getPrix() + "/" + ta.getCategories();
        con.setUrl(Url);

       // System.out.println("tt");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        //    System.out.println(str);
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

 public void reserverticket(Ticket ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/reserverTicket?idTicket="+ta.getIdTicket()+"&nbticket="+ta.getNbrTicket();
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

 public void moyenneticket(Ticket ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost:8082/piWeb1/web/app_dev.php/api/moyenneTicket?idTicket="+ta.getIdTicket()+"&moyenne="+ta.getMoyenne();
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

}
