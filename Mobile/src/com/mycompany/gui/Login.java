/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import Gui.AffichageMatch;
import Gui.AffichageUserMatch;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.UtilisateurServices;
import com.mycompany.Entite.Utilisateur;
import java.text.ParseException;


/**
 *
 * @author Katouchi
 */
public class Login {
    Form f;
    static public Utilisateur u=null;
    public Login() {
        f=new Form("Login",BoxLayout.y());
        
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
                       {
                         //System.out.println(u.getRole());
                        if(u.getRole().equals("a:0:{}"))//Houni el user chneya i7el
                        {   
                            new HomeRussie2018().getF().show();
                            }
                           
                        else//Houni el admin
                        {
                            new HomeRussieAdmin2018().getF().show();
                        }
                        
                       
                    }
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
