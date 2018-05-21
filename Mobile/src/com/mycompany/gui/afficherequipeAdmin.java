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
import com.codename1.io.Storage;
import com.codename1.io.Util;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompagny.Service.ServiceJoueur;
import com.mycompany.Entite.Equipe;
import com.mycompany.Entite.Fos_User;
import java.io.*;

import java.util.ArrayList;
import java.util.Collection;

/**
 *
 * @author Emel
 */
public class afficherequipeAdmin {

    EncodedImage enc;
    ImageViewer imgv, imgv1;
    Image img, img1;
    Database db;
    private Resources theme;
    EncodedImage encImg;
    Button fav, nofav;
    ServiceEquipe se = new ServiceEquipe();
    //  Fos_User u = new Fos_User(954);
    int id;
    // setLayout(new BorderLayout());
    Container cn;
    Cursor c;
    Container c3;
    Container m;
    Container m1;
    Container m2;
    Form f;
    SpanLabel lb;
    ServiceJoueur sj = new ServiceJoueur();
    Login l;

    public afficherequipeAdmin() throws IOException {
        // l = new Login();
        id = l.u.getId();
        System.out.println("id  :" + id);
        //  se.getChar();
        sj.getList2(63);
        theme = UIManager.initFirstTheme("/theme");

        f = new Form(BoxLayout.y());
  f.setUIID("AbonnementsForm");

        ServiceEquipe se = new ServiceEquipe();
        ArrayList<Equipe> list = se.getList2();
      
        cn = new Container();
        Button chart = new Button("stat");
        // cn.add(chart);
      //  f.add(cn);
        chart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                //    se.createPieChartForm().show();
            }
        });


        /*   System.out.println("like");
         System.out.println(EqL.size());
         System.out.println("sans like");
         System.out.println(list3.size());
         System.out.println("all like");
         System.out.println(list.size());*/
     //   Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));

       

            for (Equipe e : list) {
                c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            
                    //    m=new Container(new Layout);
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Container c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    System.out.println("eq sans like = " + e);
                    db = Database.openOrCreate("dbRussia2018");
                    EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("music.png"), false);
                    System.err.println("drapea " + e.getDrapeau());
                    img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
                    imgv1 = new ImageViewer(img1);
                    // c3.add(imgv1);
                    Label dr = new Label(img1);
                    Label nom = new Label();
                    Label lb = new Label("hahah");
                    nom.setText(e.getPays());
                    c1.add(dr);
                    c1.add(c2);
                    c2.add(nom);
                    c3.add(c1);

                    //  f.add(c1);
                    // c.setLeadComponent(nom);
                    Storage s = new Storage();

                    s.clearStorage();
                  //  fav = new Button(theme.getImage("emptylike.png"));

                  nofav = new Button("Details");

              
               c5.add(nofav);

                    nofav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            detailAdmin dt = new detailAdmin(e);
                            dt.getF().show();
                        }
                    });
                    c3.add(c5);
                    
                    f.add(c3);


            
        
     
        }

        /*    f.getToolbar().addCommandToOverflowMenu("Afficher tous les joueurs", null, (ev)->{touslesjOUEURS t=new touslesjOUEURS();
         t.getF().show();
         });
         f.getToolbar().addCommandToOverflowMenu("Mes Coups de coeur", null, (ev)->{MesFavorisEquipe h=new MesFavorisEquipe();
         h.getF().show();
         });*/
             Toolbar tb = f.getToolbar();
                       tb.addCommandToLeftBar("Retour",null , new ActionListener() {

                       @Override
                       public void actionPerformed(ActionEvent evt) {
                           
                           HomeAdminForm af;
                           af = new HomeAdminForm();
                           af.getF().show();
                         
                       }
                   });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
