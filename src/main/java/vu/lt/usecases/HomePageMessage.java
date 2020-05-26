package vu.lt.usecases;


import vu.lt.decorators.HomePage;
import vu.lt.decorators.HomePageImpl;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

@Model
public class HomePageMessage {

    @Inject
    HomePageImpl homePage;

    public String homePageMessage() {
        return homePage.homePageMessage();
    }
}
