/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.Equipe;
import Entities.match;
import Entities.stade;
import Services.MatchService;
import Services.ServiceEquipe;
import Services.StadeService;
import com.codename1.ui.*;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.spinner.TimeSpinner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author admin
 */
public class AjouterMatch {
    
        Form    f = new Form();
        Container    c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
      public AjouterMatch(){
          
          MatchService ms = new MatchService();
          StadeService sa = new StadeService();
          ServiceEquipe a = new ServiceEquipe();
          ArrayList<Equipe> li = a.getLi();
           ArrayList<stade> lis=sa.getList2();
            Picker stade= new  Picker();
            Picker pays1 = new Picker();
            Picker pays2 = new Picker();
            String[] phases = {"premier phase ","1/8","1/4","1/2","Final"};
             ComboBox phase = new ComboBox("premier phase ","1/8","1/4","1/2","Final");
             ComboBox<String> group= new ComboBox(
                          "A","B","C","D","E","F","G","H"
                  ) ;
              Button ajouterM = new Button("Ajouter Match");
                  Picker date = new Picker();
                  Picker heure = new Picker();
                  heure.setType(Display.PICKER_TYPE_TIME);
            java.util.List<String> paysNames = new ArrayList<>(); 
             java.util.List<String> stadesNames = new ArrayList<>(); 
            
           for (Equipe next2 : li)
            {
                paysNames.add(next2.getPays());
                pays1.setStrings(paysNames.toArray(new String[0]));
                pays2.setStrings(paysNames.toArray(new String[0]));
                
              for (stade next : lis) {
                  stadesNames.add(next.getNom_Stade());
                
                 
                  stade.setStrings(stadesNames.toArray(new String[0]));
                
                  
                 
                  }
                 
                   }
                  
                  ajouterM.addActionListener(new ActionListener() {
                      @Override
                      public void actionPerformed(ActionEvent evt){
                         match m = new match() ; 
                         Equipe p1 = new Equipe();       
                         Equipe p2 = new Equipe();
                         stade s = new stade();

                        int id1 = li.get(paysNames.indexOf(pays1.getSelectedString())).getIdEquipe();
                        int id2 = li.get(paysNames.indexOf(pays2.getSelectedString())).getIdEquipe();
                        int id3 = lis.get(stadesNames.indexOf(stade.getSelectedString())).getId_stade();
                        p1.setIdEquipe(id1);  
                        p2.setIdEquipe(id2);
                        s.setId_stade(id3);
                        m.setEquipe1(p1)
                                ;
                        m.setEquipe2(p2);
                        m.setStade(s);
                        Date dateMatch = date.getDate();
                        String heureMatch ="";
                        int heures = heure.getTime() / 60;
                        int min = heure.getTime() % 60;
                        if(heures<10) {
                            heureMatch+="0";
                        }
                        heureMatch+=heures+":";
                        if(min<10) {
                            heureMatch+="0";
                        }
                        heureMatch+=min;
                        m.setHeureMatch(heureMatch);
                        m.setDateMatch(dateMatch);
                        m.setPhase(phases[phase.getSelectedIndex()]);
                        ms.ajouterMatch(m);
                          try {
                              (new AffichageMatch()).getF().show();
                              
                              // p1.setIdEquipe(li.get());
                          } catch (ParseException ex) {
                              
                          }
                      }
                  });  
             
                  c.add(phase);
                  c.add(date);
                  c.add(heure);
                  
                  c.add(stade);
                  c.add(pays1);
                  c.add(pays2);
                  c.add(ajouterM);

      f.add(c);
      }
      
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }}
