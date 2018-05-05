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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.AbonnementService;
import com.mycompany.Entite.Abonnement;
import java.util.ArrayList;

/**
 *
 * @author BSS
 */
public class mesabonnement {
     Form f;
         private Resources theme;
 ImageViewer imgv;
    Image image;
    EncodedImage enc;
    AbonnementService serviceAbonnement = new AbonnementService();
     
    
     public mesabonnement(){
                  theme = UIManager.initFirstTheme("/theme");

         f = new Form("mes Abonnement");
            f.setUIID("AbonnementsForm");

  Toolbar tb = f.getToolbar();
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
//Abonnement aa=new Abonnement();

                for (Abonnement ab : abonnement) {
                 if(ab.getIduser()==4){  
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
                    
                    //  SpanLabel l5 
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
                  //  C0.add(Details);

  
                    f.add(C0);

                  // f1.refreshTheme();
             }

                }
               f.show();   
                
        //   s.clearStorage();
                   
     }
     
     
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
}
