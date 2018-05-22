/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import static com.mycompagny.Service.UtilisateurServices.ParsingUser;
import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.Entite.Messages;
import com.mycompany.gui.Login;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Katouchi
 */
public class MessagerieService {
    public static ArrayList<Messages> getConvers(int contact)
    {
        ArrayList<Messages> messages=ParsingMessages(DataBaseMobile.Executeselect("Messages", " where id_recever="+contact+" and id_sender="+Login.u.getId()+" or id_recever="+Login.u.getId()+" and id_sender="+contact+" "));
        if(messages.isEmpty())return null;
        else return messages;
    }
    public static void updateStat(Messages messages)
    {
        DataBaseMobile.Execute("update Messages set afficher=0 where id="+messages.getId());
    }
    public static Messages IsNewMessage(int recever,int sender)
    {
        ArrayList<Messages> messages=ParsingMessages(DataBaseMobile.Executeselect("messages", " where afficher=1 and id_recever="+recever+" and id_sender="+sender));
        if(!messages.isEmpty())
        {
            
            updateStat(messages.get(0));
            return messages.get(0);
        }
        else
            return null;
    
    }
    public static Messages ParsingMessage(Map<String, Object> Msg)
    {
        return new Messages(Integer.parseInt(Msg.get("id_sender").toString()), Integer.parseInt(Msg.get("id_recever").toString()), Msg.get("msg").toString(), Integer.parseInt(Msg.get("id").toString()), true, null);
    }
    public static ArrayList<Messages> ParsingMessages(String JsonArray)
    {
        ArrayList<Messages> Messages=new ArrayList<>();
        try {System.out.println("houni====> "+JsonArray);
            JSONParser j=new JSONParser();
            Map<String, Object> AllUsersInMap = j.parseJSON(new CharArrayReader(JsonArray.toCharArray()));
            
            for (Iterator<Map<String, Object>> iterator = ( (List<Map<String, Object>>) AllUsersInMap.get("root")).iterator(); iterator.hasNext();) {
            Map<String, Object> next = iterator.next();
            Messages.add(ParsingMessage(next));
            
        }
        } catch (IOException ex) {
            
        }
        
        return Messages;
    }
    
}
