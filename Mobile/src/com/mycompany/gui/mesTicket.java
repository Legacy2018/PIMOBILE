/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Form;
import com.mycompany.Entite.Fos_User;

/**
 *
 * @author BSS
 */
public class mesTicket {
     Form f;
     
     public mesTicket(){
        f = new Form("mes Ticket");
    
    
         
     }
     
     public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
