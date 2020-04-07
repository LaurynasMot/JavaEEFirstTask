package vu.lt.persistence;

import vu.lt.entities.CoPilot;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ApplicationScoped
public class CoPilotsDAO {
    @PersistenceContext
    private EntityManager em;

    public void persist(CoPilot coPilot){
        this.em.persist(coPilot);
    }
}
