package vu.lt.usecases;

import vu.lt.interceptors.LoggedInvocation;
import vu.lt.services.LicensePlateGenerator;
import vu.lt.services.LicensePlateGeneratorImpl;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@SessionScoped
@Named
public class GenerateLicensePlate implements Serializable {
    @Inject
    LicensePlateGenerator licensePlateGenerator;

    private Future<String> licensePlateGenerationTask = null;

    @LoggedInvocation
    public String generateNewLicensePlate() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        licensePlateGenerationTask = licensePlateGenerator.generateLicensePlate();
        return "/racerDetails.xhtml?faces-redirect=true&racerId=" + requestParameters.get("racerId");
    }

    public boolean isLicensePlateGenerationRunning() {
        return licensePlateGenerationTask != null && !licensePlateGenerationTask.isDone();
    }

    public String getLicensePlateGenerationStatus() throws ExecutionException, InterruptedException {
        if (licensePlateGenerationTask == null) {
            return null;
        } else if (isLicensePlateGenerationRunning()) {
            return "License plate generation is in progress";
        }
        return "Suggested license plate: " + licensePlateGenerationTask.get();
    }
}
