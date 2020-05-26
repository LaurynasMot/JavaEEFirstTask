package vu.lt.services;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Default;
import java.io.Serializable;
import java.util.Random;

@SessionScoped
public class NameGenerator implements Serializable {
    String[] name = {"Jonas", "Petras", "Juozas", "Antanas"};
    String[] lastName = {"Jonaitis", "Petraitis", "Juozaitis", "Antanaitis"};


    public String generateFirstName() {
        Random generate = new Random();
        return name[generate.nextInt(name.length)];
    }

    public String generateLastName() {
        Random generate = new Random();
        return lastName[generate.nextInt(lastName.length)];
    }
}
