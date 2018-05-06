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
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import java.text.ParseException;

/**
 *
 * @author admin
 */
public class EditScore {
      Form    f = new Form();
        

        Container    c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        public EditScore(match m) throws ParseException{
              LocalNotification n = new LocalNotification();
n.setId("demo-notification");
n.setAlertBody("It's time to take a break and look at me");
n.setAlertTitle("Break Time!");
            MatchService ms = new MatchService();
          
             Label pays = new Label("  "+m.getEquipe1().pays);
             TextField sc = new TextField(m.getScore());
              TextField sc2 = new TextField(m.getScore2());
              Label pays2 = new Label(""+m.getEquipe2().pays);
              
             
           Button aj = new Button("Modifier");
        c.add(pays);
        c.add(sc);
        c.add(sc2);
        c.add(pays2);
        c.add(aj);
       

     
        aj.addActionListener((e) -> {
                try {
                    m.setScore(Integer.parseInt(sc.getText()));
                    m.setScore2(Integer.parseInt(sc2.getText()));
                    ms.editMatch(m);
                    AffichageMatch h= new AffichageMatch();
                    
                    h.getF().show();
                               Display.getInstance().scheduleLocalNotification(
        n,
        System.currentTimeMillis() + 10 * 1000, // fire date/time
        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
);

                    
                } catch (ParseException ex) {
                    System.out.println("no");
                }
        });
    
        f.add(c);
}

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
