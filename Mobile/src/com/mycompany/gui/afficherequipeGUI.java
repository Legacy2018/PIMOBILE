/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.facebook.User;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Fos_User;
import java.io.*;

import java.util.ArrayList;

/**
 *
 * @author Emel
 */
public class afficherequipeGUI {
    
    EncodedImage enc;
    ImageViewer imgv, imgv1;
    Image img, img1;
    Database db;
    private Resources theme;
    EncodedImage encImg;
    Button fav;
    ServiceEquipe se = new ServiceEquipe();
    Fos_User u = new Fos_User(654);
    // setLayout(new BorderLayout());
    Container cn;
    Cursor c;
    Form f;
    SpanLabel lb;
    
    public afficherequipeGUI() throws IOException {
        theme = UIManager.initFirstTheme("/theme");
        
        f = new Form();
        //      lb = new SpanLabel("");

        ServiceEquipe se = new ServiceEquipe();
        // lb.setText(se.getList2().toString());
        ArrayList<Equipe> list = se.getList2();
        Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Button chart = new Button("stat");
        c3.add(chart);
        chart.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent evt) {
                se.createPieChartForm().show();
            }
        });
        db = Database.openOrCreate("dbRussia2018");
       
        Cursor c1 = db.executeQuery("Select * from favoris where usr=" + u.getId() + ";");
        for (Equipe e : list) {
            if (c1.next()) {
                
                    Row r = c1.getRow();
                    Equipe eq = new Equipe();
                    eq.setIdEquipe(Integer.parseInt(r.getString(1)));
                if (e.getIdEquipe() == e.getIdEquipe()) {
                    System.err.println("bb   " + c1.getPosition());
                    
                    db = Database.openOrCreate("dbRussia2018");
                    EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    System.err.println("drapea " + e.getDrapeau());
                    img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
                    imgv1 = new ImageViewer(img1);
                    c3.add(imgv1);
                    Label nom = new Label();
                    nom.setText(e.getPays());
                    c3.add(nom);
                    Storage s = new Storage();
                    
                    s.clearStorage();
                    fav = new Button("favoris");
                    Label liked = new Label(theme.getImage("like.png"));
                    c3.add(liked);
                    //   c3.add(fav);
                    fav.addActionListener(new ActionListener() {
                        
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            se.fav(e, u);

                        }
                    });
                }
            } else {
                EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                System.err.println("drapea " + e.getDrapeau());
                img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
                imgv1 = new ImageViewer(img1);
                c3.add(imgv1);
                Label nom = new Label();
                nom.setText(e.getPays());
                c3.add(nom);
                Storage s = new Storage();
                
                s.clearStorage();
                fav = new Button(theme.getImage("emptylike.png"));
               // fav.set
                c3.add(fav);
                fav.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent evt) {

                        fav.setIcon(theme.getImage("like.png"));
                        se.fav(e, u);
                        //   System.out.println("id eq "+fu.getId());

                    }
                });
                
            }
  
        }

        f.add(c3);
       
    }
    
    public Form getF() {
        return f;
    }
    
    public void setF(Form f) {
        this.f = f;
    }
}
