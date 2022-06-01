package fr.will33.crashplugin.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CrashAlgorithm {
    public static double random(){
        List<Double> value = new ArrayList<>();
        for(int i = 0; i < 500; i ++)
            value.add(((double) i / 10));
        Random random = new Random();
        return value.get(random.nextInt(value.size()));
    }
}
