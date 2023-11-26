/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;


@Entity
public class Machine {

    @Id
    @GeneratedValue
    private int id;
    private String marque;
    private String reference;
    private double prix;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateAchat;
    @ManyToOne
    private Salle salle;

    public Machine() {
        salle = new Salle();
    }

    public Machine(String marque, String reference, double prix, Date dateAchat, Salle salle) {
        this.marque = marque;
        this.reference = reference;
        this.prix = prix;
        this.dateAchat = dateAchat;
        this.salle = salle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Date getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(Date dateAchat) {
        this.dateAchat = dateAchat;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

}
