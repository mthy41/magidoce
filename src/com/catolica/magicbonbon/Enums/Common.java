package com.catolica.magicbonbon.Enums;

public enum Common {
    SCREEN_WIDTH(800),
    SCREEN_HEIGHT(450),
    FRAME_RATE(60);

    public final int value;

    private Common(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
