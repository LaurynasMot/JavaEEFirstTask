package vu.lt.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.deltaspike.core.api.future.Futureable;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@ApplicationScoped
public class LicensePlateGeneratorImpl implements Serializable, LicensePlateGenerator {

    @Futureable
    public Future<String> generateLicensePlate() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        final String generatedLicensePlate = RandomStringUtils.randomAlphabetic(3).toUpperCase()
                + (new Random().nextInt(899) + 100);
        return new AsyncResult<>(generatedLicensePlate);
    }
}
