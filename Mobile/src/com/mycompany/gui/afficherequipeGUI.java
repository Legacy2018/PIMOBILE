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
    private Resources theme;
    EncodedImage encImg;
    Button fav ;
    ServiceEquipe se = new ServiceEquipe();
            
    // setLayout(new BorderLayout());
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
    SpanLabel lb;

    public afficherequipeGUI() {
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
      
        for (Equipe e : list) {
           
            EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);     
            System.err.println("drapea " + e.getDrapeau());
            img1 = URLImage.createToStorage(encImg,"Cache"+ e.getPays(), "http://localhost/PiWeb1/TeamFlags/" + e.getDrapeau());
            imgv1 = new ImageViewer(img1);
            c3.add(imgv1);
            Label nom = new Label();
            nom.setText(e.getPays());
            c3.add(nom);
              Storage s = new Storage();
             
             s.clearStorage();
            fav = new Button("favoris");
            c3.add(fav);
            fav.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
                    Fos_User fu = new Fos_User(654);
                    //se.fav(e, fu);
                     Database db ;
                                      //   System.out.println("id eq "+fu.getId());

        try {
            db= Database.openOrCreate("Russia");
            

                    db.execute("insert into  favoris  (id ,usr , eq ) values ("+fu.getId()+","+fu.getId()+","+e.getIdEquipe()+");");
                    System.out.println("ok fav");
                     Cursor c= db.executeQuery("Select * from favoris ;");
                    while ( c.next())
                    {
                        Row r = c.getRow();
                                                String a =r.getString(0);

                        String n =r.getString(1);
                        String pre = r.getString(2);
                      
                                            System.out.println("id user :"+n +"ideq :"+pre +"id :"+a);

                      
                    
            }
            
        } catch (IOException ex) {
         
        }
                }
            });
            
        }
      /*  Button imaged = new Button("choisir drapeau");
        imaged.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openImageGallery(new ActionListener() {
                    public void actionPerformed(ActionEvent ev) {
                        if (ev != null && ev.getSource() != null) {
                            //       Storage s = new Storage();
//

                            String filePath = (String) ev.getSource();
                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
                            String fileName = filePath.substring(fileNameIndex);
                            System.out.println("image     : " + filePath);
                            EncodedImage encImg = EncodedImage.createFromImage(theme.getImage("round.png"), false);
                            //catch (IOException ex) {
                            //}
                            //imgv1 = new ImageViewer(img1);
                            // c3.add(imgv1);
                        }
                    }
                });
            }
        });
        c3.add(imaged);*/

        f.add(c3);

        //  f.add(c1);
        /*/
         ConnectionRequest con = new ConnectionRequest();
         con.setUrl("http://41.226.11.243:10004/tasks/");
         NetworkManager.getInstance().addToQueue(con);
         con.addResponseListener(new ActionListener<NetworkEvent>() {

         @Override
         public void actionPerformed(NetworkEvent evt) {
         ServiceTask ser = new ServiceTask();
         List<Task> list = ser.getListTask(new String(con.getResponseData()));
         System.out.println("sana");
         System.out.println(list);
         lb.setText(list.toString());
               
         System.out.println(lb.getText());
         f.refreshTheme();
         }
         });
         //*/
        /* f.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
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
