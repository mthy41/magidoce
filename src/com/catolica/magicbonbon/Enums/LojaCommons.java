package com.catolica.magicbonbon.Enums;

public enum LojaCommons {
    LOJASCROLLMAX(-2100),
    LOJASCROLLSPEED(30),
    LOJACARDGAPS(10);

    public final int value;

    private LojaCommons(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }
}
