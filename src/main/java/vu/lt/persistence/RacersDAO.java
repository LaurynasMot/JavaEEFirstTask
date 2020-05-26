package vu.lt.persistence;

import vu.lt.entities.Racer;
import vu.lt.entities.RacingClass;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ApplicationScoped
public class RacersDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(Racer racer){
        this.em.persist(racer);
    }

    public Racer findOne(Integer id) {
        return em.find(Racer.class, id);
    }

    public Racer update(Racer racer){
        return em.merge(racer);
    }
}
