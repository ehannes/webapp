package com.adde.webbapp.model.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 * This class is extended by all other DAO classes (called Catalogues in our
 * case). This is a very important class since we often choose not to Override
 * its methods in the subclasses when this implementation does the trick, and
 * sometimes we do some processing in an implementation in the subclass and
 * then call the method in this class.
 * 
 * @author ehannes
 */
public abstract class AbstractDAO<T, K> {

    private final Class<T> clazz;
    private final String PU = "webapp_pu";

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    protected EntityManager getEntityManager() {
        return EMFUtil.createEntityManager();
    }
    
    public void add(T t) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                em.persist(t);
                transaction.commit();
            } catch (Exception ex) {
                Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                        + " trying to get persist in AbstractDAO.add().");
            } finally {
                if (transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                    + " trying to get transaction or entityManager in AbstractDAO.add().");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void remove(K id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try {
                transaction.begin();
                T t = em.getReference(clazz, id);
                em.remove(t);
                transaction.commit();
            } catch (Exception e) {
                Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                        + " trying to get remove in AbstractDAO.remove().");
            } finally{
                if(transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception ex) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                    + " trying to get transaction or entityManager in AbstractDAO.remove().");
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public T update(T t) {
        EntityManager em = null;
        T tmp = null;
        try{
            em = getEntityManager();
            EntityTransaction transaction = em.getTransaction();
            try{
                em = getEntityManager();
                em.getTransaction().begin();
                tmp = em.merge(t);
                em.getTransaction().commit();
            } catch (Exception e) {
                Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                        + " trying to get merge in AbstractDAO.update().");
            } finally{
                if(transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                    + " trying to get transaction or entityManager in AbstractDAO.update().");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return tmp;
    }

    public T find(K id) {
        EntityManager em = null;
        T t = null;
        try {
            em = getEntityManager();
            t = em.find(clazz, id);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured when"
                        + " trying to get find or entityManager in AbstractDAO.find()");
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return t;
    }

    public List<T> getAll() {
        return getRange(0, getCount());
    }

    public List<T> getRange(int first, int nItems) {
        EntityManager em = null;
        List<T> found = new ArrayList<>();
        try {
            em = getEntityManager();
            try{
                CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                cq.select(cq.from(clazz));
                Query q = em.createQuery(cq);
                q.setFirstResult(first);
                q.setMaxResults(nItems);
                found.addAll(q.getResultList());
            } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured" +
                    " in AbstractDAO.getRange() {0}", e.getMessage());
            }
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured"
                    + " in AbstractDAO.getRange() when trying to "
                    + "get entityManager {0}", e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return found;
    }

    public int getCount() {
        EntityManager em = null;
        int count = -1;
        try {
            em = getEntityManager();
            try{
                CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
                Root<T> rt = cq.from(clazz);
                cq.select(em.getCriteriaBuilder().count(rt));
                Query q = em.createQuery(cq);
                count = ((Long) q.getSingleResult()).intValue();
            } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured" +
                    " in AbstractDAO.getCount() {0}", e.getMessage());
            }
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.INFO, "Exception occured"
                    + " in AbstractDAO.getCount() when trying " +
                    "to get entityManager {0}", e.getMessage());
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return count;
    }
}