
import ma.projet.beans.Utilisateur;
import ma.projet.service.UtilisateurService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Lenovo
 */
public class test3 {
    public static void main(String[] args) {
        UtilisateurService us = new UtilisateurService();
        Utilisateur u = new Utilisateur();
        u.setUsername("cam123");
        u.setMdp("123");
        
        us.create(u);
    }
}
