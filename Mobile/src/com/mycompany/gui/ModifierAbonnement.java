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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
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

 
   static public Login y;

   int x=y.u.getId();

 

    ComboBox b1 = new ComboBox();
    ComboBox b2 = new ComboBox();

    public ModifierAbonnement(){
     theme = UIManager.initFirstTheme("/theme");
       
        f = new Form(" Modifier Abonnement");
                f1 = new Form();
                f.setUIID("AbonnementsForm");
f1.setUIID("AbonnementsForm");
                    Toolbar tb = f.getToolbar();
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
         tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeRussie2018 a=new HomeRussie2018();
            a.getF().show();
            
            
        });
          Style s1 = UIManager.getInstance().getComponentStyle("Title");
         FontImage chariiot = FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, s1);
         
  Style s = UIManager.getInstance().getComponentStyle("Title");
         FontImage ajo = FontImage.createMaterial(FontImage.MATERIAL_LIBRARY_ADD, s);
                  FontImage aff = FontImage.createMaterial(FontImage.MATERIAL_TOC, s);

        Container c = new Container(new FlowLayout(Component.CENTER));
                Container c1 = new Container(new FlowLayout(Component.CENTER));
                    Container c3 = new Container(new FlowLayout(Component.CENTER));
                                        Container c2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    mesabonnement o=new mesabonnement();
   
    
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
        c2.add(b1);

        b2.addItem("phase eliminatoire");
        b2.addItem("phase de poule");
        c2.add(b2);

       

        btnajout.addActionListener((e) -> {
              if ( VerifFloat(prix.getText())&&VerifInt(nbrabonnement.getText())){
            AbonnementService ser = new AbonnementService();
            float nbr = Float.parseFloat(nbrabonnement.getText().toString());
                     float id = Float.parseFloat(String.valueOf(o.getT().getId()));
         
            Abonnement t = new Abonnement((int)id,b2.getSelectedItem().toString(), Float.parseFloat(prix.getText()), b1.getSelectedItem().toString(), (int) nbr,x);

            ser.ModifierAb(t);
            System.out.println(t);
             }else {ToastBar.showMessage("le nombre de ticket ou prix incorrect", FontImage.MATERIAL_WARNING, 5000);
                    }
    AfficherAbonnement b=new AfficherAbonnement();
      b.getF1().show();
       

        });
         btnaff.addActionListener((e) -> {
            AbonnementService ser = new AbonnementService();
           
            
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF1().show();

        });
          c2.add(prix);
        c2.add(nbrabonnement);
        c3.add(btnajout);
        c.add(btnaff);
        c2.add(c3);
        c2.add(c);
        f.add(c2);
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

   
    
    
  
    
}
