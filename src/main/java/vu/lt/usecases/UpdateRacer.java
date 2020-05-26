package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Racer;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.RacersDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter
@Setter
public class UpdateRacer implements Serializable {
    private Racer racer;

    @Inject
    private RacersDAO racersDAO;

    @PostConstruct
    private void init() {
        System.out.println("UPDATE RACER INIT CALLED");
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer racerId = Integer.parseInt(requestParameters.get("racerId"));
        this.racer = racersDAO.findOne(racerId);
    }

    @LoggedInvocation
    @Transactional
    public String updateLicensePlate() {
        try {
            racersDAO.update(this.racer);
        } catch (OptimisticLockException e) {
            return "/racerDetails.xhtml?faces-redirect=true&racerId="
                    + this.racer.getId() +
                    "&error=optimistic-lock-exception";
        }
        return "racers.xhtml?classId=" + this.racer.getRacingClass().getId() + "&faces-redirect=true";
    }
}
