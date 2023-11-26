
import java.util.Date;
import ma.projet.beans.Machine;
import ma.projet.beans.Salle;
import ma.projet.service.MachineService;
import ma.projet.service.SalleService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LACHGAR
 */
public class Test1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MachineService ms = new MachineService();
        SalleService ss = new SalleService();
        //Création des machines
        ms.create(new Machine("HP", "HZ23", 5000, new Date(), ss.getById(1)));
        ms.create(new Machine("HP", "HZ77", 4500, new Date(), ss.getById(1)));
        ms.create(new Machine("TOSHIBA", "AZ34", 6000, new Date(), ss.getById(2)));
        //Afficher les machines par salle
        for(Salle s : ss.getAll()){
            System.out.println("Salle  : "+s.getCode()+" : ");
            for(Machine m : s.getMachines())
                System.out.println("\t"+m.getReference());
        }
    }
}
