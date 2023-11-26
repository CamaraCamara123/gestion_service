/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

import java.util.List;
import ma.projet.beans.Utilisateur;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author Lenovo
 */
public class UtilisateurService implements IDao<Utilisateur>{

    @Override
    public boolean create(Utilisateur o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Utilisateur o) {
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Utilisateur o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Utilisateur getById(int id) {
        Utilisateur user  = null;
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user  = (Utilisateur) session.get(Utilisateur.class, id);
        session.getTransaction().commit();
        return user;
    }

    @Override
    public List<Utilisateur> getAll() {
        List <Utilisateur> user = null;
      
        Session session  = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        user  = session.createQuery("from Utilisateur").list();
        session.getTransaction().commit();
        return user;
    }
    
   public Utilisateur getByUtilisateurname(String username) {
    Utilisateur user = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    String hql = "from Utilisateur where username = :username";
    Query query = session.createQuery(hql).setParameter("username", username);
    user = (Utilisateur) query.uniqueResult();  // Use uniqueResult to get a single result
    session.getTransaction().commit();
    session.close();  // Close the session
    return user;
}


    
}
