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


import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import ma.projet.beans.Employe;
import ma.projet.beans.Service;
import ma.projet.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

public class ServiceService extends AbstractFacade<Service> {

    public ServiceService() {
        super(Service.class);
    }
    
     public List<Employe> getEmployeesByService(Service service) {
      
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            String hql = "SELECT e FROM Employe e WHERE e.service = :service";
            Query query = session.createQuery(hql);
            query.setParameter("service", service);
            query.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
            return query.list();
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Service> getFreeService() {
        List<Service> services = new ArrayList<>();
        try{
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
//            String hql = "SELECT s FROM Service s LEFT JOIN s.employes e WHERE e.id IS NULL";
            String hql = "SELECT s FROM Employe e RIGHT JOIN e.service s WHERE e.id IS NULL";

            services = session.createQuery(hql).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }

}

