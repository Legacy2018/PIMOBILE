/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.AnnonceCovoiturageService;
import com.mycompany.Entite.Annonce_covoiturage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import java.util.Map;





/**
 *
 * @author medma
 */

public class AjouterAnnonceCovoiturage {
        private Resources theme;
         
    Form f;
    SpanLabel lb;
    TextField AdresseDepart;
    TextField AdresseArrivée;
    Slider Tarif;
    String apiKey="AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w";
//    TextField tnom;
//    TextField tetat;
//    Button btnajout,btnaff;
//
//    public HomeForm() {
//        f = new Form("home");
//        tnom = new TextField();
//        tetat = new TextField();
//        btnajout = new Button("ajouter");
//        btnaff=new Button("Affichage");
//        f.add(tnom);
//        f.add(tetat);
//        f.add(btnajout);
//        f.add(btnaff);
//        btnajout.addActionListener((e) -> {
//            ServiceTask ser = new ServiceTask();
//            Task t = new Task(0, tnom.getText(), tetat.getText());
//            //ser.ajoutTask(t);
//            
//
//        });
//        btnaff.addActionListener((e)->{
//        Affichage a=new Affichage();
//        a.getF().show();
//        });
//    }

    public AjouterAnnonceCovoiturage(){
        theme = UIManager.initFirstTheme("/theme2");
      
        Form hi = new Form("AutoComplete", new BoxLayout(BoxLayout.Y_AXIS));
if(apiKey == null) {
    hi.add(new SpanLabel("This demo requires a valid google API key to be set in the constant apiKey, "
            + "you can get this key for the webservice (not the native key) by following the instructions here: "
            + "https://developers.google.com/places/web-service/get-api-key"));
    hi.getToolbar().addCommandToRightBar("Get Key", null, e -> Display.getInstance().execute("https://developers.google.com/places/web-service/get-api-key"));
    hi.show();
    return;
}
 final DefaultListModel<String> options = new DefaultListModel<>();
 AutoCompleteTextField from = new AutoCompleteTextField(options) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }
 };
 from.setMinimumElementsShownInPopup(5);
 from.setHint("Adresse de départ");
  AutoCompleteTextField to = new AutoCompleteTextField(options) {
     @Override
     protected boolean filter(String text) {
         if(text.length() == 0) {
             return false;
         }
         String[] l = searchLocations(text);
         if(l == null || l.length == 0) {
             return false;
         }

         options.removeAll();
         for(String s : l) {
             options.addItem(s);
         }
         return true;
     }
 };
   
 from.setMinimumElementsShownInPopup(5);
to.setHint("Adresse d'arrivée");
        AdresseDepart=new TextField();
        AdresseArrivée=new TextField();
        Picker DateDepart = new Picker();
DateDepart.setType(Display.PICKER_TYPE_DATE);
 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
 DateDepart.setText("Veuillez Choisir date Départ");
        Button Add=new Button("Ajouter");
        
        Slider sldr= new Slider();
                sldr.setMaxValue(60);
                sldr.setMinValue(0);
                sldr.setVertical(false);
                sldr.setEditable(true);
                Label Tarif1= new Label("Tarif Personne:");
                Label Tarif= new Label("1");
              Tarif.setText(String.valueOf(sldr.getProgress()));
                sldr.addDataChangedListener(new DataChangedListener() {

                  @Override
                  public void dataChanged(int type, int index) {
                       Tarif.setText(String.valueOf(sldr.getProgress()));//To change body of generated methods, choose Tools | Templates.
                  }
                  }); 
                 Slider sldr1= new Slider();
                sldr1.setMaxValue(8);
                sldr1.setMinValue(1);
                sldr1.setVertical(false);
                sldr1.setEditable(true);
                Label Places1= new Label("Nombre de Places :");
                Label Places= new Label("0");
              Places.setText(String.valueOf(sldr1.getProgress()));
                sldr1.addDataChangedListener(new DataChangedListener() {

                  @Override
                  public void dataChanged(int type, int index) {
                       Places.setText(String.valueOf(sldr1.getProgress()));//To change body of generated methods, choose Tools | Templates.
                  }
                  }); 

         f = new Form("Ajouter Annonce", BoxLayout.y());
                        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        
        tb.addMaterialCommandToSideMenu("Annonces de Covoiturage", FontImage.MATERIAL_LIST, e -> {
            AfficherAnnoncesCovoiturage a=new AfficherAnnoncesCovoiturage();
            a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Favoris", FontImage.MATERIAL_STAR, e -> {
            AnnoncesFavorites a;try {
                a = new AnnoncesFavorites();
                a.getF().show();
            } catch (IOException ex) {
               
            }

        });
        tb.addMaterialCommandToSideMenu("Mes Annonces", FontImage.MATERIAL_VIEW_LIST, e -> {
            //mes Annonces
            MesAnnoncesCovoiturage a=new MesAnnoncesCovoiturage();
            a.getF().show();
        });
        
         tb.addMaterialCommandToSideMenu("AJouter Votre Trajet", FontImage.MATERIAL_PLACE, e -> {
             AjouterAnnonceCovoiturage a=new AjouterAnnonceCovoiturage();
            a.getF().show();
        });
         f.setUIID("AjouterAnnonceForm");
      //  f.add(DateDepart);
         f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            HomeRussie2018 h = new HomeRussie2018();
            h.getF().show();
        });
         f.add(from);
        f.add(to);
        f.add(Tarif1);
        f.add(Tarif);
        f.add(sldr);
        f.add(Places1);
        f.add(Places);
        f.add(sldr1);
        f.add(DateDepart);
        f.add(Add);
      
       
   
      
         Validator v = new Validator();
        v.addConstraint(from, new LengthConstraint(5, "you must choose from th autocomplete"));
        v.addConstraint(to, new LengthConstraint(5, "you must choose from th autocomplete"));
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    
                    Date date1 = formatter.parse(DateDepart.getText());
                    Date datenow=new Date();
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yyyy");
                    //utulisateur connécté
                    System.out.println("drogba"+DateDepart.getText());
                   if(DateDepart.getText().length()>10||DateDepart.getText().length()==0){ToastBar.showMessage("Veuillez Choisir Une Date ", FontImage.MATERIAL_DATE_RANGE, 5000);}
                 else if ((date1.getTime()-datenow.getTime())<0){ToastBar.showMessage("Date Antérieure à La Date Actuelle ", FontImage.MATERIAL_DATE_RANGE, 5000);}
                 else if(from.getText().length()==0||to.getText().length()==0||sldr.getProgress()==0||sldr.getProgress()==0){ToastBar.showMessage("DVeuillez Remplir Tout Les Champs ", FontImage.MATERIAL_DATE_RANGE, 5000);}
                  else{
                    Annonce_covoiturage AnnonceCovoiturage= new Annonce_covoiturage(Login.u.getId(), 1,formatter1.format(date1), null, from.getText(),to.getText(),(Float)Float.parseFloat(Tarif.getText()));
                    System.out.println("bbbb"+AnnonceCovoiturage);
                    AnnonceCovoiturage.setNbrePlaces(sldr.getProgress());
                    
                    AnnonceCovoiturageService CovoiturageService=new AnnonceCovoiturageService();
                    CovoiturageService.addAnnonce(AnnonceCovoiturage);}
                } catch (ParseException ex) {
                    
                }
              
            }
        });
       
        /*/
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://41.226.11.243:10004/tasks/");
        NetworkManager.getInstance().addToQueue(con);
        con.addResponseListener(new ActionListener<NetworkEvent>() {

            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceTask ser = new ServiceTask();
                List<Task> list = ser.getListTask(new String(con.getResponseData()));
                System.out.println("sana");
                System.out.println(list);
                lb.setText(list.toString());
               
                System.out.println(lb.getText());
                f.refreshTheme();
            }
        });
        //*/
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeRussie2018 h=new HomeRussie2018();
          h.getF().show();
          });
            
    }
        public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }  
    String[] searchLocations(String text) {
    try {
        if(text.length() > 0) {
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key", "AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");
            r.addArgument("input", text);
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            String[] res = Result.fromContent(result).getAsStringArray("//description");
            return res;
        }
    } catch(Exception err) {
        Log.e(err);
    }
    return null;
}
}
