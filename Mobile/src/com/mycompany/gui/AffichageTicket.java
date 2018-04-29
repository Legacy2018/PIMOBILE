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
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompagny.Service.TicketService;
import java.util.ArrayList;
import com.mycompany.Entite.Ticket;
import com.mycompany.Entite.match;



/**
 *
 * @author BSS
 */
public class AffichageTicket {

    Form f,f2;
    SpanLabel lb,lb1;
     private Resources theme;
          ImageViewer imgv;
    Image image;
       EncodedImage enc;
     TicketService serviceTask = new TicketService();
  ArrayList<Ticket> tickets =serviceTask.getList2();
  ArrayList<match> matchs =serviceTask.getList22();
     
     
    public AffichageTicket() {
 
        f = new Form();
        f2=new Form();
        lb = new SpanLabel("");
        lb1 =new SpanLabel("");
        f.add(lb);
        f.add(lb1);
          UIBuilder ui = new UIBuilder();
   //  Form f = new Form("russie2019", BoxLayout.y());
     for (Ticket t :tickets) {
  
        
      
          Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

      //  ImageViewer img = new ImageViewer(theme.getImage("round.png"));

        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Label l = new Label(t.getCategories());
        Label l1 = new Label(t.getPrix().toString());
       
      
          
        //  Label l3 =new Label(String.valueOf(t.getIdMatch().getIdMatch()));
          Label l4 =new Label(String.valueOf(t.getIdMatch().getEquipe1()));
          Label l5 =new Label(String.valueOf(t.getIdMatch().getEquipe2()));
          
            System.out.println("samira"+String.valueOf(t.getIdMatch().getIdMatch()));
   
            
            
            
            


        l1.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

      //  ImageViewer img = new ImageViewer(theme.getImage("round.png"));
        Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                    Label l4 =new Label(String.valueOf(t.getIdMatch().getEquipe1()));
          Label l5 =new Label(String.valueOf(t.getIdMatch().getEquipe2()));
                 Label l = new Label(t.getCategories());
        Label l1 = new Label(t.getPrix().toString());
         EncodedImage encImg
                = EncodedImage.createFromImage(theme.getImage("round.png"), false);
        URLImage imgUrl
                = URLImage.createToStorage(encImg, "cache5", "http://localhost:8082/seance7mobil/1521036388563701528_1000x669.jpeg");
        imgUrl.fetch();
        ImageViewer img = new ImageViewer(imgUrl);

        f.add(img);
                 C2.add(l4);
           C2.add(l5);
           C3.add(l);
           C3.add(l1);
      
                   C1.add(C2);
  f2.add(C3);
      f2.add(C1);
                f2.show();
            }
        });
            
            
       //    C2.add(l3);
           C2.add(l4);
           C2.add(l5);
        
        /*
         System.out.println("mm"+t.getIdMatch().getDateMatch());
         
         System.out.println("mm"+t.getIdMatch().getIdMatch());
         System.out.println("mm");
         System.out.println("mm");
       */
        
        //Label l3=new Label(t.getNbrTicket().)
      
    
       //   Storage s = new Storage();
             //   s.clearStorage();
       
        C3.add(l);
        C3.add(l1);
       // C2.add(lb);
       // C1.add(img);
        C1.add(C2);
        C1.add(C3);
      f.add(C1);
                   
                    f.refreshTheme();
         
     }
     
      // C1.setLeadComponent(l);
        f.show();
      //  f2.add(C1);
      //  f2.refreshTheme();
       // lb.setText(serviceTask.getList2().get(9).getIdMatch().getDateMatch().toString());

        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
