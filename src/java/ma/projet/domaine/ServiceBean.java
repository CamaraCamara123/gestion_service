/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.service.ServiceService;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Lenovo
 */
@ManagedBean(name = "serviceBean")
public class ServiceBean {
    private Service service;
    private ServiceService ss;
    private List<Service> services;

    public ServiceBean() {
        this.service = new Service();
        this.ss = new ServiceService();
    }
    
    public String onCreateAction() {
        ss.create(service);
        service  = new Service();
        return null;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        if (services == null) {
            services = ss.getAll();
        }
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }
    
    public void onDeleteAction() {
        ss.delete(service);
    }

    public void onEdit(RowEditEvent event) {
        service = (Service) event.getObject();
        ss.update(service);
    }

    public void load() {
        System.out.println(service.getNom());
        service = ss.getById(service.getId());
    }

    public void onCancel(RowEditEvent event) {
    }
    
    

//    public void onEditm(RowEditEvent event) {
//        machine = (Machine) event.getObject();
//        Salle salle = salleService.getById(this.machine.getSalle().getId());
//        machine.setSalle(salle);
//        machine.getSalle().setLibelle(salle.getLibelle());
//        machineService.update(machine);
//    }

//    public String onDeleteActionm() {
//
//        machineService.delete(machineService.getById(machine.getId()));
//        return null;
//    }

//    public List<Machine> salleLoad() {
//        for (Machine m : machineService.getAll()) {
//            if (m.getSalle().equals(salle)) {
//                machines.add(m);
//            }
//        }
//        return machines;
//
//    }
//
//    public void onCancelm(RowEditEvent event) {
//    }

    
}
