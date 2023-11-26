/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

/**
 *
 * @author HP
 */


import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EmployeService extends AbstractFacade<Employe> {


    public EmployeService() {
        super(Employe.class);
    }
    
    public List<Object[]> nbEmploye(){
        List<Object[]> employees = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        employees  = session.createQuery("select count(e.nom), s.nom from Employe e, Service s "
                + "where e.service = s.id group by s.nom").list();
        session.getTransaction().commit();
        return employees;
    }
    
    public void deleteEmploye(Employe employe) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    Transaction tx = null;

    try {
        tx = session.beginTransaction();
        
        // Merge the detached employe into the session
        Employe mergedEmploye = (Employe) session.merge(employe);
        
       
        // Delete the employe
        session.delete(mergedEmploye);
        
        tx.commit();
    } catch (HibernateException e) {
        if (tx != null) tx.rollback();
        e.printStackTrace();
    } finally {
        session.close();
    }
}


}
