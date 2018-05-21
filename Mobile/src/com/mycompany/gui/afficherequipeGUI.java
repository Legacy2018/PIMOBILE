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
public class afficherequipeGUI {

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

    public afficherequipeGUI() throws IOException {
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
        ArrayList<Equipe> list2 = se.getList2();
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

        db = Database.openOrCreate("dbRussia2018");
        Cursor cr = db.executeQuery("Select * from favoris where usr=" + id + ";");
        ArrayList<Integer> EqL = new ArrayList();
        while (cr.next()) {
            Row r = cr.getRow();
            Equipe equipe = new Equipe();
            equipe.setIdEquipe(Integer.parseInt(r.getString(2)));
            // EqL.add(equipe);
            EqL.add(Integer.parseInt(r.getString(2)));
        }
        ArrayList<Integer> list3 = new ArrayList<>();
        for (Equipe e : list2) {
            list3.add(e.getIdEquipe());
        }
        boolean tr = false;

        list3.removeAll(EqL);

        /*   System.out.println("like");
         System.out.println(EqL.size());
         System.out.println("sans like");
         System.out.println(list3.size());
         System.out.println("all like");
         System.out.println(list.size());*/
     //   Container c = new Container(new BoxLayout(BoxLayout.X_AXIS));

        for (int i : list3) {

            for (Equipe e : list) {
                c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                if (e.getIdEquipe() == i) {
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
                    fav = new Button(theme.getImage("emptylike.png"));

                    nofav = new Button("Details");

                    c5.add(fav);
                    c5.add(nofav);

                    fav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Favoris", "A present l'equipe : " + e.getPays()
                                    + " Fait partie de vos coup de coeurs de la coupe du monde", "OK", null);
                            System.out.println("here fav");
                            se.fav(e, Login.u);

                            try {
                                afficherequipeGUI af = new afficherequipeGUI();
                                af.getF().show();
                            } catch (IOException ex) {
                            }
                        }
                    });
                    nofav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            detailEquipe dt = new detailEquipe(e);
                            dt.getF().show();
                        }
                    });
                    c3.add(c5);
                    
                    f.add(c3);

                }

            }
        }
        for (int i : EqL) {

            for (Equipe e : list) {
                c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                if (e.getIdEquipe() == i) {

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
                    //  c1.setLeadComponent(nom);

                    Storage s = new Storage();

                    s.clearStorage();

                    nofav = new Button(theme.getImage("like.png"));

                    fav = new Button("Details");
                    c5.add(nofav);
                    c5.add(fav);

                    nofav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Favoris", "A present l'equipe : " + e.getPays() + " a été retiré de votre liste de favoris !", "OK", null);

                            se.nofav(e, Login.u);
                            System.out.println("here no fav");

                            try {
                                afficherequipeGUI af = new afficherequipeGUI();
                                af.getF().show();
                            } catch (IOException ex) {
                            }
                        }
                    });
                    fav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            detailEquipe dt = new detailEquipe(e);
                            dt.getF().show();
                        }
                    });
              
                    c3.add(c5);
                    f.add(c3);

                }
            }
        }

        /*    f.getToolbar().addCommandToOverflowMenu("Afficher tous les joueurs", null, (ev)->{touslesjOUEURS t=new touslesjOUEURS();
         t.getF().show();
         });
         f.getToolbar().addCommandToOverflowMenu("Mes Coups de coeur", null, (ev)->{MesFavorisEquipe h=new MesFavorisEquipe();
         h.getF().show();
         });*/
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
