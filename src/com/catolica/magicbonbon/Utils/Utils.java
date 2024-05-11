package com.catolica.magicbonbon.Utils;
import java.util.Random;
public class Utils {
    public static int randSign(){
        Random rand = new Random();
        int randValue = rand.nextInt(2);
        if (randValue == 1){
            return -1;
        }
        return 1;
    }

    public static int RandIndex(int index){
        Random rand = new Random();
        return rand.nextInt(index);
    }
}
