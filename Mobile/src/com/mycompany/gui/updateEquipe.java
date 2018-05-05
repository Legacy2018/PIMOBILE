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
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.events.FocusListener;
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
        System.out.print("equipe "+equ);
                
        f = new Form();
        c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      Container  c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));

        int etat ;
        Label p= new  Label("Pays");
                Label sel= new  Label("Selectionneur");
        Label In= new  Label("In");
                Label Out= new  Label("Out");

          OnOffSwitch genre= new OnOffSwitch();
        genre.setOff("IN");
        genre.setOn("OUT");
      

        TextField pays = new TextField(equ.getPays());
        TextField selec = new TextField(equ.getSelecteur());
        TextField point = new TextField(equ.getPoint());
        ComboBox<String> cb= new ComboBox(
                "A","B","C","D","E","F","G","H"
        ) ;
        cb.setSelectedIndex(4);
        c.add(pays);
        c.add(selec);
        c.add(point);
        c.add(cb);
        
           //c.add(selec);
            //       c.add(point);
          c1.add(In);
        c1.add(genre);
        c1.add(Out);
        c.add(c1);
        if(equ.getEtat()==1)
        
        {genre.isValue();
        etat=1;
        }
        else 
        {genre.invalidate();
        etat=0;
        }
        
        pays.addPointerPressedListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                System.err.println("click");
            }
        });
        
       Button aj = new Button("Modifier");

     
        aj.addActionListener((e) -> {
            ServiceEquipe ser = new ServiceEquipe();
           // Equipe eq = new Equipe(pays.getText(), etat, "phase", cb.getSelectedItem().toString() ,"aa", 5
              //      , null);
            String s=cb.getSelectedItem().charAt(0)+"";
             System.out.print("aa cb sb : "+s);
            equ.setEtat(etat);
            equ.setGroupe(s);
           
            equ.setPays(pays.getText());
            equ.setPoint(Integer.parseInt(point.getText()));
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
