
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.beans.Salle;
import ma.projet.beans.Service;
import ma.projet.service.EmployeService;
import ma.projet.service.MachineService;
import ma.projet.service.SalleService;
import ma.projet.service.ServiceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author LACHGAR
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        SalleService ss = new SalleService();
//        //création des Salles
//        ss.create(new Salle("I", "Informatique"));
//        ss.create(new Salle("A", "Arabe"));
//        ss.create(new Salle("C", "Comptailité"));
//        //La liste des salles
//        for (Salle s : ss.getAll()) {
//            System.out.println("" + s.getCode());
//        }
        
//        ServiceService ss = new ServiceService();
//        //création des Salles
//        ss.create(new Service("servie 1"));
//        ss.create(new Service("servie 2"));
//        //La liste des salles
//        for (Service s : ss.getAll()) {
//            System.out.println("" + s.getNom());
//        }
        
        EmployeService es = new EmployeService();
        ServiceService ss = new ServiceService();
        
        Service s = ss.getById(1);
        Employe e = es.getById(2);
        List<Employe> employes = ss.getEmployeesByService(s);
        System.out.println("Service: " + s.getNom() );
        System.out.println("Chef de Service: " + employes.get(0).getNom() + " " + employes.get(0).getPrenom());
        System.out.println("Collaborateurs sous la responsabilité d'Ahmed El Amrani: ");
        for(int i = 1; i < employes.size(); i++){
            System.out.println( i + ". " + employes.get(i).getNom() + " " + employes.get(i).getPrenom());
       
        }
        
        
        System.out.println(ss.getFreeService());
        
    }
}
