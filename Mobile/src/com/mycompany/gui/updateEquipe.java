/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.OnOffSwitch;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompagny.Service.ServiceEquipe;
import com.mycompany.Entite.Equipe;

/**
 *
 * @author Emel
 */
public class updateEquipe {
      Container c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

    Form f;
    SpanLabel lb;
    Container c;
    
    public updateEquipe(Equipe equ) {

        f = new Form();
        c = new Container();
        int etat ;
        TextField pays = new TextField(equ.getPays());
        TextField selec = new TextField(equ.getSelecteur());
        TextField point = new TextField(equ.getPoint());;
        ComboBox<String> cb= new ComboBox(
                "A","B","C","D","E","F","G","H"
        ) ;
        cb.setSelectedItem(equ.getGroupe());
        c.add(pays);
        
        c.add(cb);
          OnOffSwitch genre= new OnOffSwitch();
        genre.setOff("IN");
        genre.setOn("OUT");
           c.add(genre);
           c.add(selec);
                   c.add(point);
        if(equ.getEtat()==1)
        
        {genre.isValue();
        etat=1;
        }
        else 
        {genre.invalidate();
        etat=0;
        }
        
       Button aj = new Button("Modifier");

     
        aj.addActionListener((e) -> {
            ServiceEquipe ser = new ServiceEquipe();
           // Equipe eq = new Equipe(pays.getText(), etat, "phase", cb.getSelectedItem().toString() ,"aa", 5
              //      , null);
            equ.setEtat(etat);
            equ.setGroupe(cb.getSelectedItem().toString());
            equ.setPays(pays.getText());
            equ.setPoint(5);
            equ.setSelecteur(selec.getText());
            ser.UpdateEquipe(equ);
            HomeAdminForm h= new HomeAdminForm();
            h.getF().show();
        });
        c.add(aj);
      f.add(c);
        
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    } 
}
