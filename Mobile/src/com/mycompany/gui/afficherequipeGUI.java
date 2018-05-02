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
    Fos_User u = new Fos_User(954);
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
        ArrayList<Equipe> list2 = se.getList2();

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
        ArrayList<Integer> EqL = new ArrayList();
        while (c1.next()) {
            Row r = c1.getRow();
            Equipe equipe = new Equipe();
            equipe.setIdEquipe(Integer.parseInt(r.getString(2)));
            // EqL.add(equipe);
            EqL.add(Integer.parseInt(r.getString(2)));
        }
        ArrayList<Integer> list3 = new ArrayList<>();
        for (Equipe e : list2) {
            list3.add(e.getIdEquipe());
        }
        //      ArrayList<Equipe> EqNL = new ArrayList<>(Collection.subtract(list, EqL));
        //new ArrayList<>(CollectionUtils.subtract(a, b))

        boolean tr = false;
        // EqNL=list.removeAll(EqL);
        //   EqNL=Collection

        list3.removeAll(EqL);

        System.out.println("like");
        System.out.println(EqL.size());
        System.out.println("sans like");
        System.out.println(list3.size());

        System.out.println("all like");
        System.out.println(list.size());
        for (int i : list3) {
            for (Equipe e : list) {
                if (e.getIdEquipe() == i) {
                    System.out.println("eq sans like = " + e);
                    db = Database.openOrCreate("dbRussia2018");
                    EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    System.err.println("drapea " + e.getDrapeau());
                    img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
                    imgv1 = new ImageViewer(img1);
                    //  c3.add(imgv1);
                    Label nom = new Label();
                    Label dr = new Label(img1);
                    //c3.add(dr);
                    nom.setText(e.getPays());
                    c3.add(nom);
                    Storage s = new Storage();

                    s.clearStorage();
                    fav = new Button(theme.getImage("emptylike.png"));
                    // Label liked = new Label(theme.getImage("like.png"));
                    // c3.add(liked);
                    nofav = new Button(theme.getImage("like.png"));

                    c3.add(fav);
                    c3.add(nofav);
                    nofav.setVisible(false);
                    // c3.setVisible(false);
                    fav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Favoris", "A present l'equipe :" + e.getPays() + " Fait partie de vos coup de coeurs de la coupe du monde", "OK", null);

// d.show(top, bottom, left, right),
                            //     d.showPopupDialog(fav).;
                            //    popupBody.
                            //    d.showPopupDialog();
                            se.fav(e, u);
                            // se.fav(null, u);
                            //   fav.setVisible(false);
                            // c3.add(nofav);
                            //  nofav.setVisible(true);

                            try {
                                afficherequipeGUI af = new afficherequipeGUI();
                                af.getF().show();
                            } catch (IOException ex) {
                            }
                        }
                    });
                    /*   nofav.addActionListener(new ActionListener() {

                     @Override
                     public void actionPerformed(ActionEvent evt) {
                     // se.nofav(e, u);
                     nofav.setVisible(false);
                     fav.setVisible(true);
                     }
                     });*/
                }

            }
        }
        for (int i : EqL) {
            for (Equipe e : list) {
                if (e.getIdEquipe() == i) {

                    System.out.println("eq sans like = " + e);
                    db = Database.openOrCreate("dbRussia2018");
                    EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                    System.err.println("drapea " + e.getDrapeau());
                    img1 = URLImage.createToStorage(encImg, "Cache" + e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
                    imgv1 = new ImageViewer(img1);
                    // c3.add(imgv1);
                    Label dr = new Label(img1);
                    c3.add(dr);

                    Label nom = new Label();
                    nom.setText(e.getPays());

                    c3.add(nom);
                    Storage s = new Storage();

                    s.clearStorage();
                    //  fav = new Button(theme.getImage("emptylike.png"));
                    // Label liked = new Label(theme.getImage("like.png"));
                    // c3.add(liked);
                    nofav = new Button(theme.getImage("like.png"));

                    // c3.add(fav);
                    c3.add(nofav);
                    //   nofav.setVisible(false);
                    // c3.setVisible(false);
                  /*  nofav.addActionListener(new ActionListener() {

                     @Override
                     public void actionPerformed(ActionEvent evt) {
                     se.fav(e, u);
                     // se.fav(null, u);
                     fav.setVisible(false);
                     // c3.add(nofav);
                     nofav.setVisible(true);
                     try {
                     afficherequipeGUI af=new  afficherequipeGUI();
                     af.getF().show();
                     } catch (IOException ex) {
                     }
                     }
                     });*/
                    nofav.addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog.show("Favoris", "A present l'equipe : " + e.getPays() + " a été retiré de votre liste de favoris !", "OK", null);

                            se.nofav(e, u);
                            //  nofav.setVisible(false);
                            // fav.setVisible(true);
                            try {
                                afficherequipeGUI af = new afficherequipeGUI();
                                af.getF().show();
                            } catch (IOException ex) {
                            }
                        }
                    });

                }
            }
        }


        /*     if (EqL.size() == 0) {

         for (Equipe e : list) {
         EqNL.add(e);
         }
         } else {

         for (Equipe e : list) {
         int i=0;

         System.out.println("trouve1   " + tr);

         // for (Equipe e2 : EqL)
         //   tr=false;
                
         while(i<EqL.size() || ( tr== true))
         {
         Equipe e2=EqL.get(i);
         // tr =false;
         System.out.println("trouve2   " + tr);
         System.out.print("eq1 =   " + e.getIdEquipe());
         System.out.println("eq2 =   " + e2.getIdEquipe());

         if (e.getIdEquipe() == e2.getIdEquipe()) {

         tr = true;
         System.out.println("trouve3   " + tr);

         }
         // else 
         //    tr = false;
         i++;
         }
         if (tr == false) {
         System.out.println("trouve if false   " + tr);

         EqNL.add(e);
         // tr=true;
         }
         tr= false;
         // tr = true;
         }
         // tr = false;

         }
         /*   for (Equipe e : list) {
         //     while(list.|| tr==true)
         {
         if (!EqL.isEmpty()) {
         for (Equipe e2 : EqL) {
         if (e.getIdEquipe() == e2.getIdEquipe()) {
         tr = true;
         }
         }
         if (tr == false) {
         EqNL.add(e);
         }
         } else {
         EqNL.add(e);
         }

         }
         }*/
        /*    if (c1.next()) {
                
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
         }else
         {  EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
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
         }*/
        /*  else {
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
                
         }*/
        //   }
        f.add(c3);

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
