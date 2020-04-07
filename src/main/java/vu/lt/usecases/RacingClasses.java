package vu.lt.usecases;
import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.RacingClass;
import vu.lt.persistence.ClassesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
@Model
public class RacingClasses {
    @Inject
    private ClassesDAO classesDAO;

    @Getter @Setter
    private RacingClass classToCreate = new RacingClass();

    @Getter
    private List<RacingClass> allClasses;

    @PostConstruct
    public void init(){
        loadAllClasses();
    }

    @Transactional
    public String createClass(){
        this.classesDAO.persist(classToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllClasses(){
        this.allClasses = classesDAO.loadAll();
    }
}
