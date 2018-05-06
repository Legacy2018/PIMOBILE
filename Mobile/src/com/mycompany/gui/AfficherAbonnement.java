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
import com.codename1.ui.TextField;
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
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BSS
 */
public class AfficherAbonnement {

    Form f,f1,f2;
    private Resources theme;
    ImageViewer imgv;
    Image image;
    EncodedImage enc;
    AbonnementService serviceAbonnement = new AbonnementService();

    public static        Abonnement t;
    Button  btnaff,reserver,supprimer,modifier;



    public AfficherAbonnement() {
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
        
        tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
        });
        tb.addMaterialCommandToSideMenu("Website", FontImage.MATERIAL_WEB, e -> {
        });
        tb.addMaterialCommandToSideMenu("Settings", FontImage.MATERIAL_SETTINGS, e -> {
        });
        tb.addMaterialCommandToSideMenu("About", FontImage.MATERIAL_INFO, e -> {
        });
       
     
              ArrayList<Abonnement> abonnement = serviceAbonnement.getList2();
              Style s = UIManager.getInstance().getComponentStyle("Title");
         FontImage info = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s);
         FontImage del = FontImage.createMaterial(FontImage.MATERIAL_DELETE, s);
         FontImage res = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);
         FontImage ed = FontImage.createMaterial(FontImage.MATERIAL_UPDATE, s);

                for (Abonnement ab : abonnement) {
                    
                    Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container C5 = new Container(new BoxLayout(BoxLayout.X_AXIS));
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

                    SpanLabel l = new SpanLabel(ab.getCategorie());
                    Label l1 = new Label(ab.getPrix() + "$");
                    SpanLabel l2 = new SpanLabel(String.valueOf(ab.getNbrabonnement()) + " Dispo");
                    //  Label l3 =new Label(String.valueOf(t.getIdMatch().getIdMatch()));
                    SpanLabel l4 = new SpanLabel(ab.getType());
                    
                    //  SpanLabel l5 = new SpanLabel(String.valueOf(t.getIdMatch().getEquipe2()));

                    //  System.out.println("samira"+String.valueOf(t.getIdMatch().getIdMatch()));
                    //Button bt1 = new Button("retour");
                  //  Button bt = new Button("deatils");
   TextField nt=new TextField();
                            
                    //   ConnectionRequest req = new ConnectionRequest();
                    //  req.setUrl("http://localhost:8082/piWeb1/web/app_dev.php/api/ticket/find?idTicket=" + t.getIdTicket());
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

                            Button bton = new Button("reserver",res);
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
                            
                         
                             bton.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
            float nbr = ab.getNbrabonnement();
float resu = nbr-Integer.parseInt(nt.getText());

            Abonnement t = new Abonnement(ab.getId(),ab.getType(), ab.getPrix() ,ab.getCategorie(),(int) resu);

            ser.reserverab(t);
                   
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF1().show();
             

        });
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


    
          t   = new Abonnement(ab.getId(),ab.getType(), ab.getPrix() ,ab.getCategorie(),ab.getNbrabonnement());

          //  ser.ModifierAb(t);
                                           System.out.println(t);
          ModifierAbonnement b=new ModifierAbonnement();
        b.getF().show();
        
             

        });
                
                             
                             
                             
                             

                            C3.add(l4);
                            // C3.add(l5);
                           
                            C3.add(l);
                            C3.add(l1);
                              C3.add(nt);
                            C3.add(bton);
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
                  

                    //    C2.add(l3);
                    //  C2.add(l5);

                    /*
         System.out.println("mm"+t.getIdMatch().getDateMatch());
         
         System.out.println("mm"+t.getIdMatch().getIdMatch());
         System.out.println("mm");
         System.out.println("mm");
                     */
                    //Label l3=new Label(t.getNbrTicket().)
                    //   Storage s = new Storage();
                    //   s.clearStorage();
                    C3.add(l);
                    C3.add(l1);
                    C3.add(l2);
                    C2.add(l4);
                    // C2.add(lb);
                    // C1.add(img);
                  //  C3.add(reserver);
                    C4.add(C2);
                    C4.add(C3);
                    C1.add(C5);
                    C1.add(C4);
                    C0.add(C1);
                    C0.add(Details);

  
                    f1.add(C0);

                   f1.refreshTheme();
         
    
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
  f1.show();

           
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form getF1() {
        return f1;
    }

    public void setF1(Form f1) {
        this.f1 = f1;
    }

    public static Abonnement getT() {
        return t;
    }

    public static void setT(Abonnement t) {
        AfficherAbonnement.t = t;
    }


    
}
