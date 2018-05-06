/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceJoueur;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Joueur;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Emel
 */
public class affichjoueur {

    Form f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    Container cgx, cdx;
    Container chd, cbd;
    Container m;
    ServiceJoueur sj;
    Resources theme;
    public affichjoueur(Equipe e) {
                theme = UIManager.initFirstTheme("/theme");

        sj= new ServiceJoueur();
        ArrayList<Joueur> list = sj.getList2(e.getIdEquipe());       
        System.out.println("liste des jouers    : "+list);
         f = new Form();
         for (Joueur jr : list)
         {
        Container m = new Container(new BoxLayout(BoxLayout.Y_AXIS));
         EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    System.err.println("drapea " + jr.getImg());
                    URLImage img1 = URLImage.createToStorage(encImg, "CacheJ" + jr.getIdJoueur(), "http://localhost/PiWeb1/joueur/" + jr.getImg());
                    ImageViewer imgv1 = new ImageViewer(img1);
                    // c3.add(imgv1);
        cgx = new Container(new BoxLayout(BoxLayout.X_AXIS));
        cgx.add(imgv1);
        cdx = new Container(new BoxLayout(BoxLayout.X_AXIS));

        chd = new Container(new BoxLayout(BoxLayout.Y_AXIS));
             Label nom = new Label(jr.getNomJoueur());
             chd.add(nom);
        cbd = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        
             Label position = new Label(jr.getPosition());
             cbd.add(position);
        cdx.add(chd);
        cdx.add(cbd);
                 
        
        m.add(cgx);
        m.add(cdx);
        
        f.add(m);

         }

        
    }

}
