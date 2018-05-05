/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.AbonnementService;
import com.mycompany.Entite.Abonnement;
import java.util.ArrayList;
/**
 *
 * @author BSS
 */
public class ModifierAbonnement {
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

    public ModifierAbonnement(){
     theme = UIManager.initFirstTheme("/theme");
       
        f = new Form("Abonnement");
                f1 = new Form();
    AfficherAbonnement o=new AfficherAbonnement();
   
    
        prix = new TextField();
        prix.setText(String.valueOf(o.getT().getPrix()));

        nbrabonnement = new TextField();
          
        nbrabonnement.setText(String.valueOf(o.getT().getNbrabonnement()));
        btnajout = new Button("Modifier");
        btnaff = new Button("Affichage");

        // f.add(categorie);
        b1.addItem("virage");
        b1.addItem("peluse");
        b1.addItem("gradin");
        f.add(b1);

        b2.addItem("phase eliminatoire");
        b2.addItem("phase de poule");
        f.add(b2);

        f.add(prix);
        f.add(nbrabonnement);
        f.add(btnajout);
        f.add(btnaff);

        btnajout.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
            float nbr = Float.parseFloat(nbrabonnement.getText().toString());
                     float id = Float.parseFloat(String.valueOf(o.getT().getId()));
            System.out.println("aaaaaaaa"+id);
            Abonnement t = new Abonnement((int)id,b2.getSelectedItem().toString(), Float.parseFloat(prix.getText()), b1.getSelectedItem().toString(), (int) nbr);

            ser.ModifierAb(t);
            System.out.println(t);
    AfficherAbonnement b=new AfficherAbonnement();
      b.getF1().show();

        });
         btnaff.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
           
            
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF1().show();

        });
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
