package main.java.app;

import java.util.Random;

public class RandomGenerator {
    public static final String CHARACTERS = "abcdefghijklmnopqrstuvwxyz";
    public static final int LENGTH = CHARACTERS.length();
    public static Random random = new Random();

//    public RandomGenerator() {
//        this.random = new Random();
//    }
    public static String getRandomWorld(int origin, int bound){
        int length = random.nextInt(origin,bound);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int idx = random.nextInt(LENGTH);
            char ch = CHARACTERS.charAt(idx);
            sb.append(ch);
        }
        return sb.toString();
    }
}
