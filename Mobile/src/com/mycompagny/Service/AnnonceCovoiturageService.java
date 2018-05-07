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
import com.mycompany.Entite.Annonce_collocation;
import com.mycompany.Entite.Annonce_covoiturage;
import com.mycompany.gui.AfficherAnnoncesCovoiturage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author medma
 */
public class AnnonceCovoiturageService {
    
    
     public void addAnnonce(Annonce_covoiturage Annonce) {
         ConnectionRequest AR=new ConnectionRequest("http://localhost/malek/PiWeb1/web/app_dev.php/api/addannonce");
                AR.setPost(false);
                AR.addArgument("idAnnonceur", Annonce.getId_annonceur()+"");
                AR.addArgument("DateDepart", Annonce.getDate_depart()+"");
                AR.addArgument("AdrDepart", Annonce.getAdresse_depart());
                AR.addArgument("AdrArrivee", Annonce.getAdresse_arrivee());
                AR.addArgument("Tarif", Annonce.getTarif()+"");
                AR.addArgument("date", Annonce.getDate_depart()+"");
                AR.addArgument("nbr", Annonce.getNbrePlaces()+"");
                System.out.println("mimi"+Annonce.getDate_depart());
                NetworkManager.getInstance().addToQueue(AR);
                AR.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            String msg=new String(AR.getResponseData(),"utf-8");
                            System.out.println(msg);
                        } catch (UnsupportedEncodingException ex) {
                         
                        }
                    }
                });
            }
      public void ReserverAnnonce(int places, int IdAnnonce) {
          System.out.println("Place"+places);
         ConnectionRequest AR=new ConnectionRequest("http://localhost/malek/PiWeb1/web/app_dev.php/api/updateplaces");
                AR.setPost(false);
                AR.addArgument("idAnnonce", IdAnnonce+"");
                AR.addArgument("nbrplaces", places+"");
               
                
                NetworkManager.getInstance().addToQueue(AR);
                AR.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            String msg=new String(AR.getResponseData(),"utf-8");
                            System.out.println(msg);
                            AfficherAnnoncesCovoiturage afficherAnnonces=new AfficherAnnoncesCovoiturage();
                            afficherAnnonces.getF().show();
                        } catch (UnsupportedEncodingException ex) {
                         
                        }
                    }
                });
            }
      public void EditAnnonce(Annonce_covoiturage Annonce) {
         ConnectionRequest AR=new ConnectionRequest("http://localhost/malek/PiWeb1/web/app_dev.php/api/editannonce");
                AR.setPost(false);
                AR.addArgument("idAnnonce", Annonce.getId_annonce()+"");
                AR.addArgument("idAnnonceur", Annonce.getId_annonceur()+"");
                AR.addArgument("DateDepart", Annonce.getDate_depart()+"");
                AR.addArgument("AdrDepart", Annonce.getAdresse_depart());
                AR.addArgument("AdrArrivee", Annonce.getAdresse_arrivee());
                AR.addArgument("Tarif", Annonce.getTarif()+"");
                 AR.addArgument("date", Annonce.getDate_depart()+"");
                
                NetworkManager.getInstance().addToQueue(AR);
                AR.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            String msg=new String(AR.getResponseData(),"utf-8");
                            System.out.println(msg);
                             AfficherAnnoncesCovoiturage afficherAnnonces=new AfficherAnnoncesCovoiturage();
                            afficherAnnonces.getF().show();
                        } catch (UnsupportedEncodingException ex) {
                         
                        }
                    }
                });
            }
      public void removeAnnonce(Annonce_covoiturage Annonce) {
          System.out.println("zertfyghjklm");
          System.out.println("babou"+AfficherAnnoncesCovoiturage.AnnonceCovoiturage);
         ConnectionRequest AR=new ConnectionRequest("http://localhost/malek/PiWeb1/web/app_dev.php/api/deleteannonce");
                AR.setPost(false);
                AR.addArgument("idAnnonce", Annonce.getId_annonce()+"");
               System.out.println("AR"+AR.getUrl());
                       
                
                NetworkManager.getInstance().addToQueue(AR);
                AR.addResponseListener(new ActionListener<NetworkEvent>() {

                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                        try {
                            String msg=new String(AR.getResponseData(),"utf-8");
                            System.out.println(msg);
                            AfficherAnnoncesCovoiturage afficherAnnonces=new AfficherAnnoncesCovoiturage();
                            afficherAnnonces.getF().show();
                        } catch (UnsupportedEncodingException ex) {
                         
                        }
                    }
                });
            }
    
    
    public ArrayList<Annonce_covoiturage> getListAnnonces(String json) throws ParseException {

        ArrayList<Annonce_covoiturage> listAnnonces = new ArrayList<>();

        try {
         
            JSONParser j = new JSONParser();

            Map<String, Object> Annonces = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Annonces);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Annonces.get("root");

            for (Map<String, Object> obj : list) {
                Annonce_covoiturage e = new Annonce_covoiturage();
                   System.out.println("____________________________________________________");
                      System.out.println(e);
// private int id_annonceur;
//    private int id_annonce;
//    private Date date_depart;
//    private Date Date_arrivee;
//    private String adresse_depart;
//    private String adresse_arrivee;
//    private float tarif;
                // System.out.println(obj.get("id"));
                      
                      
               float tmp1=Float.parseFloat(obj.get("idAnnonce").toString());
               float tmp2=Float.parseFloat(obj.get("idAnnonceur").toString());
               
               
                int idAnnonceur =(int) tmp2;
                int idAnnonce =(int) tmp1;
                 String Adepart = obj.get("adresseDepart").toString();
                String Aarrivee = obj.get("adresseArrivee").toString();
                float Tarif = Float.parseFloat(obj.get("tari").toString());
                System.out.println("emel"+obj.get("nbrplaces").toString());
                float NbrPlaces=Float.parseFloat(obj.get("nbrplaces").toString());
   String dateDepart = obj.get("dateDepart").toString();
                System.out.println("ffff"+obj.get("dateDepart").toString());
             
       SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
             Float date=Float.parseFloat(((Map<String, Object>)obj.get("dateDepart")).get("timestamp").toString());
                Long k=date.longValue();
                Date format=new Date(k*1000);
                String d=formatter.format(format);
                System.out.println("d="+d);
                
                
                e.setDate_depart(d);
                e.setId_annonceur((int)idAnnonceur);
                e.setId_annonce((int)idAnnonce);
             
                e.setAdresse_depart(Adepart);
                e.setAdresse_arrivee(Aarrivee);
                e.setTarif(Tarif);
                
                e.setNbrePlaces((int)NbrPlaces);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                System.out.println("bb"+e);
                System.out.println(e);
                listAnnonces.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println("samir"+listAnnonces);
        return listAnnonces;

    }
    ArrayList<Annonce_covoiturage> listTAnnoncesCovoiturage = new ArrayList<>();
    
    public ArrayList<Annonce_covoiturage> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/malek/PiWeb1/web/app_dev.php/api/showall");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    AnnonceCovoiturageService ser = new AnnonceCovoiturageService();
                    listTAnnoncesCovoiturage = ser.getListAnnonces(new String(con.getResponseData()));
                } catch (ParseException ex) {
                  
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTAnnoncesCovoiturage;
    }
}
