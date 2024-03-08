/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eapweather;

import eapweather.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author softa
 */
public class CitydataJpaController implements Serializable {

    public CitydataJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Citydata citydata) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            City cityname = citydata.getCityname();
            if (cityname != null) {
                cityname = em.getReference(cityname.getClass(), cityname.getAreaname());
                citydata.setCityname(cityname);
            }
            em.persist(citydata);
            if (cityname != null) {
                cityname.getCitydataList().add(citydata);
                cityname = em.merge(cityname);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Citydata citydata) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citydata persistentCitydata = em.find(Citydata.class, citydata.getId());
            City citynameOld = persistentCitydata.getCityname();
            City citynameNew = citydata.getCityname();
            if (citynameNew != null) {
                citynameNew = em.getReference(citynameNew.getClass(), citynameNew.getAreaname());
                citydata.setCityname(citynameNew);
            }
            citydata = em.merge(citydata);
            if (citynameOld != null && !citynameOld.equals(citynameNew)) {
                citynameOld.getCitydataList().remove(citydata);
                citynameOld = em.merge(citynameOld);
            }
            if (citynameNew != null && !citynameNew.equals(citynameOld)) {
                citynameNew.getCitydataList().add(citydata);
                citynameNew = em.merge(citynameNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = citydata.getId();
                if (findCitydata(id) == null) {
                    throw new NonexistentEntityException("The citydata with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Citydata citydata;
            try {
                citydata = em.getReference(Citydata.class, id);
                citydata.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The citydata with id " + id + " no longer exists.", enfe);
            }
            City cityname = citydata.getCityname();
            if (cityname != null) {
                cityname.getCitydataList().remove(citydata);
                cityname = em.merge(cityname);
            }
            em.remove(citydata);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Citydata> findCitydataEntities() {
        return findCitydataEntities(true, -1, -1);
    }

    public List<Citydata> findCitydataEntities(int maxResults, int firstResult) {
        return findCitydataEntities(false, maxResults, firstResult);
    }

    private List<Citydata> findCitydataEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Citydata.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Citydata findCitydata(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Citydata.class, id);
        } finally {
            em.close();
        }
    }

    public int getCitydataCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Citydata> rt = cq.from(Citydata.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
