/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

/**
 *
 * @author Lenovo
 */
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.ServiceService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.List;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean
@RequestScoped
public class ServiceEmployeeManagedBean {

    private List<Service> services;
    private Service selectedService;
    private List<Employe> employees;
    private ServiceService serviceService;
    
    
    private TreeNode root;
    
    public ServiceEmployeeManagedBean(){
        serviceService = new ServiceService();
       
        
        List<Service> serviceList = serviceService.getAll();
        
        // This is the root node, so it's data is root and its parent is null
        this.root = new DefaultTreeNode("Root Node", null);
        
        for(Service s : serviceList){
            // Create child node
            TreeNode child = new DefaultTreeNode("Service: " + s.getNom(), this.root);
            // Reference the parent of child node
            child.setParent(this.root);

            List<Employe> employes = serviceService.getEmployeesByService(s);
              
            if(employes != null && !employes.isEmpty()){
                TreeNode descendent = new DefaultTreeNode("Chef de Service: " + employes.get(0).getNom() + " " + employes.get(0).getPrenom(), child);
                // Reference the parent of descendent node
                descendent.setParent(child);

                for(int i = 1; i < employes.size(); i++){
                    TreeNode em1 = new DefaultTreeNode(employes.get(i).getNom() + " " + employes.get(i).getPrenom(), descendent);
                    em1.setParent(descendent);
                }
            }
            
        }
        
    }

    public TreeNode getRoot() {
            return root;
    }

    public void setRoot(TreeNode root) {
            this.root = root;
    }
        
    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service getSelectedService() {
        return selectedService;
    }

    public void setSelectedService(Service selectedService) {
        this.selectedService = selectedService;
    }

    public List<Employe> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employe> employees) {
        this.employees = employees;
    }

    public void loadEmployeesByService() {
        if (selectedService != null) {
            employees = serviceService.getEmployeesByService(selectedService);
        }
    }
}
