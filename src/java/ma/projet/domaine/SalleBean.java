/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.List;
import javax.faces.bean.ManagedBean;
import ma.projet.beans.Machine;
import ma.projet.beans.Salle;
import ma.projet.service.MachineService;
import ma.projet.service.SalleService;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 *
 * @author LACHGAR
 */
@ManagedBean(name = "salleBean")
public class SalleBean {

    private Machine machine;
    private Salle salle;
    private SalleService salleService;
    private List<Salle> salles;
    private List<Machine> machines;
    private MachineService machineService;
    private static ChartModel barModel;

    public SalleBean() {
        salle = new Salle();
        salleService = new SalleService();
        machine = new Machine();
        machineService = new MachineService();
    }

    public List<Machine> getMachines() {
        if (machines == null) {
            machines = salle.getMachines();
        }
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public List<Salle> getSalles() {
        if (salles == null) {
            salles = salleService.getAll();
        }
        return salles;
    }

    public void setSalles(List<Salle> salles) {
        this.salles = salles;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public String onCreateAction() {
        salleService.create(salle);
        salle  = new Salle();
        return null;
    }

    public void onDeleteAction() {
        salle.setMachines(null);
        salleService.delete(salle);

    }

    public void onEdit(RowEditEvent event) {
        salle = (Salle) event.getObject();
        salle.setMachines(null);
        salleService.update(salle);
    }

    public void load() {
        System.out.println(salle.getLibelle());
        salle = salleService.getById(salle.getId());
        getMachines();
    }

    public void onCancel(RowEditEvent event) {
    }

    public void onEditm(RowEditEvent event) {
        machine = (Machine) event.getObject();
        Salle salle = salleService.getById(this.machine.getSalle().getId());
        machine.setSalle(salle);
        machine.getSalle().setLibelle(salle.getLibelle());
        machineService.update(machine);
    }

    public String onDeleteActionm() {

        machineService.delete(machineService.getById(machine.getId()));
        return null;
    }

    public List<Machine> salleLoad() {
        for (Machine m : machineService.getAll()) {
            if (m.getSalle().equals(salle)) {
                machines.add(m);
            }
        }
        return machines;

    }

    public void onCancelm(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries salles = new ChartSeries();
        salles.setLabel("Salles");
        model.setAnimate(true);
        for (Salle s : salleService.getAll()) {
            salles.set(s.getLibelle(), s.getMachines().size());
        }
        model.addSeries(salles);
        
                
        return model;
    }
}
