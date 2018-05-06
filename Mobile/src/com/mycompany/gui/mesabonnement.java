/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.AbonnementService;
import com.mycompany.Entite.Abonnement;
import static com.mycompany.gui.AfficherAbonnement.t;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BSS
 */
public class mesabonnement {
    Form f,f1,f2;
    private Resources theme;
    ImageViewer imgv;
    Image image;
    EncodedImage enc;
    AbonnementService serviceAbonnement = new AbonnementService();

    public static        Abonnement t;
    Button  btnaff,supprimer,modifier;
     static public Login y;

   int x=y.u.getId();
     public mesabonnement(){
                    theme = UIManager.initFirstTheme("/theme");
        f = new Form();
                f.setUIID("AbonnementsForm");

      f1 = new Form();
              f1.setUIID("AbonnementsForm");

              f2 = new Form();

          f2.setUIID("AbonnementsForm");

                      Toolbar tb = f1.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        
        tb.addMaterialCommandToSideMenu("Tickets", FontImage.MATERIAL_SHOPPING_CART, e -> {
              AffichageTicket a=new AffichageTicket();
        a.getF().show();
            
        });
        tb.addMaterialCommandToSideMenu("Abonnements", FontImage.MATERIAL_SHOPPING_CART, e -> {
               ajouterAbonnement a=new ajouterAbonnement();
        a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Mes Tickets", FontImage.MATERIAL_TOC, e -> {
              mesTicket a=new mesTicket();
        });
        tb.addMaterialCommandToSideMenu("Mes Abonnements", FontImage.MATERIAL_TOC, e -> {
               mesabonnement a=new mesabonnement();
        });    
          Style s1 = UIManager.getInstance().getComponentStyle("Title");
         FontImage chariiot = FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, s1);
          FontImage aff = FontImage.createMaterial(FontImage.MATERIAL_TOC, s1);
       
     
              ArrayList<Abonnement> abonnement = serviceAbonnement.getList2();
              Style s = UIManager.getInstance().getComponentStyle("Title");
         FontImage info = FontImage.createMaterial(FontImage.MATERIAL_INFO, s);
         FontImage del = FontImage.createMaterial(FontImage.MATERIAL_DELETE, s);
         FontImage res = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
         FontImage ed = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, s);

                for (Abonnement ab : abonnement) {
                 if(ab.getIduser()==x){  
                    FontImage prix = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, s);
       FontImage cat = FontImage.createMaterial(FontImage.MATERIAL_EVENT_SEAT, s) ;  
                    Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                     Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                       Button Details=new Button("Voir Abonnement",info);
                    EncodedImage encImg
                            = EncodedImage.createFromImage(theme.getImage("music.png"), false);
                    if (ab.getCategorie().equals("virage")) {

                        URLImage imgUrl
                                = URLImage.createToStorage(encImg, "cache0", "http://localhost:8082/seance7mobil/medailbronze.png");
                        imgUrl.fetch();
                        ImageViewer img = new ImageViewer(imgUrl);
                        C5.add(img);
                    }
                    if (ab.getCategorie().equals("peluse")) {
                        URLImage imgUrl
                                = URLImage.createToStorage(encImg, "cache33", "http://localhost:8082/seance7mobil/gold.png");
                        imgUrl.fetch();
                        ImageViewer img = new ImageViewer(imgUrl);
                        C5.add(img);
                    }
                    if (ab.getCategorie().equals("gradin")) {
                        URLImage imgUrl
                                = URLImage.createToStorage(encImg, "cac0", "http://localhost:8082/seance7mobil/silvrmedail.png");
                        imgUrl.fetch();
                        ImageViewer img = new ImageViewer(imgUrl);
                        C5.add(img);
                    }

                    Label l = new Label(ab.getCategorie(),cat);
                    Label l1 = new Label("Prix : "+ab.getPrix()+"$");
                    SpanLabel l2 = new SpanLabel(String.valueOf(ab.getNbrabonnement()) + " Dispo");
                    //  Label l3 =new Label(String.valueOf(t.getIdMatch().getIdMatch()));
                    SpanLabel l4 = new SpanLabel(ab.getType());
                    
                      Details.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {

                            Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                            //  ImageViewer img = new ImageViewer(theme.getImage("round.png"));
                            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                            Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                            Label l4 = new Label(ab.getType());
                            //  Label l5 =new Label(String.valueOf(t.getIdMatch().getEquipe2()));
                            SpanLabel l = new SpanLabel(ab.getCategorie());
                            SpanLabel l1 = new SpanLabel("Parix:" + ab.getPrix() + "$");
                            SpanLabel l2 = new SpanLabel(String.valueOf(ab.getNbrabonnement()));

                           // Button bton = new Button("reserver",res);
                 supprimer = new Button("supprimer",del);
                                  modifier = new Button("Modifier",ed);


                            try {
                                enc = EncodedImage.create("/giphy.gif");
                            } catch (IOException ex) {

                            }

                            if (ab.getCategorie().equals("virage")) {

                                image = URLImage.createToStorage(enc, "yyyy", "http://localhost:8082/seance7mobil/bronzeepng.png", URLImage.RESIZE_SCALE);
                                imgv = new ImageViewer(image);
                                C2.add(image);

                            }
                            if (ab.getCategorie().equals("peluse")) {

                                image = URLImage.createToStorage(enc, "mmm", "http://localhost:8082/seance7mobil/Sans titre.png", URLImage.RESIZE_SCALE);
                                imgv = new ImageViewer(image);
                                C2.add(image);

                            }
                            if (ab.getCategorie().equals("gradin")) {
                                image = URLImage.createToStorage(enc, "ooo", "http://localhost:8082/seance7mobil/silveer1.png", URLImage.RESIZE_SCALE);
                                imgv = new ImageViewer(image);
                                C2.add(image);

                            }
                            
                         
                             supprimer.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
            float nbr = ab.getNbrabonnement();


            Abonnement t = new Abonnement(ab.getId(),ab.getType(), ab.getPrix() ,ab.getCategorie(),ab.getNbrabonnement());

            ser.supprimerAb(t);
                   
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF1().show();
             

        });
                                       modifier.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
            float nbr = ab.getNbrabonnement();


    
          t   = new Abonnement(ab.getId(),ab.getType(), ab.getPrix() ,ab.getCategorie(),ab.getNbrabonnement(),ab.getIduser());

          //  ser.ModifierAb(t);
                                           System.out.println(t);
          ModifierAbonnement b=new ModifierAbonnement();
        b.getF().show();
        
             

        });
                
                             
                                       

                            C3.add(l4);
                            // C3.add(l5);
                           
                            C3.add(l);
                            C3.add(l1);
                            
                          //  C3.add(bton);
                            C3.add(supprimer);
                            C3.add(modifier);
                            C1.add(C2);
                           

                            f2.add(C1);

                            f2.add(C3);
                           Toolbar tb = f2.getToolbar();
                            tb.addCommandToLeftBar("back", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                  C1.removeAll();
                                    C2.removeAll();
                                    C3.removeAll();
                                    //C4.removeAll();
                                   // C5.removeAll();
                                    f1.show();
                                
                       
                                }
                            });

                            f2.show();

                            
                        }
                        
                    });
                    
         C3.add(l);
                    C6.add(l1);
                    C3.add(l2);
                    C2.add(l4);
                    // C2.add(lb);
                    // C1.add(img);
                  //  C3.add(reserver);
                    C4.add(C2);
                    C4.add(C3);
                    C4.add(C6);
                    C1.add(C5);
                    C1.add(C4);
                    C0.add(C1);
                    C0.add(Details);

  
                    f.add(C0);

                  // f1.refreshTheme();
                 }
    
                }
/*  f1.show();
               Toolbar tb = f.getToolbar();
                            tb.addCommandToLeftBar("back", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    f.showBack();
                                }
                            });*/
              //  f2.show();
                //  f2.add(C1);
                //  f2.refreshTheme();
                // lb.setText(serviceTask.getList2().get(9).getIdMatch().getDateMatch().toString());
  f.show();

           
    }

     
     
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
      public static Abonnement getT() {
        return t;
    }

    public static void setT(Abonnement t) {
        AfficherAbonnement.t = t;
    }

}
