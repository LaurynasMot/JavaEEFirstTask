package vu.lt.decorators;

import javax.enterprise.inject.Default;

@Default
public class HomePageImpl implements HomePage {
    @Override
    public String homePageMessage() {
        return "Hello. Current date is - ";
    }
}