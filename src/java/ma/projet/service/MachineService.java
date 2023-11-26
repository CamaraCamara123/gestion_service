/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Machine;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LACHGAR
 */
public class MachineService implements IDao<Machine>{

    @Override
    public boolean create(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Machine o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Machine getById(int id) {
        Machine machine  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machine  = (Machine) session.get(Machine.class, id);
        session.getTransaction().commit();
        return machine;
    }

    @Override
    public List<Machine> getAll() {
        List <Machine> machines = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machines  = session.createQuery("from Machine").list();
        session.getTransaction().commit();
        return machines;
    }
    
    
    public List<Object[]> nbmachine(){
        List<Object[]> machines = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        machines  = session.createQuery("select count(m.marque), m.marque from Machine m group by m.marque").list();
        session.getTransaction().commit();
        return machines;
    }
    
    public List<Machine> getbydates(Date d1 , Date d2){
        List <Machine> machines = new ArrayList<>();
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
         machines  = session.createQuery("from Machine m where m.dateAchat between :d1 and :d2").setParameter("d1", d1).setParameter("d2", d2).list();
        session.getTransaction().commit();
        return machines;
        
    }
    
}
