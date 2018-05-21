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
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
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
public class touslesjOUEURS {


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
    TextField eq, nom;
    Button search;
    List list0;

    public touslesjOUEURS() {
        eq = new TextField();
        nom = new TextField();

        Form f2 = new Form();
        Container cn = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme");

        sj = new ServiceJoueur();
        list0 = new ArrayList<Joueur>();
        list0 = sj.getList2All();

        System.out.println("liste des jouers    : " + list0);
        f = new Form();
          f.setUIID("AbonnementsForm");

        TextField eq = new TextField();
        search = new Button("serch");

        /*  for (Joueur jr : list0) {
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

         }*/
        Label no= new Label("nom");
        Label position= new Label("Position");
        f.add(no);
            
        f.add(eq);
        f.add(position);
        f.add(nom);
        f.add(search);
        search.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String poss = eq.getText().toString();
                String noms = nom.getText().toString();
                List<Joueur> list = sj.getList2All();
                List<Joueur> listf = new ArrayList<>();
                /*   System.out.println("etat  ==" + etatF);
                 System.out.println("phase  ==" + phaseF);
                 System.out.println("groupe  ==" + groupeF);
                 System.out.println("liste initiale " + list);*/
                boolean chnged = false;

                if (!poss.equals("")) {
                    chnged = true;
                    for (Joueur j : list) {
                        if (poss.equals(j.getPosition())) {

                            listf.add(j);

                        }
                    }

                    list.clear();
                    list.addAll(listf);

                    listf.clear();
                } else if (!noms.equals("")) {
                    for (Joueur j : list) {
                        if (noms.equals(j.getNomJoueur())) {

                            listf.add(j);

                        }
                    }

                    list.clear();
                    list.addAll(listf);

                    listf.clear();
                } else {
                    list = sj.getList2All();
                }

                /*if (!groupeF.equals("")) {
                 chnged = true;
                    
                 list.stream().filter((e) -> (e.getGroupe().equals(groupeF))).map((e) -> {
                        
                 return e;
                 }).forEachOrdered((e) -> {
                 listf.add(e);
                 });
                 list.clear();
                 for (Equipe e : listf) {
                 list.add(e);
                 }
                    
                 listf.clear();
                 }*/
                if (chnged == true) {

                    //  System.out.println("change true list.size() element" + list);
                    //  System.out.println("list.size()" + list.size());
                    chnged = true;
                    List<Joueur> lstf = list;
                    list0.clear();
                    list0.addAll(lstf);
                    System.out.println("listffff.size() element" + list0);
                    System.out.println("listf.size()" + lstf.size());
                    rechJoueur r = new rechJoueur(list0);
                  //  r.getF().show();

                }
                if (chnged == false || list.size() == 0) {
                    list.clear();
                    list = sj.getList2All();
                    System.out.println("lsie    :" + list);
                      rechJoueur r = new rechJoueur(list);
//                    r.getF().show();

                }
            }
        });

    }

}
