/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author sana
 */
public class HomeFormTicket {
   
    
    Form f,f1,f2;
    TextField categorie;
    TextField prix;
    Button btnajout,btnaff,btnaff1,mesticket,mesabonnement;
  private Resources theme;
    public HomeFormTicket() {
        f = new Form("home");
            f.setUIID("AbonnementsForm");
     theme = UIManager.initFirstTheme("/theme");
                  Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                       Style s = UIManager.getInstance().getComponentStyle("Title");
         FontImage chariiot = FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, s);
          FontImage aff = FontImage.createMaterial(FontImage.MATERIAL_TOC, s);
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
        btnaff=new Button("Ticket",chariiot);
         btnaff1=new Button("Abonnement",chariiot);
          mesticket=new Button("Mes Ticket",aff);
         mesabonnement=new Button("Mes Abonnement",aff);
      
          C0.add(btnaff);

        C0.add(btnaff1);
       C0.add(mesticket);
       C0.add(mesabonnement);
        btnaff.addActionListener((e)->{
        AffichageTicket a=new AffichageTicket();
        a.getF().show();
        });
        
         btnaff1.addActionListener((e)->{
             ajouterAbonnement a=new ajouterAbonnement();
        a.getF().show();
        });
         mesticket.addActionListener((e)->{
             
             
             
             mesTicket a=new mesTicket();
        a.getF().show();
        });
         mesabonnement.addActionListener((e)->{
               mesabonnement a=new mesabonnement();
        a.getF().show();
        });
   

     f.add(C0);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getCategorie() {
        return categorie;
    }

    public void setCategorie(TextField categorie) {
        this.categorie = categorie;
    }

    public TextField getPrix() {
        return prix;
    }

    public void setPrix(TextField prix) {
        this.prix = prix;
    }

  

}
