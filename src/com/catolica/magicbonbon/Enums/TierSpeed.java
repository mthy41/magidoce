package com.catolica.magicbonbon.Enums;

public enum TierSpeed {
    T0SPEED(1.0f),
    T1SPEED(0.80f),
    T2SPEED(0.60f),
    T3SPEED(0.40f),
    T4SPEED(0.20f);

    public final float value;
    
    private TierSpeed(float value){
        this.value = value;
    }
    
    public float getValue(){
        return value;
    }
}

