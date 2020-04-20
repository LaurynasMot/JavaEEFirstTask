package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Racer;
import vu.lt.entities.RacingClass;
import vu.lt.persistence.ClassesDAO;
import vu.lt.persistence.RacersDAO;
import vu.lt.services.CreateRandomCoPilot;

@Model
public class RacersForClass implements Serializable{

    @Inject
    private ClassesDAO classesDAO;

    @Inject
    private RacersDAO racersDAO;

    @Getter @Setter
    private RacingClass racingClass;

    @Getter @Setter
    private Racer racerToCreate = new Racer();

    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer classId = Integer.parseInt(requestParameters.get("classId"));
        this.racingClass = classesDAO.findOne(classId);
    }

    @Transactional
    public String createRacer() {
        racerToCreate.setRacingClass(this.racingClass);
        racersDAO.persist(racerToCreate);
        return "racers?faces-redirect=true&classId=" + this.racingClass.getId();
    }
}
