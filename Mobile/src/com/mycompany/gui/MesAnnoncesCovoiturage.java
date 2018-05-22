/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author medma
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.util.Callback;
import com.mycompagny.Service.AnnonceCovoiturageService;
import com.mycompany.Entite.Annonce_covoiturage;
import static com.mycompany.gui.AfficherAnnoncesCovoiturage.AnnonceCovoiturage;
import com.sun.glass.ui.MenuItem;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import java.util.LinkedHashMap;
import java.util.Map;



/**
 *
 * @author medma
 */
public class MesAnnoncesCovoiturage {

    public static String t = "";
    Style s = UIManager.getInstance().getComponentStyle("Title");
    public static Annonce_covoiturage AnnonceCovoiturage = null;
    Form f;
    SpanLabel lb;
    private Resources theme;
    private com.codename1.ui.Container gui_MapContainer = new com.codename1.ui.Container(new com.codename1.ui.layouts.BorderLayout());

    public MesAnnoncesCovoiturage(String terme) {

        TextField searchField = new TextField("", "Toolbar Search");
        searchField.setText(t);
        System.out.println("itheniya");
        if (terme.length() == 0) {
            new MesAnnoncesCovoiturage();
        }
        // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);

        String ch;
        f = new Form();
        f.setUIID("AnnoncesForm");
        lb = new SpanLabel("");

        theme = UIManager.initFirstTheme("/theme2");
        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));

        tb.addMaterialCommandToSideMenu("Annonces de Covoiturage", FontImage.MATERIAL_LIST, e -> {
            AfficherAnnoncesCovoiturage a = new AfficherAnnoncesCovoiturage();
            a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Favoris", FontImage.MATERIAL_STAR, e -> {
            AnnoncesFavorites a;
            try {
                a = new AnnoncesFavorites();
                a.getF().show();
            } catch (IOException ex) {

            }

        });
        tb.addMaterialCommandToSideMenu("Mes Annonces", FontImage.MATERIAL_VIEW_LIST, e -> {
            //mes Annonces
        });

        tb.addMaterialCommandToSideMenu("Ajouter Votre Trajet", FontImage.MATERIAL_PLACE, e -> {
            AjouterAnnonceCovoiturage a = new AjouterAnnonceCovoiturage();
            a.getF().show();
        });
        AnnonceCovoiturageService CovoiturageService = new AnnonceCovoiturageService();
        ArrayList<Annonce_covoiturage> ListesAnnonces = CovoiturageService.getList2();
        Annonce_covoiturage ann = new Annonce_covoiturage();
        System.out.println("malek" + ListesAnnonces);
        f.getToolbar().setTitleComponent(searchField);

        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        FontImage dt = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, s);
        ArrayList<Annonce_covoiturage> Filtred = new ArrayList();
        System.out.println("fff");
        for (Annonce_covoiturage Ann : ListesAnnonces) {
            System.out.println("ann=" + Ann);
            System.out.println("ffff");
            //Utilisateur Connecté
            if (Ann.getAdresse_depart().contains(terme) || Ann.getAdresse_arrivee().contains(terme)||Ann.getId_annonceur()==Login.u.getId()) {
                try {
                    FontImage Enter = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);
                    
                    System.out.println("ann1=" + Ann);
                    System.out.println("fffff");
                    Container C0 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C00 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    
                    ImageViewer img = new ImageViewer(theme.getImage("car.png"));
                    Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Button details = new Button("Voir Détails", Enter);
                    Label AdresseDepart = new Label(Ann.getAdresse_depart());
                    Label AdresseDepart1 = new Label("De :");
                    SpanLabel AdresseArrivee = new SpanLabel(Ann.getAdresse_arrivee());
                    Label AdresseArrivee1 = new Label("à :");
                    
                    Label Prix = new Label(String.valueOf(Ann.getTarif()));
                    Label Prix1 = new Label("Prix En $");
                    Label AnnonceId = new Label(String.valueOf(Ann.getId_annonce()));
                    AnnonceId.setVisible(false);
                    Label AnnonceurId = new Label(String.valueOf(Ann.getId_annonceur()));
                    Label PlacesDispos = new Label(String.valueOf(Ann.getNbrePlaces()));
                    Label PlacesDispos1 = new Label("Places Dispos");
                    Label datedep = new Label(Ann.getDate_depart(), dt);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    // AnnonceId.setVisible(false);
                    details.addPointerPressedListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            
                            try {
                                Date date1 = formatter.parse(datedep.getText());
                                Date datenow = new Date();
                                
                                {
                                    
                                    try {
                                        System.out.println("faouziiii");
                                        AnnonceCovoiturage = new Annonce_covoiturage(Integer.parseInt(AnnonceurId.getText()), Integer.parseInt(AnnonceId.getText()), datedep.getText(), AdresseDepart.getText(), AdresseArrivee.getText(), Float.parseFloat(Prix.getText()));
                                        AnnonceCovoiturage.setNbrePlaces(Integer.parseInt(PlacesDispos.getText()));
                                        AfficherAnnonce AffAnnonce = new AfficherAnnonce();
                                        System.out.println(AnnonceCovoiturage + "amine");
                                        
                                        System.out.println("Issoum" + Prix.getText());
                                        try {
                                            AffAnnonce.getF().show();
                                        } catch (IllegalArgumentException e) {
                                            e.getMessage();
                                        }
                                        
                                    } catch (Exception ex) {
                                    }
                                }
                            } catch (ParseException ex) {
                                
                            }
                        }
                    });
                    C6.add(AdresseDepart1);
                    C6.add(AdresseDepart);
                    
                    C5.add(AdresseArrivee1);
                    C5.add(AdresseArrivee);
                    
                    C2.add(AnnonceId);
                    C2.add(datedep);
                    C2.add(C3);
                    C2.add(C5);
                    C2.add(C6);
                    C4.add(Prix1);
                    C4.add(Prix);
                    C7.add(PlacesDispos1);
                    C7.add(PlacesDispos);
                    C2.add(C4);
                    C2.add(C7);
                    C1.add(img);
                    C1.add(C2);
                    C0.add(C1);
                 
                    Date date1 = formatter.parse(datedep.getText());
                    Date datenow=new Date();
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yy");
                      if ((date1.getTime()-datenow.getTime())<0) {
                       FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_TIMER_OFF, s);
                       Label lb=new Label("Expirée",prx);
                       C0.add(lb);
                      }
                       C0.add(details);
                        f.add(C0);
                    
                    f.show();
                    f.getContentPane().animateLayout(0);
                } catch (ParseException ex) {
                }

            }
        }

        searchField.addDataChangeListener((i1, i2) -> { // <2>

            f.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                searchField.startEditingAsync(); // <4>
            });
            System.out.println("rabaaa");
            t = searchField.getText();
            System.out.println("chfbik" + t);
            if (t.length() == 0) {
                f.removeAll();
                System.out.println("pifff");
                MesAnnoncesCovoiturage a = new MesAnnoncesCovoiturage();
            } else {
                MesAnnoncesCovoiturage a = new  MesAnnoncesCovoiturage(t);
                System.out.println("paff");
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
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            HomeRussie2018 h = new HomeRussie2018();
            h.getF().show();
        });

    }

    public MesAnnoncesCovoiturage() {
        // <1>
        System.out.println("abdlaziz");
        TextField searchField = new TextField("", "Chercher");
        System.out.println("loula");
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);
        searchField.setText(t);
        FontImage dt = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, s);
        String ch;
        f = new Form();
        f.setUIID("AnnoncesForm");
        lb = new SpanLabel("");
        Label l = new Label(" ");
        l.setUIID("Separator");
        theme = UIManager.initFirstTheme("/theme2");
        f.refreshTheme();
        Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));

        tb.addMaterialCommandToSideMenu("Annonces de Covoiturage", FontImage.MATERIAL_LIST, e -> {
            AfficherAnnoncesCovoiturage a = new AfficherAnnoncesCovoiturage();
            a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Favoris", FontImage.MATERIAL_STAR, e -> {
            AnnoncesFavorites a;
            try {
                a = new AnnoncesFavorites();
                a.getF().show();
            } catch (IOException ex) {

            }

        });
        tb.addMaterialCommandToSideMenu("Mes Annonces", FontImage.MATERIAL_VIEW_LIST, e -> {
            //mes Annonces
        });

        tb.addMaterialCommandToSideMenu("Ajouter Votre Trajet", FontImage.MATERIAL_PLACE, e -> {
            AjouterAnnonceCovoiturage a = new AjouterAnnonceCovoiturage();
            a.getF().show();
        });
        f.addComponent(new Label(""));
        f.show();
        AnnonceCovoiturageService CovoiturageService = new AnnonceCovoiturageService();
        ArrayList<Annonce_covoiturage> ListesAnnonces = CovoiturageService.getList2();
        Annonce_covoiturage ann = new Annonce_covoiturage();
        System.out.println("malek" + ListesAnnonces);
        f.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        for (Annonce_covoiturage Ann : ListesAnnonces) {
            //utilisateur Connecté
            if (Ann.getId_annonceur()==Login.u.getId()) {
                
          
                try {
                    Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    FontImage Enter = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);
                    ImageViewer img = new ImageViewer(theme.getImage("car.png"));
                    Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Button details = new Button("Voir Détails", Enter);
                    Label AdresseDepart = new Label(Ann.getAdresse_depart());
                    Label AdresseDepart1 = new Label("De :");
                    SpanLabel AdresseArrivee = new SpanLabel(Ann.getAdresse_arrivee());
                    Label AdresseArrivee1 = new Label("à :");
                    
                    Label Prix = new Label(String.valueOf(Ann.getTarif()));
                    Label Prix1 = new Label("Prix En $");
                    Label AnnonceId = new Label(String.valueOf(Ann.getId_annonce()));
                    AnnonceId.setVisible(false);
                    Label AnnonceurId = new Label(String.valueOf(Ann.getId_annonceur()));
                    Label PlacesDispos = new Label(String.valueOf(Ann.getNbrePlaces()));
                    Label PlacesDispos1 = new Label("Places Dispos");
                    // AnnonceId.setVisible(false);
                    Label datedep = new Label(Ann.getDate_depart(), dt);
                    details.addPointerPressedListener(new ActionListener() {
                        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            try {
                                Date date1 = formatter.parse(datedep.getText());
                                Date datenow = new Date();
                                
                                {
                                    
                                    try {
                                        System.out.println("faouziiii");
                                        AfficherAnnoncesCovoiturage.AnnonceCovoiturage = new Annonce_covoiturage(Integer.parseInt(AnnonceurId.getText()), Integer.parseInt(AnnonceId.getText()), datedep.getText(), AdresseDepart.getText(), AdresseArrivee.getText(), Float.parseFloat(Prix.getText()));
                                        AfficherAnnoncesCovoiturage.AnnonceCovoiturage.setNbrePlaces(Integer.parseInt(PlacesDispos.getText()));
                                        System.out.println("faouziiii"+AnnonceCovoiturage);
                                        System.out.println("faouziiii"+AfficherAnnoncesCovoiturage.AnnonceCovoiturage);
                                        AfficherAnnonce AffAnnonce = new AfficherAnnonce();
                                        System.out.println(AnnonceCovoiturage + "amine");
                                        
                                        System.out.println("Issoum" + Prix.getText());
                                        try {
                                            AffAnnonce.getF().show();
                                        } catch (IllegalArgumentException e) {
                                            e.getMessage();
                                        }
                                        
                                    } catch (Exception ex) {
                                    }
                                }
                            } catch (ParseException ex) {
                                
                            }
                        }
                    });
                    C6.add(AdresseDepart1);
                    C6.add(AdresseDepart);
                    
                    C5.add(AdresseArrivee1);
                    C5.add(AdresseArrivee);
                    
                    C2.add(AnnonceId);
                    C2.add(datedep);
                    C2.add(C3);
                    C2.add(C5);
                    C2.add(C6);
                    C4.add(Prix1);
                    C4.add(Prix);
                    C7.add(PlacesDispos1);
                    C7.add(PlacesDispos);
                    C2.add(C4);
                    C2.add(C7);
                    C1.add(img);
                    C1.add(C2);
                    C0.add(C1);
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
                    Date date1 = formatter.parse(datedep.getText());
                    Date datenow=new Date();
                    SimpleDateFormat formatter1 = new SimpleDateFormat("dd/MM/yy");
                    if ((date1.getTime()-datenow.getTime())<0) {
                        FontImage prx = FontImage.createMaterial(FontImage.MATERIAL_TIMER_OFF, s);
                        Label lb=new Label("Expirée",prx);
                        C0.add(lb);
                    }
                    C0.add(details);
                    
                    f.add(C0);
                    
                    f.show();
                    f.getContentPane().animateLayout(0);
                } catch (ParseException ex) {
                }
            }
        }

        searchField.addDataChangeListener((i1, i2) -> { // <2>

            f.getToolbar().addCommandToRightBar("", searchIcon, (e) -> {
                searchField.startEditingAsync(); // <4>
            });
            t = searchField.getText();
            System.out.println("theltha");
            f.removeAll();
            MesAnnoncesCovoiturage a = new MesAnnoncesCovoiturage(t);

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
        f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            HomeRussie2018 h = new HomeRussie2018();
            h.getF().show();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public static String getRoutesEncoded(String src, String dest) {

        String ret = "";

        try {

            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);

            request.addArgument("key", "AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");

            request.addArgument("origin", src);

            request.addArgument("destination", dest);

            NetworkManager.getInstance().addToQueueAndWait(request);

            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
//            System.out.println("response "+response);
            if (response.get("routes") != null) {

                ArrayList routes = (ArrayList) response.get("routes");

                if (routes.size() > 0) {
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }

                System.out.println("distance " + ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("legs")).get(0)).get("distance")).get("text").toString());

                //  String d = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("distance")).get("text").toString();
            }

        } catch (IOException e) {

            e.printStackTrace();

        }

        return ret;

    }

    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {

        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {

            @Override

            protected void readResponse(InputStream input) throws IOException {

                String ret = "";

                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));

                if (response.get("routes") != null) {

                    ArrayList routes = (ArrayList) response.get("routes");

                    if (routes.size() > 0) {
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                    }

                }

                callback.onSucess(ret);

            }

        };

        request.addArgument("key", "AIzaSyBoVSG3FplX2Ckbl672m4HWjnKK4WS5j2w");

        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());

        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);

    }
}
