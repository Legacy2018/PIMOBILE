/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.DataChangedListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompagny.Service.TicketService;
import com.mycompany.Entite.match;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author BSS
 */
public class mesTicket {
    private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
       private void initStarRankStyle1(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
    /*   public void showForm() {
  Form hi = new Form("Star Slider", new BoxLayout(BoxLayout.Y_AXIS));
  hi.add(FlowLayout.encloseCenter(createStarRankSlider()));
  hi.show();
}*/



    Form f, f2,f3;
      TextField nbrticket;
    SpanLabel lb, lb1;
    private Resources theme;
    ImageViewer imgv;
    Image image;
    EncodedImage enc;
    TicketService serviceTask = new TicketService();
    ArrayList<com.mycompany.Entite.Ticket> tickets = serviceTask.getList2();
    ArrayList<match> matchs = serviceTask.getList22();
    static public Login y;

   int x=y.u.getId();
     public mesTicket(){
       
        theme = UIManager.initFirstTheme("/theme");
        f = new Form("Mes Tickets");
                        f.setUIID("AbonnementsForm");

        f2 = new Form();
                 f2.setUIID("AbonnementsForm");

           f3 = new Form();
                        f3.setUIID("AbonnementsForm");
                                             Toolbar tb = f.getToolbar();
        Image icon = theme.getImage("icon.png");
        Container topBar = BorderLayout.east(new Label(icon));
        
        tb.addMaterialCommandToSideMenu("Tickets", FontImage.MATERIAL_SHOPPING_CART, e -> {
              AffichageTicket a=new AffichageTicket();
        a.getF().show();
            
        });
        tb.addMaterialCommandToSideMenu("Abonnements", FontImage.MATERIAL_SHOPPING_CART, e -> {
               ajouterAbonnement a=new ajouterAbonnement();
        a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Mes Tickets", FontImage.MATERIAL_TOC, e -> {
              mesTicket a=new mesTicket();
               a.getF().show();
        });
        tb.addMaterialCommandToSideMenu("Mes Abonnements", FontImage.MATERIAL_TOC, e -> {
               mesabonnement a=new mesabonnement();
                a.getF().show();
        });    
         tb.addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            HomeRussie2018 a=new HomeRussie2018();
            a.getF().show();
            
            
        });
          Style s1 = UIManager.getInstance().getComponentStyle("Title");
         FontImage chariiot = FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, s1);
          FontImage aff = FontImage.createMaterial(FontImage.MATERIAL_TOC, s1);
        lb = new SpanLabel("");
        lb1 = new SpanLabel("");
        f.add(lb);
        f.add(lb1);
        UIBuilder ui = new UIBuilder();
         for (com.mycompany.Entite.Ticket t : tickets) {
            if(t.getIdUser()==x){ 
 
         FontImage info = FontImage.createMaterial(FontImage.MATERIAL_SEARCH, s1);
           FontImage res = FontImage.createMaterial(FontImage.MATERIAL_LIBRARY_ADD, s1);
           FontImage prix = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, s1);
       FontImage cat = FontImage.createMaterial(FontImage.MATERIAL_EVENT_SEAT, s1);

          //    FontImage ticket = FontImage.createMaterial(FontImage.MATERIAL_ATTACH_MONEY, s1);
           FontImage ticket = FontImage.createMaterial(FontImage.MATERIAL_CONFIRMATION_NUMBER ,s1);

                             Container C0 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

            Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container C3 = new Container(new BoxLayout(BoxLayout.X_AXIS));

            Container C4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Container C6 = new Container(new BoxLayout(BoxLayout.X_AXIS));

               
            
            EncodedImage encImg
                    = EncodedImage.createFromImage(theme.getImage("smoking.png"), false);
            URLImage imgUrl
                    = URLImage.createToStorage(encImg, "cache7", "http://localhost/sarra/seance7mobil/ssss.jpeg");
            imgUrl.fetch();
            ImageViewer img = new ImageViewer(imgUrl);

            Label l = new Label(t.getCategories(),cat);
            Label l1 = new Label("Prix: "+t.getPrix().toString() +"$");
            
            Label l2 = new Label(String.valueOf(t.getNbrTicket()) ,ticket);
            //  Label l3 =new Label(String.valueOf(t.getIdMatch().getIdMatch()));
                 float mt=Float.parseFloat(String.valueOf(t.getIdMatch()));
                 for (match m:matchs){
                     if (t.getIdMatch()==m.getIdMatch()){
                     SpanLabel l4 = new SpanLabel(m.getEquipe1()+ "Vs" +m.getEquipe2()); 
                                 C2.add(l4);}

                 }
           // System.out.println("mahmouddddddddddd"+ matchs.get(8)); 
           
            //  SpanLabel l5 = new SpanLabel(String.valueOf(t.getIdMatch().getEquipe2()));

            //  System.out.println("samira"+String.valueOf(t.getIdMatch().getIdMatch()));
            Button bt1 = new Button("retour");
            Button bt = new Button("deatils",info);
            
    
  
                                if(t.getNbrTicket()==0){
            bt.setEnabled(true);
            }else{
            
            
            }

            //    C2.add(l3);
            //  C2.add(l5);

            
                   Slider starRank1 = new Slider();
    starRank1.setEditable(false);
    starRank1.setMinValue(0);
    starRank1.setMaxValue(5);
   starRank1.setProgress((int)t.getMoyenne());
   Font fnt = Font.getDefaultFont();
       // Font fnt = FontImage.getMaterialDesignFont();
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
   
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle1(starRank1.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle1(starRank1.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle1(starRank1.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle1(starRank1.getSliderFullUnselectedStyle(), fullStar);
    starRank1.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
            
            
            
            
            /*
            
            
         System.out.println("mm"+t.getIdMatch().getDateMatch());
         
         System.out.println("mm"+t.getIdMatch().getIdMatch());
         System.out.println("mm");
         System.out.println("mm");
             */
            //Label l3=new Label(t.getNbrTicket().)
            //   Storage s = new Storage();
            //   s.clearStorage();
            C5.add(img);
            C3.add(l);
             C3.add(l2);
            C6.add(l1);
           
            // C2.add(lb);
            // C1.add(img);
             // C3.setLeadComponent(l1);
            C4.add(C2);
            C4.add(C3);
            C4.add(C6);
            C1.add(C5);
            C1.add(C4);
            C0.add(C1);
    C0.add(bt);
    
            f.add(C0);
             C5.add(FlowLayout.encloseCenter(starRank1));
 
          //  f.refreshTheme();
                
    }
                }
   f.show();
    
                

        // C1.setLeadComponent(l);
     
        //  f2.add(C1);
      
         
     }
     
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
