/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import javax.faces.application.NavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import ma.projet.beans.Utilisateur;
import ma.projet.service.UtilisateurService;

/**
 *
 * @author Lenovo
 */
@ManagedBean(name = "utilisateurBean")
public class UtilisateurBean {

    private Utilisateur user;
    private UtilisateurService userService;
    private String username;
    private String mdp;

    public UtilisateurBean() {
        user = new Utilisateur();
        userService = new UtilisateurService();
    }

    public Utilisateur getUtilisateur() {
        return user;
    }

    public void setUtilisateur(Utilisateur user) {
        this.user = user;
    }

    public UtilisateurService getUtilisateurService() {
        return userService;
    }

    public void setUtilisateurService(UtilisateurService userService) {
        this.userService = userService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public boolean login() {
        user = userService.getByUtilisateurname(username);
        if (user != null) {
            if (user.getUsername().equals(username) && user.getMdp().equals(mdp)) {
                System.out.println("connecté !!!!");

                FacesContext context = FacesContext.getCurrentInstance();
                NavigationHandler navigationHandler = context.getApplication().getNavigationHandler();
                navigationHandler.handleNavigation(context, null, "faces/web/employe/EmployeForm.xhtml?faces-redirect=true");

                return true;
            } else {
                System.out.println("Erreur de connexion !!!!");
                return false;
            }
        }
        return false;
    }

}
