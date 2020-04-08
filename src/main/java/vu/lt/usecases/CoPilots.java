package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import vu.lt.entities.CoPilot;
import vu.lt.entities.Racer;
import vu.lt.entities.RacingClass;
import vu.lt.entities.keys.CoPilotKey;
import vu.lt.persistence.ClassesDAO;
import vu.lt.persistence.CoPilotsDAO;
import vu.lt.persistence.RacersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
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

    @Getter @Setter
    private CoPilotKey coPilotKey = new CoPilotKey();

    @Getter @Setter
    private CoPilot currentCoPilot;

    @PostConstruct
    public void init(){
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        Integer racerId = Integer.parseInt(requestParameters.get("racerId"));

        this.racingClass = classesDAO.findOne(classId);
        this.racer = racersDAO.findOne(racerId);

        coPilotKey.setRacerId(this.racer.getId());
        coPilotKey.setRacingClassId(this.racingClass.getId());
        findCoPilot();
        coPilotToCreate.setId(coPilotKey);
    }

    private void findCoPilot() {
        this.currentCoPilot = coPilotsDAO.findCoPilot(coPilotKey);
    }

    @Transactional
    public String createCoPilot() {
        this.coPilotToCreate.setRacer(this.racer);
        this.coPilotToCreate.setRacingClass(this.racingClass);
        this.coPilotsDAO.saveOrUpdate(coPilotToCreate);
        return "copilots?faces-redirect=true&classId="+this.racingClass.getId()+"&racerId="+this.racer.getId();
    }
}
