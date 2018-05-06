/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;

/**
 *
 * @author Emel
 */
public class gererEquipe {
    
    // setLayout(new BorderLayout());
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
 
    public gererEquipe() {

        f = new Form();
                 f.setUIID("AbonnementsForm");

       

      
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
}
