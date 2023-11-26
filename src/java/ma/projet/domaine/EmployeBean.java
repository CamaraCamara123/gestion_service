/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.annotation.MultipartConfig;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.ServiceService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;
import sun.misc.IOUtils;

/**
 *
 * @author Lenovo
 */

@ManagedBean(name = "employeBean")
@ViewScoped 
public class EmployeBean  {
    private Employe employe;
    private Employe chef;
    private Service service;
    private List<Employe> employes;
    private EmployeService employeService;
    private ServiceService serviceService;
    private static ChartModel barModel;
    private List<Employe> chefs;
    
    public EmployeBean() {
        employe = new Employe();
        service = new Service();
        chef = new Employe();
        employe.setChef(chef);
        employe.setService(service);
        employeService = new EmployeService();
        serviceService = new ServiceService();
        chefs = new ArrayList<>();

    }
   
    
    public List<Employe> getEmployes() {
        if (employes == null) {
            employes = employeService.getAll();
        }
        return employes;
    }

    public void setEmployes(List<Employe> employes) {
        this.employes = employes;
    }

    public EmployeService getEmployeService() {
        return employeService;
    }

    public void setEmployeService(EmployeService employeService) {
        this.employeService = employeService;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    
    public String onCreateAction() {
        if (employe.getChef() != null) {
            // Assuming chef is a managed entity or retrieved from the database
            Employe chef = employeService.getById(employe.getChef().getId());
            System.out.println("chef: " + chef);
            employe.setChef(chef);
        } else {
            employe.setChef(null);
        }
        
        employeService.create(employe);
        employe = new Employe();
        service = new Service();
        chef = new Employe();
        employe.setChef(null);
        employe.setService(null);
        return null;
    }

//    public List<Employe> serviceLoad() {
//        for (Employe e : employeService.getAll()) {
//            if (e.getService().equals(service)) {
//                employes.add(e);
//            }
//        }
//        return employes;
//
//    }
    
//    public void load() {
//        System.out.println("service: " + service.getNom());
//        service = serviceService.getById(service.getId());
//        chef = employeService.getById(chef.getId());
//        getEmployes();
//    }
    
    public List<Employe> getChefs(){
        employeService.getAll().stream().filter((e) -> (e.getChef() == null)).forEach((e) -> {
            chefs.add(e);
        });
        return chefs;
    }
    
    public void onChangeChef(ValueChangeEvent event){
        if(event.getNewValue() != null){
            System.out.println(event.getNewValue());
            Employe newChef = employeService.getById((int) event.getNewValue());
            System.out.println("nouveau Chef: " + newChef.getNom()); 
            setChef(newChef);
            
        }else{
            setChef(new Employe());
            System.out.println("null event");
        } 
    }
    
    public List<Service> getValidService(){
       System.out.println("chxcvdfedfch: " + chef.getNom()); 
        List<Service> res = new ArrayList();
        if(chef != null && chef.getService() != null){
            res.add(chef.getService()); 
        }else{
            res = serviceService.getFreeService();
        }
        
        return res;
    }
    

    public void onEdit(RowEditEvent event) {
        Employe editedEmploye = (Employe) event.getObject();

        // Retrieve the persisted employe from the database
        Employe persistedEmploye = employeService.getById(editedEmploye.getId());

        // Update the relevant properties of the persisted employe
        persistedEmploye.setNom(editedEmploye.getNom());
        persistedEmploye.setPrenom(editedEmploye.getPrenom());
        persistedEmploye.setDateNaissance(editedEmploye.getDateNaissance());

        // Retrieve the persisted service
//        Service persistedService = serviceService.getById(this.service.getId());

        // Update the service of the persisted employe
        persistedEmploye.setService(editedEmploye.getService());

        // Retrieve and set the persisted chef
//        Employe persistedChef = employeService.getById(editedEmploye.getChef().getId());
        persistedEmploye.setChef(editedEmploye.getChef());

        // Set collaborators to null
        persistedEmploye.setCollaborators(null);

        // Perform the update
        employeService.update(persistedEmploye);
        
        FacesMessage message = new FacesMessage(FacesMessage.FACES_MESSAGES, "Employee Updated!");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    
    public String onDeleteAction() {
        Employe employeToDelete = employeService.getById(employe.getId());
        
        if (employeToDelete != null) {
            System.out.println("employeToDelete" + employeToDelete.getNom());
            employeToDelete.setService(null);
//            employeToDelete.getCollaborators().size();
            employeToDelete.setCollaborators(null);
            employeToDelete.setChef(null);
//            Service s = employe.getService();
//            if (service != null) {
//                s.getEmployees().remove(employe);
//                employe.setService(null);
//            }
            employeService.deleteEmploye(employeToDelete);
            
            FacesMessage message = new FacesMessage(FacesMessage.FACES_MESSAGES, "Employee Deleted!");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
      
        return null;
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }
//
    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries employees = new ChartSeries();
        employees.setLabel("employes");
        model.setAnimate(true);
        for (Object[] m : employeService.nbEmploye()) {
            employees.set(m[1], Integer.parseInt(m[0].toString()));
        }
        model.addSeries(employees);

        return model;
    }

    
    public void handleFileUpload(FileUploadEvent event) {
        try {
            InputStream inputStream = event.getFile().getInputstream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            byte[] fileContent = outputStream.toByteArray();
            System.out.println("fileContent: " + Arrays.toString(fileContent));
            employe.setImage(fileContent);
            outputStream.close();
            inputStream.close();

            FacesMessage message = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (IOException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error uploading file");
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    // Add this method to get the image stream
    public ByteArrayInputStream getImageStream(byte[] imageBytes) {
        if (null != imageBytes) {
            ByteArrayInputStream b = new ByteArrayInputStream(imageBytes);
            int byteRead;
            while ((byteRead = b.read()) != -1) {
                System.out.print((char) byteRead);
            }
//            System.out.println("Image byte array length: " + b.read());
            return b;
        } else {
            System.out.println("Image bytes are null");
            return null;
        }

    }
    
    public String getImageBase64(byte[] imageBytes) {
    if (imageBytes != null && imageBytes.length > 0) {
        return Base64.getEncoder().encodeToString(imageBytes);
    }
    return "";
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
    
    
}
