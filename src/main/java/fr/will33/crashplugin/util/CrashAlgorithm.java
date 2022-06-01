package fr.will33.crashplugin.util;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class CrashAlgorithm {
    public static double even() {
        double tinyrandom, smallrandom, mediumrandom, largerandom, xlargerandom, anomalyrandom, chance[] = { 0.1D, 0.3D, 0.6D, 0.8D, 0.95D, 0.99D, 1.0D };
        double CrashNumber = 1.0D;
        int event = Arrays.binarySearch(chance, Math.random());
        if (event < 0)
            event = -event - 1;
        switch (event) {
            case 1:
                tinyrandom = ThreadLocalRandom.current().nextDouble(1.1D, 1.5D);
                CrashNumber = tinyrandom;
                break;
            case 2:
                smallrandom = ThreadLocalRandom.current().nextDouble(1.5D, 3.0D);
                CrashNumber = smallrandom;
                break;
            case 3:
                mediumrandom = ThreadLocalRandom.current().nextDouble(3.0D, 8.0D);
                CrashNumber = mediumrandom;
                break;
            case 4:
                largerandom = ThreadLocalRandom.current().nextDouble(8.0D, 15.0D);
                CrashNumber = largerandom;
                break;
            case 5:
                xlargerandom = ThreadLocalRandom.current().nextDouble(15.0D, 30.0D);
                CrashNumber = xlargerandom;
                break;
            case 6:
                anomalyrandom = ThreadLocalRandom.current().nextDouble(30.0D, 125.0D);
                CrashNumber = anomalyrandom;
                break;
        }
        return CrashNumber;
    }

    public static double downbad() {
        double tinyrandom, smallrandom, mediumrandom, largerandom, xlargerandom, anomalyrandom, chance[] = { 0.2D, 0.5D, 0.8D, 0.95D, 0.98D, 0.99D, 1.0D };
        double CrashNumber = 1.0D;
        int event = Arrays.binarySearch(chance, Math.random());
        if (event < 0)
            event = -event - 1;
        switch (event) {
            case 1:
                tinyrandom = ThreadLocalRandom.current().nextDouble(1.1D, 1.5D);
                CrashNumber = tinyrandom;
                break;
            case 2:
                smallrandom = ThreadLocalRandom.current().nextDouble(1.5D, 3.0D);
                CrashNumber = smallrandom;
                break;
            case 3:
                mediumrandom = ThreadLocalRandom.current().nextDouble(3.0D, 8.0D);
                CrashNumber = mediumrandom;
                break;
            case 4:
                largerandom = ThreadLocalRandom.current().nextDouble(8.0D, 15.0D);
                CrashNumber = largerandom;
                break;
            case 5:
                xlargerandom = ThreadLocalRandom.current().nextDouble(15.0D, 30.0D);
                CrashNumber = xlargerandom;
                break;
            case 6:
                anomalyrandom = ThreadLocalRandom.current().nextDouble(30.0D, 125.0D);
                CrashNumber = anomalyrandom;
                break;
        }
        return CrashNumber;
    }

    public static double downhorrendous() {
        double tinyrandom, smallrandom, mediumrandom, largerandom, xlargerandom, anomalyrandom, chance[] = { 0.3D, 0.6D, 0.9D, 0.95D, 0.98D, 0.99D, 1.0D };
        double CrashNumber = 1.0D;
        int event = Arrays.binarySearch(chance, Math.random());
        if (event < 0)
            event = -event - 1;
        switch (event) {
            case 1:
                tinyrandom = ThreadLocalRandom.current().nextDouble(1.1D, 1.5D);
                CrashNumber = tinyrandom;
                break;
            case 2:
                smallrandom = ThreadLocalRandom.current().nextDouble(1.5D, 3.0D);
                CrashNumber = smallrandom;
                break;
            case 3:
                mediumrandom = ThreadLocalRandom.current().nextDouble(3.0D, 8.0D);
                CrashNumber = mediumrandom;
                break;
            case 4:
                largerandom = ThreadLocalRandom.current().nextDouble(8.0D, 15.0D);
                CrashNumber = largerandom;
                break;
            case 5:
                xlargerandom = ThreadLocalRandom.current().nextDouble(15.0D, 30.0D);
                CrashNumber = xlargerandom;
                break;
            case 6:
                anomalyrandom = ThreadLocalRandom.current().nextDouble(30.0D, 125.0D);
                CrashNumber = anomalyrandom;
                break;
        }
        return CrashNumber;
    }
}
