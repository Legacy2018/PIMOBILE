/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.AnnonceCovoiturageService;
import com.mycompany.Entite.Annonce_covoiturage;
import static com.mycompany.gui.AfficherAnnoncesCovoiturage.AnnonceCovoiturage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author medma
 */
public class AnnoncesFavorites {
    Form f;
    Database db;
    private Resources theme;
    Button btnajout, btnaff;

    public AnnoncesFavorites() throws IOException {
        f = new Form("Favoris", BoxLayout.y());
        System.out.println("mmimimim");
        boolean mybasereturn = Database.exists("Favoris");
        System.out.println("dbexists" + mybasereturn);
        db = Database.openOrCreate("Favoris");
List <String>l =new ArrayList();
        if (mybasereturn == false) {
            db.execute("create table Favoris create table Favoris(idannonce TEXT, utilisateurid TEXT);");
        }
        //changer par id annonceur connecté
        Cursor c = db.executeQuery("select * FROM Favoris where utilisateurid="+1);
        while (c.next()) {
            System.out.println("samiiiirrrr");
            Row r = c.getRow();
            String idannonce = r.getString(0);
            String idannonceur = r.getString(1);
            
           l.add(idannonce);
           
        }

        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
   Style s = UIManager.getInstance().getComponentStyle("Title");
    
    
    
    

    
        TextField searchField = new TextField("", "Toolbar Search"); // <1>
        searchField.getHintLabel().setUIID("Title");
        searchField.setUIID("Title");
        searchField.getAllStyles().setAlignment(Component.LEFT);

        String ch;
        f = new Form();
            f.setUIID("AnnoncesForm");
        FontImage dt = FontImage.createMaterial(FontImage.MATERIAL_DATE_RANGE, s);

        theme = UIManager.initFirstTheme("/theme2");
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
        });
        
         tb.addMaterialCommandToSideMenu("AJouter Votre Trajet", FontImage.MATERIAL_PLACE, e -> {
             AjouterAnnonceCovoiturage a=new AjouterAnnonceCovoiturage();
            a.getF().show();
        });
          f.getToolbar().addCommandToRightBar("Retour", null, (ev) -> {
            HomeRussie2018 h = new HomeRussie2018();
            h.getF().show();
        });
        AnnonceCovoiturageService CovoiturageService = new AnnonceCovoiturageService();
        ArrayList<Annonce_covoiturage> ListesAnnonces = CovoiturageService.getList2();
        Annonce_covoiturage ann = new Annonce_covoiturage();
        System.out.println("malek" + ListesAnnonces);
        f.getToolbar().setTitleComponent(searchField);
        FontImage searchIcon = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
        for (Annonce_covoiturage Ann : ListesAnnonces) {
            for (String i:l){if (String.valueOf(Ann.getId_annonce()).equals(i)){
            
              
                     FontImage Enter = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);

                System.out.println("ann1=" + Ann);
                System.out.println("fffff");
               Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
               Container C00 = new Container(new BoxLayout(BoxLayout.X_AXIS));
               Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C4 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C7 = new Container(new BoxLayout(BoxLayout.X_AXIS));
           
           ImageViewer img = new ImageViewer(theme.getImage("car.png"));
Container C2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
Button details=new Button("Voir Détails", Enter);
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
            Label datedep= new Label(Ann.getDate_depart(),dt);
            // AnnonceId.setVisible(false);
                details.addPointerPressedListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent evt) {
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
                });

               C6.add(AdresseDepart1);
            C6.add(AdresseDepart);
           
            C5.add(AdresseArrivee1);
            C5.add(AdresseArrivee);
            C2.add(datedep);
            C2.add(AnnonceId);
            
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
             C0.add(details);
            f.add(C0);
          
            
            

             
            }
                
            }
      

        }
     
        f.show();
        btnajout.addActionListener((e) -> {
            AjouterAnnonceCovoiturage a = new AjouterAnnonceCovoiturage();
            a.getF().show();

        });
        btnaff.addActionListener((e) -> {
            AfficherAnnoncesCovoiturage a = new AfficherAnnoncesCovoiturage();
            a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
