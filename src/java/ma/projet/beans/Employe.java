/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.beans;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author Lenovo
 */
@Entity
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nom;
    private String prenom;
    
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    
    @ManyToOne
    private Service service;
    
    @ManyToOne(cascade = CascadeType.REMOVE)
    private Employe chef;
    
    @OneToMany(mappedBy = "chef", fetch = FetchType.EAGER)
    private List<Employe> collaborators;
    
    @Lob
    private byte[] image;
    
    public Employe() {
    }

    public Employe(String nom, String prenom, Date dateNaissance, Service service, Employe chef, List<Employe> collaborators) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.service = service;
        this.chef = chef;
        this.collaborators = collaborators;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    

    
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Employe getChef() {
        return chef;
    }

    public void setChef(Employe chef) {
        this.chef = chef;
    }

    public List<Employe> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<Employe> collaborators) {
        this.collaborators = collaborators;
    }
    
   
    public List<Employe> getHierarchy() {
        List<Employe> hierarchy = new ArrayList<>();
        Employe current = this;

        while (current != null) {
            hierarchy.add(current);
            current = current.getChef();
        }

        Collections.reverse(hierarchy); // Ensure the order is from top to bottom

        return hierarchy;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
    
    
    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", dateNaissance=" + dateNaissance + ", service=" + service.getNom() + ", chef=" + chef + '}';
    }

    public void setImage(StreamedContent imageData) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
      
    
    
}

