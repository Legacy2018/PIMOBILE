/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.mycompany.Entite.DataBaseMobile;
import com.mycompany.Entite.Utilisateur;

/**
 *
 * @author Katouchi
 */
public class UtilisateurServices extends Fos_UserServices {

    public UtilisateurServices() {
    }
    public static Utilisateur FindUserBymailorid(String logger) {
        
        return new Utilisateur();
    }
}
