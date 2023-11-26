/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ma.projet.service;

import java.util.List;
import ma.projet.beans.Salle;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;

/**
 *
 * @author LACHGAR
 */
public class SalleService implements IDao<Salle>{
    @Override
    public boolean create(Salle o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean update(Salle o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }
    @Override
    public boolean delete(Salle o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Salle getById(int id) {
        Salle salle  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        salle  = (Salle) session.get(Salle.class, id);
        session.getTransaction().commit();
        return salle;
    }

    @Override
    public List<Salle> getAll() {
        
         List <Salle> salles = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        salles  = session.createQuery("from Salle").list();
        session.getTransaction().commit();
        return salles;
    }
    
}
