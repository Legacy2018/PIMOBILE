/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompany.Entite.Equipe;
import java.util.ArrayList;

/**
 *
 * @author Emel
 */
public class AjouterEquipe {

     // setLayout(new BorderLayout());
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
    SpanLabel lb;
    Container c;
    public AjouterEquipe() {

        f = new Form();
          f.setUIID("AbonnementsForm");

        c = new Container();
        int etat;
         Label lbeq= new Label("Equipe");
      Label lbSel= new Label("Selecetionneur");
            Label lbpt= new Label("Point");
      Label lbetat= new Label("Etat");
     // Label lbeq= new Label("Equipe");
            Label lbgr= new Label("Groupe");
        TextField pays = new TextField(null, "Pays");
        TextField selec = new TextField(null,"Selectionneur");
        TextField point = new TextField(null, "Nombre de points");;
        ComboBox<String> cb = new ComboBox(
                "A", "B", "C", "D", "E", "F", "G", "H"
        );
        c.add(lbeq);
        c.add(pays);


        OnOffSwitch genre = new OnOffSwitch();
        genre.setOff("IN");
        genre.setOn("OUT");
        c.add(lbetat);
        
        c.add(genre);
        c.add(lbSel);
        c.add(selec);
        c.add(lbpt);
        c.add(point);
        c.add(lbgr);
        c.add(cb);
       /* if (genre.isValue()) {
            etat = 1;
        } else { 
            etat = 0;
        }*/
        Button aj = new Button("Ajouter");
        Button An= new Button("Annuler");
   c.add(aj);;
   c.add(An);
   
      
        aj.addActionListener((e) -> {
            if (verifInt(point.getText())){
            ServiceEquipe ser = new ServiceEquipe();
            String gnr="";

           if (genre.isValue())
           { gnr="OUT";
         //  eq.setEtat(0);
           }
           else {gnr="IN";
        //   eq.setEtat(1);
           }
                                   Equipe eq = new Equipe(pays.getText(), 1, "phase", cb.getSelectedItem(), selec.getText(), Integer.parseInt(point.getText()),"tn.png");

            ser.ajouterEquipe(eq);
            HomeAdminForm ho =new HomeAdminForm();
            ho.getF().show();
            }else Dialog.show("Entrée Invalide", "Veuillez saisir un nombre de points", null, "ok");
        });
        
        An.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                HomeAdminForm h = new HomeAdminForm();
                h.getF().show();
            }
        });
       f.add(c);

    }
    
   public boolean verifInt(String aux)
   { try {Integer.parseInt(aux);
   return true;
   }catch(Exception e){ return false;}
       
       }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
