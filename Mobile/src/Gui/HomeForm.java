/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import java.text.ParseException;


/**
 *
 * @author sana
 */
public class HomeForm {

    Form f;
    TextField tnom;
    TextField tetat;
    Button btnajout,btnaff;

    public HomeForm() {
        f = new Form("Russie 2018");
                 f.setUIID("AbonnementsForm");
     
        btnajout = new Button("Affichage Matchs");
        FontImage.setMaterialIcon(btnajout, FontImage.MATERIAL_HOME);
        btnaff=new Button("Affichage Stade");
        FontImage.setMaterialIcon(btnaff, FontImage.MATERIAL_ACCOUNT_CIRCLE);
        f.add(btnajout);
        f.add(btnaff);
                   
            

       btnajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    AffichageMatch m = new AffichageMatch();
                    m.getF().show();
                } catch (ParseException ex) {
                   
                }
            }
        });
        btnaff.addActionListener((e)->{
  LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE ); // Whether to repeat and what frequency
         
         
        
        AffichageStade a  =new AffichageStade();
        
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getTnom() {
        return tnom;
    }

    public void setTnom(TextField tnom) {
        this.tnom = tnom;
    }

}
