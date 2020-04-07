package vu.lt.persistence;

import vu.lt.entities.Racer;

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
}
