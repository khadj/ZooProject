package project.helper;

import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {
    public static int getNumber(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }
}
