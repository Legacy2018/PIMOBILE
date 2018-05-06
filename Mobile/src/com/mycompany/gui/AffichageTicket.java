/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.io.ConnectionRequest;
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
import java.util.ArrayList;
import com.mycompany.Entite.Ticket;
import com.mycompany.Entite.match;
import java.io.IOException;
import com.codename1.messaging.Message;
import com.codename1.ui.Dialog;
import com.mycompagny.Service.AbonnementService;

/**
 *
 * @author BSS
 */
public class AffichageTicket {
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
    ArrayList<Ticket> tickets = serviceTask.getList2();
    ArrayList<match> matchs = serviceTask.getList22();

        String textAttachmentUri;
          
    public AffichageTicket() {
       
        theme = UIManager.initFirstTheme("/theme");
        f = new Form();
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
        });
        tb.addMaterialCommandToSideMenu("Mes Abonnements", FontImage.MATERIAL_TOC, e -> {
               mesabonnement a=new mesabonnement();
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
        //  Form f = new Form("russie2019", BoxLayout.y());
        for (Ticket t : tickets) {
 
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
                    = EncodedImage.createFromImage(theme.getImage("music.png"), false);
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
                    Dialog.show("error", "nombre de tickets  epuisé", "ok", null);
            }else{
                                  //   bt.setEnabled(false);
            
           

            ConnectionRequest req = new ConnectionRequest();
            req.setUrl("http://localhost/sarra/piWeb1/web/app_dev.php/api/ticket/find?idTicket=" + t.getIdTicket());

            bt.addActionListener(new ActionListener() {
           
                @Override
                public void actionPerformed(ActionEvent evt) {

                    Container C1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

                    //  ImageViewer img = new ImageViewer(theme.getImage("round.png"));
                    Container C2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

                    Container C3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                               FontImage ticket = FontImage.createMaterial(FontImage.MATERIAL_CONFIRMATION_NUMBER ,s1);

                 nbrticket = new TextField();
                            nbrticket.setHint("Nombres de Tickets ");
           for (match m:matchs){
                     if (t.getIdMatch()==m.getIdMatch()){
                     SpanLabel l4 = new SpanLabel(m.getEquipe1()+ "Vs" +m.getEquipe2()); 
                                 C3.add(l4);}

                 }
           // FontImage prix = FontImage.createMaterial(FontImage.MATERIAL_MONEY_OFF, s1);

                    //+ "VS" + String.valueOf(t.getIdMatch().getEquipe2()));
                    //  Label l5 =new Label(String.valueOf(t.getIdMatch().getEquipe2()));
                    Label l = new Label(t.getCategories(),cat);
                    l.setWidth(100);
                   Label l1 = new Label("Parix:" + t.getPrix().toString(),prix);
                   l1.setWidth(100);
                   Label l2 = new Label("Nombre de Tickets Dispo: "+String.valueOf(t.getNbrTicket()),ticket);
                 l2.setWidth(100);
         

              
                    try {
                        enc = EncodedImage.create("/giphy.gif");
                    } catch (IOException ex) {

                    }
                    image = URLImage.createToStorage(enc, "im5", "http://localhost/sarra/seance7mobil/59b8043afc7e93361e8b4570.jpg", URLImage.RESIZE_SCALE);
                    imgv = new ImageViewer(image);
                    C2.add(image);
                    
                          Button bton = new Button("reserver",res);
                          
              
                     bton.addActionListener((e) -> {
                         
                           
                         if (VerifInt(nbrticket.getText())&&(Float.valueOf(nbrticket.getText())<=t.getNbrTicket()) ){
                         TicketService ser = new TicketService();
                       float nbr = t.getNbrTicket();
                       //  match tmpMatch = new match((int) Float.parseFloat(String.valueOf(t.getIdMatch())));
                  
                             //  float id = Float.parseFloat(String.valueOf(t.getIdTicket()));
float resu = nbr-Integer.parseInt(nbrticket.getText());
 

            Ticket tick = new Ticket(t.getIdTicket(),(int) resu);
              ser.reserverticket(tick);
 
      
                            
                
          AffichageTicket b=new AffichageTicket();
        b.getF().show();
          for (match o:matchs){
                     if (t.getIdMatch()==o.getIdMatch()){
                     

               
              
                                
                            Message m = new Message("Votr reservation de ticket a été effectué avec succé du matchs "+o.getEquipe1()+ "Vs" +o.getEquipe2());
                     
                            m.getAttachments().put(textAttachmentUri, "text/plain");
                      //      m.getAttachments().put(imageAttachmentUri, "image/png");
                            Display.getInstance().sendMessage(new String[] {"sarraboukercha0@gmail.com"}, "Russie2018", m);
           
              
                }
                     }
                        
                      }else {ToastBar.showMessage("le nombre de ticket incorrect", FontImage.MATERIAL_WARNING, 5000);
                    }
                    });
                     Slider starRank = new Slider();
    starRank.setEditable(true);
    starRank.setMinValue(0);
    starRank.setMaxValue(5);
  // starRank.setProgress((int)t.getMoyenne());
   Font fnt = Font.getDefaultFont();
       // Font fnt = FontImage.getMaterialDesignFont();
    Style s = new Style(0xffff33, 0, fnt, (byte)0);
    Image fullStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    s.setOpacity(100);
    s.setFgColor(0);
   
    Image emptyStar = FontImage.createMaterial(FontImage.MATERIAL_STAR, s).toImage();
    initStarRankStyle(starRank.getSliderEmptySelectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderEmptyUnselectedStyle(), emptyStar);
    initStarRankStyle(starRank.getSliderFullSelectedStyle(), fullStar);
    initStarRankStyle(starRank.getSliderFullUnselectedStyle(), fullStar);
    starRank.setPreferredSize(new Dimension(fullStar.getWidth() * 5, fullStar.getHeight()));
  
                    
         starRank.addDataChangedListener(new DataChangedListener() {

                  @Override
                  public void dataChanged(int type, int index) {
                       float x=starRank.getProgress();//To change body of generated methods, choose Tools | Templates.
                 float y= (t.getMoyenne()+x)/2;
                 float nbr=Float.parseFloat(String.valueOf(t.getNbrTicket()));
                 TicketService ser = new TicketService();
                    Ticket tick = new Ticket(t.getIdTicket(),(int)nbr,y);
                   // System.out.println("aaaaaaaaa"+tick+"bbbbbbb"+y);
              ser.moyenneticket(tick);
             
                        
    
                  }
                  });           
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    

                 //   C3.add(l4);
                    // C3.add(l5);
                    C3.add(l);
                    C3.add(l1);
                    C3.add(l2);
                 
                     C3.add(FlowLayout.encloseCenter(starRank)); 
                      C3.add(nbrticket);
                C1.add(C2);
                         C3.add(bton);
                                    
                   f2.add(C1);
                    f2.add(C3);
                      
 
 

               

                  Toolbar tb = f2.getToolbar();
                            tb.addCommandToLeftBar("back", null, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                              /* C1.removeAll();
                                   C2.removeAll();
                                   C3.removeAll();
                                    starRank.remove();
                                  C4.removeAll();
                                    C5.removeAll();*/
                                AbonnementService ser = new AbonnementService();
           
            
          AfficherAbonnement b=new AfficherAbonnement();
        b.getF().show();
                       
                                }
                            });
                          f2.show();
                            
                }
            });
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
                

   f.show();
        }
                

        // C1.setLeadComponent(l);
     
        //  f2.add(C1);
        //  f2.refreshTheme();
        // lb.setText(serviceTask.getList2().get(9).getIdMatch().getDateMatch().toString());

    }
  public boolean VerifInt(String Aux){
        try {int f=Integer.parseInt(Aux);
        return true;}
        catch(Exception e){return false;}
        
    }
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Form getF2() {
        return f2;
    }

    public void setF2(Form f2) {
        this.f2 = f2;
    }

}
