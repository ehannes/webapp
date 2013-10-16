package com.adde.webbapp.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hajo
 */
public abstract class AbstractDAO<T, K> {

    private EntityManagerFactory emf;
    private final Class<T> clazz;
    private final String PU = "webapp_pu";
    
    protected AbstractDAO (Class<T> clazz) {
        this.clazz = clazz;
        emf = Persistence.createEntityManagerFactory(PU);
    }
    
    protected EntityManager getEntityManager(){
        EntityManager em = emf.createEntityManager();
        return em;
    }
    
    public void add(T t) {
        EntityManager em = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        } catch(Exception ex){
            Logger.getAnonymousLogger().log(Level.INFO, "Exception type: {0}", ex.getClass().getName());
            Logger.getAnonymousLogger().log(Level.INFO, "this is {0}, {1}, ", new Object[]{this.getClass().toString(), this.getClass().getCanonicalName(), this.getClass().getName()});
            Logger.getAnonymousLogger().log(Level.INFO, "Error occurred in {0}.add : "
                    + ex.getMessage(), this.getClass().getName());
            System.exit(1);
        } finally{
            if(em != null){
                em.close();
            }
        }
    }

    public void remove(K id) {
        EntityManager em = null;
        try{
        em = getEntityManager();
        em.getTransaction().begin();
        T t = em.getReference(clazz, id);
        em.remove(t);
        em.getTransaction().commit();
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.INFO, "Error occurred in {0}.remove...\n"
                    + e.getMessage(), this.getClass().getName());
            System.exit(1);
        } finally{
            if(em != null){
                em.close();
            }
        }
    }

    public T update(T t) {
        EntityManager em = null;
        T tmp = null;
        try{
            em = getEntityManager();
            em.getTransaction().begin();
            tmp = em.merge(t);
            em.getTransaction().commit();
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.INFO, "Error occurred in {0}.update...\n"
                    + e.getMessage(), this.getClass().getName());
            System.exit(1);
        } finally{
            if(em != null){
                em.close();
            }
        }
        return tmp;
    }

    public T find(K id) {
        EntityManager em = getEntityManager();
        T t = em.find(clazz, id);
        return t;
    }
    
    public List<T> getAll(){
        return getRange(0,getCount());
    }

    public List<T> getRange(int first, int nItems) {
        EntityManager em = getEntityManager();
        List<T> found = new ArrayList<>();
        try{
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(clazz));
            Query q = em.createQuery(cq);
            q.setFirstResult(first);
            q.setMaxResults(nItems);
            found.addAll(q.getResultList());
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.INFO, "Error occurred in {0}.getRange...\n"
                    + e.getMessage(), this.getClass().getName());
            System.exit(1);
        } finally{
            em.close();
        }
        return found;
    }

    public int getCount() {
        EntityManager em = null;
        int count = -1;
        try{
            em = getEntityManager();
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<T> rt = cq.from(clazz);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            count = ((Long)q.getSingleResult()).intValue();
        } catch(Exception e){
            Logger.getAnonymousLogger().log(Level.INFO, "Error occurred in {0}.getCount...\n"
                    + e.getMessage(), this.getClass().getName());
            System.exit(1);
        } finally{
            if(em != null){
                em.close();
            }
        }
        return count;
    }
}