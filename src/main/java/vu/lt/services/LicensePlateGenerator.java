package vu.lt.services;

import java.util.concurrent.Future;

public interface LicensePlateGenerator {
    Future<String> generateLicensePlate();
}
