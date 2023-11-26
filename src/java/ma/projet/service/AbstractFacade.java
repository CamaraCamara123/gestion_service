/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;


import ma.projet.dao.IDao;
import org.hibernate.Session;
import java.util.List;
import ma.projet.util.HibernateUtil;
import org.hibernate.Transaction;


public abstract class AbstractFacade<T> implements IDao<T> {

    private Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public boolean create(T o) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(o);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(T o) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(o);
            session.getTransaction().commit();
            session.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace(); 
            return false;
        }
    }

    @Override
    public boolean delete(T o) {
        Session session = null;
        Transaction tx = null;
        try {
           
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(o);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();  
            return false;
        }finally {
            if(session != null){
                session.close();
            }   
        }
    }

 
     @Override
    public T getById(int id) {
        T entity = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entity = (T) session.get(entityClass, id);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }
    
    @Override
    public List<T> getAll() {
        List<T> entities = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entities = session.createQuery("from " + entityClass.getSimpleName()).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entities;
    }
    
}



