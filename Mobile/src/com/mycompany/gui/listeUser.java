/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.db.Cursor;
import com.codename1.db.Row;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.UtilisateurServices;
import static com.mycompagny.Service.UtilisateurServices.ParsingUser;
import com.mycompany.Entite.Utilisateur;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Katouchi
 */
public class listeUser {
    Form f=new Form(BoxLayout.y());;
    ArrayList<Utilisateur> Users=new ArrayList<>();
    TextField recherche=new TextField(null, "Username");
    public listeUser(String sort) {
        f.setScrollableY(true);
        f.add(recherche);
        recherche.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               
                    
                    new listeUser(" where username like '%"+recherche.getText()+"%' ");
                
            }
        });
        
        Users=UtilisateurServices.getAllUsers();
        try {
            MyApplication.db.execute("DELETE FROM Utilisateurs;");
        } catch (IOException ex) {
            
        }
        for (Iterator<Utilisateur> iterator = Users.iterator(); iterator.hasNext();) {
           Utilisateur t = iterator.next();
        try {
            MyApplication.db.execute("Insert into Utilisateurs (id,username,email) values("+t.getId()+",'"+t.getUsername()+"','"+t.getEmail()+"'); ");
        } catch (IOException ex) {
            
        }
        try {
          //
           afficher(sort);
       } catch (IOException ex) {
           
       }
        }
       
        
       
    }
    public void afficher(String sorted) throws IOException
    {
        
         
             Cursor c;
             if(sorted==null)
                c=MyApplication.db.executeQuery("Select * from Utilisateurs;");
             else
                c=MyApplication.db.executeQuery("Select * from Utilisateurs "+sorted+" ;");
             Container con=new Container(BoxLayout.y());
            while (c.next()) {  
             con=new Container(BoxLayout.y());
             Row r= c.getRow();
             Label l1=new Label(r.getString(1));
             l1.addPointerPressedListener(new ActionListener() {
                 @Override
                 public void actionPerformed(ActionEvent evt) {
                     
                         new ShowProfile(l1.getText()).getF().show();
                     
                 }
             });
             con.setScrollableY(true);
             con.setLeadComponent(l1);
             Label l2=new Label(r.getString(2));
             Label l3=new Label("----------------------");
             l1.getAllStyles().setFgColor(0);
             l2.getAllStyles().setFgColor(0);
             l3.getAllStyles().setFgColor(0);
             con.add(l1);
             con.add(l2);
             con.add(l3);
             
             f.add(con);
            }
    
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public ArrayList<Utilisateur> getUsers() {
        return Users;
    }

    public void setUsers(ArrayList<Utilisateur> Users) {
        this.Users = Users;
    }
    
}
