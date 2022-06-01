package fr.will33.crashplugin.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CrashAlgorithm {
    public static final List<Double> value;

    static{
        List<Double> v = new ArrayList<>();
        for(int i = 0; i < 500; i ++)
            v.add(((double) i / 10));
        value = Collections.unmodifiableList(v);
    }

    public static double random(){
        Random random = new Random();
        return value.get(random.nextInt(value.size()));
    }
}
