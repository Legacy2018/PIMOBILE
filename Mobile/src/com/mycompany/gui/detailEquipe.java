/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompany.Entite.Equipe;

/**
 *
 * @author Emel
 */
public class detailEquipe {

    Form f;
    Container c, c1,c2, c3,c4,c5, m;
 private Resources theme;
    public detailEquipe(Equipe e) {
        f = new Form();
                theme = UIManager.initFirstTheme("/theme");

        EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    System.err.println("drapeau " + e.getDrapeau());
        URLImage img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
        ImageViewer imgv1 = new ImageViewer(img1);
        Container i = new Container(new LayeredLayout());
        i.add(imgv1);
        f.add(i);
        c = new Container(new BoxLayout((BoxLayout.X_AXIS)));

        m = new Container(new BoxLayout((BoxLayout.Y_AXIS)));

        Label p = new Label("Equipe");
        Label p2 = new Label(e.getPays());
        c.add(p);
        c.add(p2);
        m.add(c);
        
        c1 = new Container(new BoxLayout((BoxLayout.X_AXIS)));
        Label g = new Label("Groupe");
        Label g2 = new Label(e.getGroupe());
        c1.add(g);
        c1.add(g2);
        m.add(c1);
        
        c2 = new Container(new BoxLayout((BoxLayout.X_AXIS)));
        Label ph = new Label("Phase");
        Label ph2 = new Label(e.getPhase());
        c2.add(ph);
        c2.add(ph2);
        m.add(c2);
        
         c3 = new Container(new BoxLayout((BoxLayout.X_AXIS)));
        Label s = new Label("GSelectionneur");
        Label s2 = new Label(e.getSelecteur());
        c3.add(s);
        c3.add(s2);
        m.add(c3);
        
         c4 = new Container(new BoxLayout((BoxLayout.X_AXIS)));
        Label pt = new Label("Point");
        Label pt2 = new Label(String.valueOf(e.getPoint()));
        c4.add(pt);
        c4.add(pt2);
        m.add(c4);
        
        Button jou= new Button("Liste des Joueurs");
        Button stat= new Button("Statistiques");
        stat.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceEquipe ser = new ServiceEquipe();
                ser.createPieChartForm(e).show();;
                        
            }
            
        });
        jou.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                affichjoueur af= new affichjoueur(e);
                af.getF().show();
                
            }
        });
        
         c5 = new Container(new BoxLayout((BoxLayout.Y_AXIS)));
     
        c5.add(jou);
        c5.add(stat);
        m.add(c5);
        
        f.add(m);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
