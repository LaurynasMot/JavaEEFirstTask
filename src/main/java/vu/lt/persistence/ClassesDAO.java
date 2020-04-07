package vu.lt.persistence;

import vu.lt.entities.RacingClass;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@ApplicationScoped
public class ClassesDAO {
    @PersistenceContext
    private EntityManager em;

    public List<RacingClass> loadAll() {
        return em.createNamedQuery("RacingClass.findAll", RacingClass.class).getResultList();
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    public void persist(RacingClass racingClass){
        this.em.persist(racingClass);
    }

    public RacingClass findOne(Integer id) { return em.find(RacingClass.class, id); }
}
