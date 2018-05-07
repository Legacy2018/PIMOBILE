/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.MessagerieService;
import static com.mycompagny.Service.MessagerieService.ParsingMessage;
import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.Entite.Messages;
import com.mycompany.Entite.Utilisateur;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Katouchi
 */
public class Messagerie extends Thread{
    Form f=new Form(BoxLayout.y());
    Utilisateur contact;
    ArrayList<Messages> Messages;
    TextField msgtosend=new TextField(null, "Ecrivez votre message...");
    TextArea Message=new TextArea(null, 15, 2);
    Button Send=new Button("Envoyer");
    public Messagerie(Utilisateur contact) {
       
        this.contact=contact;
        //System.out.println("Le mes "+Messages);
        DescussionForm();
        this.start();
       
    }

    @Override
    public void run() {
       Messages m=null;
        while (true) {            
            m=MessagerieService.IsNewMessage(Login.u.getId(), contact.getId());
            if(m!=null)
            {
                
               updateMessages();
            }
            else
            {
                
               System.gc(); 
            }
        }
    }
    public void updateMessages()
    {
        Message.getAllStyles().setFgColor(0);
        Messages=MessagerieService.getConvers(contact.getId());
        System.out.println("Houni el kol"+ Messages);
        Message.setText("Contact Name");
       if(Messages!=null)
       {
           if(!Messages.isEmpty())
           {
               for (Iterator< Messages > iterator = Messages.iterator(); iterator.hasNext();) {
                Messages m=iterator.next();
                System.out.println(m);
                if(m.getSender()==Login.u.getId())
                    Message.setText(Message.getText()+"\n"+"You : "+m.getMessage());
                else
                    Message.setText(Message.getText()+"\n"+contact.getUsername()+" : "+m.getMessage());
                
        }
           }
           
       }
        
    }
    
    public void DescussionForm()
    {
        
        Message.setEnabled(false);
        Send.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                DataBaseMobile.Execute("INSERT INTO `messages` (`id`, `id_sender`, `id_recever`, `msg`, `afficher`, `datemsg`) VALUES (NULL, "+Login.u.getId()+", "+contact.getId()+", '"+msgtosend.getText()+"', 0, '2018-05-03 00:00:00');");
                updateMessages();
            }
        });
        Container C=new Container(BoxLayout.x());
        msgtosend.setColumns(12);
        Message.getAllStyles().setFgColor(0);
        updateMessages();
        C.add(msgtosend);
        C.add(Send);
        f.add(Message);
        f.add(C);
    }
    
    
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
    
}