/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.mycompagny.Service.TicketService;
import com.mycompany.gui.Ticket;

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
        f = new Form("home");
        /*tnom = new TextField();
        tetat = new TextField();
        btnajout = new Button("ajouter");*/
        btnaff=new Button("Affichage");
       /* f.add(tnom);
        f.add(tetat);
        f.add(btnajout);
       */ f.add(btnaff);/*
        btnajout.addActionListener((e) -> {
            TicketService ser = new TicketService();
            Ticket t = new Ticket( tnom.getText(), Float.parseFloat(tetat.getText()));
            //ser.ajoutTask(t);
            

        });*/
        btnaff.addActionListener((e)->{
        AffichageTicket a=new AffichageTicket();
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
