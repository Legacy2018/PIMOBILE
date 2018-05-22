/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Gui;

import Entities.stade;
import Services.StadeService;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;






/**
 *
 * @author Samado
 */
public class AffichageStade {

   Form form,form2;
    Container cont;
    Container c1;
    Container c2;
    TextField t ;
  
    public AffichageStade()   {
      
      
                   form = new Form();
                                    form.setUIID("AbonnementsForm");

                   
                   cont = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                   StadeService serviceTask=new StadeService();
                   ArrayList<stade> lis=serviceTask.getList2();
                   for (Iterator<stade> iterator = lis.iterator(); iterator.hasNext();)
                   {
                       stade next = iterator.next();
                       c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                       c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
                       Label nom = new Label("Nom de stade: "+next.getNom_Stade());
                       Label capacite = new Label("Capacite du stade : "+next.getCapacité());
                       Label Ville = new Label("La ville : "+next.getVille());
                   
                  /*     btn.addActionListener((new ActionListener() {

            public void actionPerformed(ActionEvent evt) {
              
  
            ConnectionRequest r = new ConnectionRequest();
            r.setPost(false);
            r.setUrl("https://maps.googleapis.com/maps/api/place/autocomplete/json");
            r.addArgument("key",MAPS_KEY );
            r.addArgument("input", next.getVille());
            NetworkManager.getInstance().addToQueueAndWait(r);
            Map<String,Object> result;
            try {
                result = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(r.getResponseData()), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(AffichageStade.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(AffichageStade.class.getName()).log(Level.SEVERE, null, ex);
            }
           
    
    
            }
                        })); }*/
            
       
                       Button aj = new Button("ajouter stade");
                       aj.addActionListener((new ActionListener() {
                           @Override
                           public void actionPerformed(ActionEvent evt) {
                               
                           }
                       }));
            
                       
                       c1.add(nom);
                       c1.add(capacite);
                       
                       c1.add(Ville);
                       
                       
                       
                       
                       
                       cont.add(c1);
                       
            }
                   
                   
                   
                   
                   form.add(cont);
                   form.getToolbar().addCommandToRightBar("back", null, (ev)->{HomeForm h=new HomeForm();
                   h.getF().show();
                   });
    }
    
                                

    public Form getF() {
        return form;
    }

    public void setF(Form f) {
        this.form = f;
    }
    

}
