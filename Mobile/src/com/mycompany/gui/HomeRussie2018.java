/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Gui.HomeForm;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompany.gui.afficherequipeGUI;
import com.mycompany.gui.HomeFormTicket;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;


/**
 *
 * @author admin
 */
public class HomeRussie2018 {
      Form f;
        private Resources theme1;
                private Resources theme;

        
    public HomeRussie2018(){
          f = new Form("home");
           f.setUIID("AbonnementsForm");
             theme = UIManager.initFirstTheme("/theme");

         Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        
        tb.addMaterialCommandToSideMenu("Covoiturage", FontImage.MATERIAL_LIST, e -> {
           
            
            AfficherAnnoncesCovoiturage a=new AfficherAnnoncesCovoiturage();
           
            a.getF().show();
            
        });
        
        
        tb.addMaterialCommandToSideMenu("Equipe", FontImage.MATERIAL_WEB, e -> {
              try {
                  afficherequipeGUI a=new afficherequipeGUI();
                  a.getF().show();
              } catch (IOException ex) {
                
              }
             
            
        });
        tb.addMaterialCommandToSideMenu("Tickets/Abonnements", FontImage.MATERIAL_SETTINGS, e -> {
            
            HomeFormTicket a=new HomeFormTicket();
            a.getF().show();
            
        });
        tb.addMaterialCommandToSideMenu("Matchs", FontImage.MATERIAL_INFO, e -> {
            HomeForm h = new HomeForm();
            h.getF().show();
            
        });
        tb.addMaterialCommandToSideMenu("Utilisateur", FontImage.MATERIAL_HEADSET, e -> {
            new listeUser(null).f.show();
        });
        
    }
       public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
