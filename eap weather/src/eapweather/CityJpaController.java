/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eapweather;

import eapweather.exceptions.IllegalOrphanException;
import eapweather.exceptions.NonexistentEntityException;
import eapweather.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author softa
 */
public class CityJpaController implements Serializable {

    public CityJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(City city) throws PreexistingEntityException, Exception {
        if (city.getCitydataList() == null) {
            city.setCitydataList(new ArrayList<Citydata>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Citydata> attachedCitydataList = new ArrayList<Citydata>();
            for (Citydata citydataListCitydataToAttach : city.getCitydataList()) {
                citydataListCitydataToAttach = em.getReference(citydataListCitydataToAttach.getClass(), citydataListCitydataToAttach.getId());
                attachedCitydataList.add(citydataListCitydataToAttach);
            }
            city.setCitydataList(attachedCitydataList);
            em.persist(city);
            for (Citydata citydataListCitydata : city.getCitydataList()) {
                City oldCitynameOfCitydataListCitydata = citydataListCitydata.getCityname();
                citydataListCitydata.setCityname(city);
                citydataListCitydata = em.merge(citydataListCitydata);
                if (oldCitynameOfCitydataListCitydata != null) {
                    oldCitynameOfCitydataListCitydata.getCitydataList().remove(citydataListCitydata);
                    oldCitynameOfCitydataListCitydata = em.merge(oldCitynameOfCitydataListCitydata);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCity(city.getAreaname()) != null) {
                throw new PreexistingEntityException("City " + city + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(City city) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            City persistentCity = em.find(City.class, city.getAreaname());
            List<Citydata> citydataListOld = persistentCity.getCitydataList();
            List<Citydata> citydataListNew = city.getCitydataList();
            List<String> illegalOrphanMessages = null;
            for (Citydata citydataListOldCitydata : citydataListOld) {
                if (!citydataListNew.contains(citydataListOldCitydata)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Citydata " + citydataListOldCitydata + " since its cityname field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Citydata> attachedCitydataListNew = new ArrayList<Citydata>();
            for (Citydata citydataListNewCitydataToAttach : citydataListNew) {
                citydataListNewCitydataToAttach = em.getReference(citydataListNewCitydataToAttach.getClass(), citydataListNewCitydataToAttach.getId());
                attachedCitydataListNew.add(citydataListNewCitydataToAttach);
            }
            citydataListNew = attachedCitydataListNew;
            city.setCitydataList(citydataListNew);
            city = em.merge(city);
            for (Citydata citydataListNewCitydata : citydataListNew) {
                if (!citydataListOld.contains(citydataListNewCitydata)) {
                    City oldCitynameOfCitydataListNewCitydata = citydataListNewCitydata.getCityname();
                    citydataListNewCitydata.setCityname(city);
                    citydataListNewCitydata = em.merge(citydataListNewCitydata);
                    if (oldCitynameOfCitydataListNewCitydata != null && !oldCitynameOfCitydataListNewCitydata.equals(city)) {
                        oldCitynameOfCitydataListNewCitydata.getCitydataList().remove(citydataListNewCitydata);
                        oldCitynameOfCitydataListNewCitydata = em.merge(oldCitynameOfCitydataListNewCitydata);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = city.getAreaname();
                if (findCity(id) == null) {
                    throw new NonexistentEntityException("The city with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            City city;
            try {
                city = em.getReference(City.class, id);
                city.getAreaname();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The city with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Citydata> citydataListOrphanCheck = city.getCitydataList();
            for (Citydata citydataListOrphanCheckCitydata : citydataListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This City (" + city + ") cannot be destroyed since the Citydata " + citydataListOrphanCheckCitydata + " in its citydataList field has a non-nullable cityname field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(city);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<City> findCityEntities() {
        return findCityEntities(true, -1, -1);
    }

    public List<City> findCityEntities(int maxResults, int firstResult) {
        return findCityEntities(false, maxResults, firstResult);
    }

    private List<City> findCityEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(City.class));
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

    public City findCity(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(City.class, id);
        } finally {
            em.close();
        }
    }

    public int getCityCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<City> rt = cq.from(City.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
