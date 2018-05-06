/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Equipe;
import Entities.match;
import Entities.stade;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Samado
 */
public class MatchService {
      public ArrayList<match> getList2() throws ParseException {
             ArrayList<match> listmatches = new ArrayList<>();
        
        
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/PiWebInt/web/app_dev.php/GestionMatch/all");
         NetworkManager.getInstance().addToQueueAndWait(con);
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
                        match a = new match();
                      Equipe e = new Equipe();
                      Equipe e2 = new Equipe();
                      stade s = new stade();
              float idM= Float.parseFloat(obj.get("idMatch").toString());
                a.setIdMatch((int)idM);
                 a.setPhase(obj.get("phase").toString());
               e.setPays(((Map<String,Object>) obj.get("idEquipe1") ) .get("pays").toString());
                e2.setPays(((Map<String,Object>) obj.get("idEquipe2") ) .get("pays").toString());
                s.setNom_Stade(((Map<String,Object>) obj.get("nomStade") ) .get("nom").toString());
               if(obj.get("score")!=null) {
                   float id = Float.parseFloat(obj.get("score").toString());
                   a.setScore((int)id);
               }
               if(obj.get("score2")!=null) {
                  float id2 = Float.parseFloat(obj.get("score2").toString());
                  a.setScore2((int)id2);
               }
               a.setEstJoue(obj.get("score")!=null && obj.get("score2")!=null);
                Float d = Float.parseFloat(((Map<String,Object>) obj.get("date") ) .get("timestamp").toString());
                Float d1 = Float.parseFloat(((Map<String,Object>) obj.get("heure") ) .get("timestamp").toString());
               SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");  
               SimpleDateFormat sf1 =new SimpleDateFormat("hh:mm");
             Long k = d.longValue();
             Long k1 = d1.longValue();
                
               Date format= new Date(k*1000);
               Date format1 = new Date(k1*1000);
               String p = sf.format(format);
               String p1 =sf1.format(format1);
               
               System.out.println("Date "+p);
                   System.out.println("heure "+p1);
                a.setDateMatch(format);
                a.setHeureMatch(p1);
                a.setStade(s);
                a.setEquipe2(e2);
                a.setEquipe1(e);
                
                 listmatches.add(a);
                       
                      

                    }}
                catch (IOException ex) {
            Log.e(ex);
        }
            
            }});
                 NetworkManager.getInstance().addToQueueAndWait(con);
        return listmatches;
    

}
public void ajouterMatch(match eq) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PiWebInt/web/app_dev.php/GestionMatch/go";
        
        con.setUrl(Url);
        con.addArgument("id",eq.getEquipe1().getIdEquipe()+"");
        con.addArgument("id2",eq.getEquipe2().getIdEquipe()+"");
        con.addArgument("date", eq.getDateMatch().toString());
        con.addArgument("phase", eq.getPhase());
        con.addArgument("heure", eq.getHeureMatch());
        con.addArgument("Nom_Stade", eq.getStade().getId_stade()+"");
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
public void editMatch(match eq) {
        ConnectionRequest con = new ConnectionRequest();

        String Url = "http://localhost/PiWebInt/web/app_dev.php/GestionMatch/edi";
        
        con.setUrl(Url);
        con.addArgument("idMatch",eq.getIdMatch()+"");
        con.addArgument("score",eq.getScore()+"");
        con.addArgument("score2",eq.getScore2()+"");

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
