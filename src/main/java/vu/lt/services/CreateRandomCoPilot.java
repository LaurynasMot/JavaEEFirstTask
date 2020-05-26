package vu.lt.services;

import lombok.Getter;
import lombok.Setter;
import org.h2.util.json.JSONArray;
import org.h2.util.json.JSONObject;
import vu.lt.entities.RacingClass;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.json.stream.JsonParser;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Named
@SessionScoped
@Getter
@Setter
public class CreateRandomCoPilot implements Serializable {
    @Inject
    NameGenerator nameGenerator;

    private List<String> allNames = new ArrayList<>();
    int count = 1;

    public String getRandomCoPilot() {
        allNames.clear();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        for (int a = 0; a < count; a++) {
            allNames.add(nameGenerator.generateFirstName() + " " + nameGenerator.generateLastName());
        }
        return "copilots?faces-redirect=true&classId=" + requestParameters.get("classId") + "&racerId=" + requestParameters.get("racerId");
    }

    public void clearList() {
        allNames.clear();
    }
}
