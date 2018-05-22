/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.match;
import Services.MatchService;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.gui.Login;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class AffichageUserMatch {
    Form form,form2;
    Container cont;
    Container c1;
    Container c2;
   Login l;
     Database db;
boolean created =false;
  int id=l.u.getId();
   private final com.codename1.ui.list.MultiList gui_MultiList = new com.codename1.ui.list.MultiList();
    public AffichageUserMatch() throws ParseException {
       
          LocalNotification n = new LocalNotification();
n.setId("ok");
n.setAlertBody("It's time to take a break and look at me");
n.setAlertTitle("Break Time!");
        form = new Form();
    
     form.setUIID("AbonnementsForm");
     cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     MatchService service=new MatchService();
     
    ArrayList<match> lis=service.getList2();
         created =Database.exists("dbRussiamatch");
        try {
            db= Database.openOrCreate("dbRussiamatch");
            if (created ==false)
            {
            
            db.execute("create table favorismatch  (id int, usr int , mt int,  PRIMARY KEY (id,usr,mt));");
         
            }
            
        } catch (IOException ex) {
         
        }
            
       for (match next : lis) {
           c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
           c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
           Label pays;
           if(next.isEstJoue()) {
               pays = new Label(next.getEquipe1().getPays()+" "+ next.getScore()+" ---- "+next.getScore2()+" "+ next.getEquipe2().getPays());
           }
           else {
               pays = new Label(next.getEquipe1().getPays()+" Vs "+ next.getEquipe2().getPays());
           }
           Label phase = new Label("Phase : "+next.getPhase());
           Label stade = new Label("Stade : "+next.getStade().nom_Stade);
           Label date = new Label("Date : "+ next.getDateMatch());
           Label heure = new Label("heure : "+ next.getHeureMatch());
           Button partager = new Button("Paratager Match");
                      Button favoris = new Button("ajouter favoris");

         partager.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent evt) {
               String accessToken = "EAACEdEose0cBAGchyRcCcSHpVhILT5OMzq73xPlQXuE3ioKGk01UEuTInqTSyD1XRScqZBQe5WcSxRAVDvXVEz8TAseVoMGeYX8FKZABUHZAVZCERmrZBnDr3tFyMrCswFaSLnmeTXkYbDWlxfv8iDqlUOa9RKgurRy7HoKJuZBgTyHz86wXmcZAJYvWZBJu76MZD";
               FacebookClient fbClient = new DefaultFacebookClient(accessToken,Version.VERSION_2_6);
               /*FacebookType responce = fbClient.publish("me/feed",FacebookType.class,Parameter.with("message", "Java Graph API Test"));
               System.err.println("fb.com/"+responce.getId());*/
                
                    

               FacebookType response = fbClient.publish("me/feed",
                       FacebookType.class,
                       Parameter.with("message",next.getEquipe1().pays+" "+next.getScore()+ " - "+next.getScore2()+" "+next.getEquipe2().pays
                       ));
               System.out.println("fb.com/"+response.getId());
               Dialog.show("Succes", "Votre Match à été partagé sur facebook", "Fermer", null);
           }});
         
           
           favoris.addActionListener( new  ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
         //Database db;

        try {
            System.out.println("no");
            db = Database.openOrCreate("dbRussiamatch");
 System.out.println("no1");
            System.out.println("id"+id);
            db.execute("insert into  favorismatch   (id ,usr , mt) values (" + id + "," + id + "," + next.getIdMatch() + ");");
            System.out.println("ok fav");
            Cursor c = db.executeQuery("Select * from favorismatch ;");
            while (c.next()) {
                Row r = c.getRow();
                String a = r.getString(0);

                String n = r.getString(1);
                String pre = r.getString(2);

                System.out.println("id user :" + n + "idmatch:" + pre + "id :" + a);

            }

        } catch (IOException ex) {

        }
               }
           });
           
           c1.add(pays);
           c1.add(stade);
           c1.add(phase);
           c1.add(date);
           c1.add(heure);
           c1.add (favoris);
                   c1.add(partager);
     
         
           
           
           
           cont.add(c1);
       }
        
        
       
       form.add(cont);
          form.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
          h.getF().show();
          });
    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

    
}
