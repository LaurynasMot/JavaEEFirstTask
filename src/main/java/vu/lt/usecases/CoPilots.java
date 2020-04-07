package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.CoPilot;
import vu.lt.entities.Racer;
import vu.lt.entities.RacingClass;
import vu.lt.persistence.ClassesDAO;
import vu.lt.persistence.CoPilotsDAO;
import vu.lt.persistence.RacersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class CoPilots {

    @Inject
    private ClassesDAO classesDAO;

    @Inject
    private RacersDAO racersDAO;

    @Inject
    private CoPilotsDAO coPilotsDAO;

    @Getter @Setter
    private RacingClass racingClass;

    @Getter @Setter
    private Racer racer;

    @Getter @Setter
    private CoPilot coPilotToCreate = new CoPilot();

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        Integer racerId = Integer.parseInt(requestParameters.get("racerId"));
        this.racingClass = classesDAO.findOne(classId);
        this.racer = racersDAO.findOne(racerId);
    }

    @Transactional
    public String createCoPilot() {
        coPilotToCreate.setRacer(this.racer);
        coPilotToCreate.setRacingClass(this.racingClass);
        coPilotsDAO.persist(coPilotToCreate);
        return "racers?faces-redirect=true";
        //maybe return "racers?faces-redirect=true&classId=" + this.racingClass.getId();
    }
}
