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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author sana
 */
public class HomeForm {
   
    
    Form f,f1,f2;
    TextField categorie;
    TextField prix;
    Button btnajout,btnaff,btnaff1,mesticket,mesabonnement;
  private Resources theme;
    public HomeForm() {
        f = new Form("home");
            f.setUIID("AbonnementsForm");
     theme = UIManager.initFirstTheme("/theme");
                 
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
        btnaff=new Button("Ticket");
         btnaff1=new Button("Abonnement");
          mesticket=new Button("Mes Ticket");
         mesabonnement=new Button("Mes Abonnement");
       f.add(mesticket);
       f.add(mesabonnement);
          f.add(btnaff);

        f.add(btnaff1);
      
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
