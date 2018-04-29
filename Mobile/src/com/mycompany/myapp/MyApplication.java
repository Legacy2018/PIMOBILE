package com.mycompany.myapp;


import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Dialog;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.io.Log;
import com.codename1.ui.Toolbar;
import com.mycompany.gui.afficherequipeGUI;
import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.gui.HomeForm;
import java.io.IOException;

/**
 * This file was generated by <a href="https://www.codenameone.com/">Codename One</a> for the purpose 
 * of building native mobile applications using Java.
 */
public class MyApplication {

    private Form current;
    private Resources theme;

    public void init(Object context) {
        theme = UIManager.initFirstTheme("/theme");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    
    public void start() throws IOException {
        if(current != null){
            current.show();
            return;
        }
          afficherequipeGUI f = new afficherequipeGUI();
        f.getF().show();
    //    DataBaseMobile.Execute("insert into reclamation values(null,'1','1','2018-1-1','faza','faza okhra');");
     //     HomeForm h = new HomeForm();
    //    h.getF().show();
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
