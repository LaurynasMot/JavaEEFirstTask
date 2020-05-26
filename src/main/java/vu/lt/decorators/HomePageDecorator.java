package vu.lt.decorators;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public class HomePageDecorator implements HomePage {

    @Inject
    @Delegate
    @Any
    HomePageImpl homePage;

    @Override
    public String homePageMessage() {
        return homePage.homePageMessage() + java.time.LocalDate.now();
    }
}
