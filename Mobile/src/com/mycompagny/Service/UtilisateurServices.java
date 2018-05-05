/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.JSONParser;
import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.Entite.Fos_User;
import com.mycompany.Entite.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Katouchi
 */
public class UtilisateurServices extends Fos_UserServices {

    public UtilisateurServices() {
    }
    public static Utilisateur FindUserBymailorid(String logger) {
        if(!ParsingUsers(DataBaseMobile.Executeselect("Fos_User", " where '"+logger+"'=username ")).isEmpty())
            return ParsingUsers(DataBaseMobile.Executeselect("Fos_User", " where '"+logger+"'=username ")).get(0);
        return null;
    }
    public static ArrayList<Utilisateur> getAllUsers()
    {
        return ParsingUsers(DataBaseMobile.Executeselect("Fos_User", null));
    }
    public static Utilisateur ParsingUser(Map<String, Object> user)
    {
        return new Utilisateur(new Fos_User(Integer.parseInt(user.get("id").toString()) , user.get("username").toString(), user.get("email").toString(), user.get("password").toString(), true, true, "user"));
    }
    public static ArrayList<Utilisateur> ParsingUsers(String JsonArray)
    {    ArrayList<Utilisateur> AllUsers=new ArrayList<>();
        try {
        JSONParser j=new JSONParser();
        Map<String, Object> AllUsersInMap = j.parseJSON(new CharArrayReader(JsonArray.toCharArray()));
        
        for (Iterator<Map<String, Object>> iterator = ( (List<Map<String, Object>>) AllUsersInMap.get("root")).iterator(); iterator.hasNext();) {
            Map<String, Object> next = iterator.next();
            AllUsers.add(ParsingUser(next));
            
        }
        
            
        } catch (IOException ex) {
            
        }
    return AllUsers;
    }
}
