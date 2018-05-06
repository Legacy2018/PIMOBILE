/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.AbonnementService;
import com.mycompany.Entite.Abonnement;
import java.util.ArrayList;

/**
 *
 * @author BSS
 */
public class ajouterAbonnement {
     Form f, f1;
    private Resources theme;
    ImageViewer imgv;
    Image image;
    EncodedImage enc;
    AbonnementService serviceAbonnement = new AbonnementService();
    ArrayList<Abonnement> abonnement = serviceAbonnement.getList2();
    TextField prix;
    TextField nbrabonnement;
    Button btnajout, btnaff;

    ComboBox b1 = new ComboBox();
    ComboBox b2 = new ComboBox();

    public ajouterAbonnement(){
       
     theme = UIManager.initFirstTheme("/theme");
       
        f = new Form("Abonnement");
                f1 = new Form();
f.setUIID("AbonnementsForm");
f1.setUIID("AbonnementsForm");

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

        prix = new TextField();
        prix.setHint("prix");

        nbrabonnement = new TextField();
          
        nbrabonnement.setHint("nbrabonnement");
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");

     

        f.add(prix);
        f.add(nbrabonnement);
      
           // f.add(categorie);
        b1.addItem("virage");
        b1.addItem("peluse");
        b1.addItem("gradin");
     

        b2.addItem("phase eliminatoire");
        b2.addItem("phase de poule");
        f.add(b2);
 f.add(b1);
   f.add(btnajout);
        f.add(btnaff);
        
        btnajout.addActionListener((e) -> {
            if ( VerifFloat(prix.getText())&&VerifInt(nbrabonnement.getText())){
              
                        
           
            AbonnementService ser = new AbonnementService();
            float nbr = Float.parseFloat(nbrabonnement.getText().toString());

            Abonnement t = new Abonnement(b2.getSelectedItem().toString(), Float.parseFloat(prix.getText()), b1.getSelectedItem().toString(), (int) nbr, 4);

            ser.ajoutTask(t);
            
          AfficherAbonnement a=new AfficherAbonnement();
        a.getF1().show();
            }else {ToastBar.showMessage("le nombre de ticket ou prix incorrect", FontImage.MATERIAL_WARNING, 5000);
                    }
            
            });
         btnaff.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
           
            
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF1().show();

        });
}
    public boolean VerifFloat(String Aux){
        try {Float f=Float.parseFloat(Aux);
        return true;}
        catch(Exception e){return false;}
        
    }
        public boolean VerifInt(String Aux){
        try {int f=Integer.parseInt(Aux);
        return true;}
        catch(Exception e){return false;}
        
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form getF2() {
        return f1;
    }

    public void setF2(Form f2) {
        this.f1 = f2;
    }

  
    
}
