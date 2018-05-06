/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;


import Entities.stade;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author  Samado
 */
public class StadeService {
     public ArrayList<stade> getList2() {
        ArrayList<stade> liststades = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiWebInt/web/app_dev.php/GestionStade/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();
                
                try {
                    //renvoi une map avec clé = root et valeur le reste
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks.get("root"));

                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

                    for (Map<String, Object> obj : list) {
                        stade a = new stade();
                        
                        float id = Float.parseFloat(obj.get("capacite").toString());
                        float id2 = Float.parseFloat(obj.get("id").toString());
                        a.setId_stade((int)id2);
                a.setNom_Stade(obj.get("nom").toString());
            
                a.setVille(obj.get("adresse").toString());
            
                a.setCapacité((int)id);
               
                liststades.add(a);
                       
                      

                    }
                } catch (IOException ex) {
                    System.out.print("elele");
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liststades;
    }
     public void ajouterStade(stade eq) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PiWebInt/web/app_dev.php/GestionStade/ad?"
              
                + "&Nom_Stade=" + eq.getNom_Stade()
                + "&id2=" + eq.getCapacité()
                + "&id=" + eq.getVille()
               ;

        con.setUrl(Url);

        System.out.println("ti imchi");

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            else{
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

}
    

