package vu.lt.persistence;

import vu.lt.entities.CoPilot;
import vu.lt.entities.keys.CoPilotKey;

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

    public void saveOrUpdate(CoPilot coPilot){
        this.em.merge(coPilot);
    }

    public CoPilot findCoPilot(CoPilotKey coPilotKey){
        return this.em.find(CoPilot.class,coPilotKey);
    }
}
