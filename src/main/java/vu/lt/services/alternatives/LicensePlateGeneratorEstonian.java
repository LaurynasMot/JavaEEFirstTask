package vu.lt.services.alternatives;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.deltaspike.core.api.future.Futureable;
import vu.lt.services.LicensePlateGenerator;

import javax.ejb.AsyncResult;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Future;

@Alternative
@ApplicationScoped
public class LicensePlateGeneratorEstonian implements Serializable, LicensePlateGenerator {

    @Futureable
    public Future<String> generateLicensePlate() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {

        }
        final String generatedLicensePlate = (new Random().nextInt(899) + 100)
                + RandomStringUtils.randomAlphabetic(3).toUpperCase();
        return new AsyncResult<>(generatedLicensePlate);
    }
}
