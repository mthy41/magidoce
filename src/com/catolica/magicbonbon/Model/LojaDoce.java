package com.catolica.magicbonbon.Model;

import com.catolica.magicbonbon.Interfaces.I_GetAndCheck;
import com.raylib.java.textures.Texture2D;

public class LojaDoce extends Doce implements I_GetAndCheck{
    // Constructor values
    public String inLojaName;
    public String inLojaDesc;
    float inLojaPrice;

    // Default values
    boolean isVisible = false;
    boolean isOwning = false;

    public LojaDoce(
        Texture2D texture,
        float dropValue,
        String inLojaName,
        String inLojaDesc,
        float inLojaPrice
    ){
        super(texture, dropValue);
        this.inLojaName = inLojaName;
        this.inLojaDesc = inLojaDesc;
        this.inLojaPrice = inLojaPrice;
    }

    @Override
    public void letVisible(){
        this.isVisible = true;
    }

    @Override
    public void own(){
        this.isOwning = true;
    }

    @Override
    public boolean checkIsVisible(){
        return this.isVisible;
    }

    @Override
    public boolean checkIsOwning(){
        return this.isOwning;
    }
}
