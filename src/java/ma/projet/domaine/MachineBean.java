/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.domaine;

import java.util.Date;
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
@ManagedBean(name = "machineBean")
public class MachineBean {

    private Machine machine;

    private Salle salle;
    private List<Machine> machines;
    private List<Machine> machinesBetweenDates;
    private MachineService machineService;
    private SalleService salleService;
    private static ChartModel barModel;
    private Date date1;
    private Date date2;

    public MachineBean() {
        machine = new Machine();
        machineService = new MachineService();
        salleService = new SalleService();

    }

    public List<Machine> getMachines() {
        if (machines == null) {
            machines = machineService.getAll();
        }
        return machines;
    }

    public void setMachines(List<Machine> machines) {
        this.machines = machines;
    }

    public MachineService getMachineService() {
        return machineService;
    }

    public void setMachineService(MachineService machineService) {
        this.machineService = machineService;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public Date getDate1() {
        return date1;
    }

    public void setDate1(Date date1) {
        this.date1 = date1;
    }

    public Date getDate2() {
        return date2;
    }

    public void setDate2(Date date2) {
        this.date2 = date2;
    }

    public String onCreateAction() {
        machineService.create(machine);
        machine = new Machine();
        return null;
    }

    public String onDeleteAction() {

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

    public void load() {
        System.out.println(salle.getLibelle());
        salle = salleService.getById(salle.getId());
        getMachines();
    }

    public void onEdit(RowEditEvent event) {
        machine = (Machine) event.getObject();
        Salle salle = salleService.getById(this.machine.getSalle().getId());
        machine.setSalle(salle);
        machine.getSalle().setLibelle(salle.getLibelle());
        machineService.update(machine);
    }

    public void onCancel(RowEditEvent event) {
    }

    public ChartModel getBarModel() {
        return barModel;
    }

    public ChartModel initBarModel() {
        CartesianChartModel model = new CartesianChartModel();
        ChartSeries machines = new ChartSeries();
        machines.setLabel("machines");
        model.setAnimate(true);
        for (Object[] m : machineService.nbmachine()) {
            machines.set(m[1], Integer.parseInt(m[0].toString()));
        }
        model.addSeries(machines);

        return model;
    }

    public List<Machine> machineLoad() {
        machinesBetweenDates = machineService.getbydates(date1, date2);
        return null;

    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public List<Machine> getMachinesBetweenDates() {
        return machinesBetweenDates;
    }

    public void setMachinesBetweenDates(List<Machine> machinesBetweenDates) {
        this.machinesBetweenDates = machinesBetweenDates;
    }

}
