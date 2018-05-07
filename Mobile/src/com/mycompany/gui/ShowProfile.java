/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.UtilisateurServices;
import com.mycompany.Entite.Utilisateur;

/**
 *
 * @author Katouchi
 */
public class ShowProfile {
    Form f=new Form(BoxLayout.y());
    Utilisateur u;
    public ShowProfile(String username) {
        u=UtilisateurServices.FindUserBymailorid(username);
        f.add(new Label(u.getUsername()));
        f.add(new Label(u.getEmail()));
        Button contacter=new Button("Contacter");
        contacter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new Messagerie(u).getF().show();
            }
        });
        f.add(contacter);
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }
    
    
}
