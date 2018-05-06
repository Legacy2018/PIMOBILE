/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.UtilisateurServices;
import com.mycompany.Entite.Utilisateur;
import java.io.IOException;

/**
 *
 * @author Katouchi
 */
public class Login {
    Form f;
    static public Utilisateur u=null;
    public Login() {
        f=new Form(BoxLayout.y());
        
        TextField Login=new TextField(null, "Login");
        TextField Password=new TextField(null, "Password");
        Button Log=new Button("Login");
        Label l=new Label();
        Log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                String Message=null;
                u=UtilisateurServices.FindUserBymailorid(Login.getText());
                if(u!=null)
                {
                    if(u.getPassword().equals(Password.getText()))
<<<<<<< HEAD
                       try {
                           new afficherequipeGUI().getF().show();
                    } catch (IOException ex) {
                    }
=======
<<<<<<< HEAD
                    {
                        
                        if(u.getRole().equals("user"))
                            new Messagerie().getF().show();
                        else
                            new HomeAdminForm().getF().show();
//==================Houni t7el el page================================>
                        //new HomeForm().getF().show();
                        
                       
                    }
                       
=======
                       new homeFormTicket().getF().show();
>>>>>>> 3f6d73b81cc43a58ea1c6020a59844de36220e4a
>>>>>>> 16a38b4d341af142b05fea448e539c24eb2a5185
                    else
                        Message="Login ou mot de passe erroné";
                        
                }
                else
                    Message="Login ou mot de passe erroné";
                
                l.setText(Message);
            }

            
        });
        
        f.add(Login);
        f.add(Password);
        f.add(l);
        f.add(Log);
        f.show();
    
    }
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
}
