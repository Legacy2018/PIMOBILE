package com.mycompany.myapp;


import com.codename1.db.Database;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import com.mycompagny.Service.UtilisateurServices;
import com.mycompany.gui.afficherequipeGUI;
import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.Entite.Utilisateur;
import com.mycompany.gui.homeFormTicket;
import com.mycompany.gui.HomeAdminForm;
import com.mycompany.gui.Login;
import java.io.IOException;
import com.mycompagny.Service.MessagerieService;
import com.mycompany.Entite.Messages;
/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

     Database db;
boolean created =false;
    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() 
            //throws IOException 
    {
        
        
           created =Database.exists("dbRussia2018");
        try {
            db= Database.openOrCreate("dbRussia2018");
            if (created ==false)
            {
            
            db.execute("create table favoris (id int, usr int , eq int,  PRIMARY KEY (id,usr,eq));");
            System.out.println("ok  ahahaha     ");
            }
            
        } catch (IOException ex) {
         
        }
        
        /*if(current != null){
            current.show();
            return;
        }
        /* afficherequipeGUI f;
        try {
            f = new afficherequipeGUI();
                    f.getF().show();

        } catch (IOException ex) {
        }*/
         
      
        Login l=new Login();
    // HomeAdminForm Admin = new HomeAdminForm();
    //  Admin.getF().show();

      // HomeAdminForm Admin = new HomeAdminForm();
      // Admin.getF().show();
    //    DataBaseMobile.Execute("insert into reclamation values(null,'1','1','2018-1-1','faza','faza okhra');");

    //     HomeForm h = new HomeForm();
    //   h.getF().show();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
        if(current instanceof Dialog) {
            ((Dialog)current).dispose();
            current = Display.getInstance().getCurrent();
        }
    }
    
    public void destroy() {
    }

}
