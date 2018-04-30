/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompany.Entite.Equipe;
import java.util.ArrayList;

/**
 *
 * @author Emel
 */
public class ModifierEquipe {
  

    // setLayout(new BorderLayout());
    Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
    SpanLabel lb;
    Container c;
    public ModifierEquipe() {

        f = new Form();
        c = new Container();
        int etat ;
        TextField pays,selec,point = new TextField();
        ComboBox<String> cb= new ComboBox(
                "A","B","C"
        ) ;
        c.add(cb);
          OnOffSwitch genre= new OnOffSwitch();
        genre.setOff("IN");
        genre.setOn("OUT");
           c.add(genre);
        if(genre.isValue())
            etat=1;
        else 
            etat=0;
       Button aj = new Button("Ajouter");

      f.add(c);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
}
