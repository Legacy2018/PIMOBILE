/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.match;
import Services.MatchService;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;


import java.text.ParseException;

import java.util.ArrayList;

/**
 *
 * @author samado
 */
public class AffichageMatch {

   Form form,form2;
    Container cont;
    Container c1;
    Container c2;

    public AffichageMatch() throws ParseException {
          LocalNotification n = new LocalNotification();
n.setId("ok");
n.setAlertBody("It's time to take a break and look at me");
n.setAlertTitle("Break Time!");
        form = new Form();
                 form.setUIID("AbonnementsForm");
    
    
     cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     MatchService service=new MatchService();
     
    ArrayList<match> lis=service.getList2();
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
        
     
           Button Edit = new Button("Edit score ");
           Edit.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent evt) {
                   try {
                       System.out.println("edit "+next);
                       EditScore update = new EditScore(next);
                       update.getF().show();
                   } catch (ParseException ex) {
                       System.out.println("nono");
                   }
                   
               }
           });
           
           
           
           c1.add(pays);
           c1.add(stade);
           c1.add(phase);
           c1.add(date);
           c1.add(heure);
         
           c1.add(Edit);
         
           
           
           
           cont.add(c1);
       }
        
        
       
       form.add(cont);
          form.getToolbar().addCommandToRightBar("back", null, (ev)->{try {
              AffichageMatch h=new AffichageMatch();
              h.getF().show();
              } catch (ParseException ex) {
              }
          });
    }

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }

}
