/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entite;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Katouchi
 */
public class DataBaseMobile {
    private String DataBaseName;
    private String UserName;
    private String Password;
    private String ServeurName;

    public DataBaseMobile(String DataBaseName, String UserName, String Password, String ServeurName) {
        this.DataBaseName = DataBaseName;
        this.UserName = UserName;
        this.Password = Password;
        this.ServeurName = ServeurName;
    }

    public DataBaseMobile() {
    }

    public String getDataBaseName() {
        return DataBaseName;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public String getServeurName() {
        return ServeurName;
    }

    public void setDataBaseName(String DataBaseName) {
        this.DataBaseName = DataBaseName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public void setServeurName(String ServeurName) {
        this.ServeurName = ServeurName;
    }
    public static boolean Execute(String query)
    {
         ConnectionRequest cr=new ConnectionRequest("http://localhost/mobile/PiServices/Request.php");
                cr.setPost(false);
                cr.addArgument("Query", query);
                NetworkManager.getInstance().addToQueue(cr);
                cr.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {
                   String message;
                        try {
                            message=new String( cr.getResponseData(),"utf-8");
                            System.out.println(message);
                        } catch (UnsupportedEncodingException ex) {
                            
                        }
                    }
                });
        return true;
    }
    
}
