/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import java.util.ArrayList;
/**
 *
 * @author Emel
 */
public class HomeAdminForm {
    
      EncodedImage enc;
    ImageViewer imgv, imgv1;
    Image img, img1;
    private Resources theme;
    EncodedImage encImg;

    // setLayout(new BorderLayout());
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
    SpanLabel lb;

    public HomeAdminForm() {
         theme = UIManager.initFirstTheme("/theme");

        f = new Form();
           f.setUIID("AbonnementsForm");

        ServiceEquipe se = new ServiceEquipe();
        // lb.setText(se.getList2().toString());
        ArrayList<Equipe> list = se.getList2();
        Container c3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       // Button chart = new Button("stat");
     //   c3.add(chart);
        Button modif ;
        Button supp;
      
        for (Equipe e : list) {
           ServiceEquipe ser = new ServiceEquipe();
            EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("music.png"), false);     
            System.err.println("drapea " + e.getDrapeau());
            img1 = URLImage.createToStorage(encImg,"Cache"+ e.getPays(), "http://localhost/emel/PiWeb1/TeamFlags/" + e.getDrapeau());
            imgv1 = new ImageViewer(img1);

            c3.add(imgv1);

            Label nom = new Label();
            nom.setText(e.getPays());
            supp=new Button("Supprimer");
            modif= new Button("modifier");
            supp.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    ser.DeleteEquipe(e);
                    HomeAdminForm h= new HomeAdminForm();
                    h.getF().show();
                }
            });
             modif.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                                        System.out.println("edit "+e);

                    updateEquipe up = new updateEquipe(e);
                    up.getF().show();
                }
            });
             c3.add(modif);
             c3.add(supp);
             
            c3.add(nom);
         
        }
   
        f.add(c3);

      
          f.getToolbar().addCommandToOverflowMenu("Ajouter Equipe", null, (ev)->{AjouterEquipe h=new AjouterEquipe();
          h.getF().show();
          });
          f.getToolbar().addCommandToOverflowMenu("Gerer Equipe", null, (ev)->{gererEquipe h=new gererEquipe();
          h.getF().show();
          });
    }

    

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
