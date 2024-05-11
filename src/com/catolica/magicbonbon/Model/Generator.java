package com.catolica.magicbonbon.Model;
import java.util.ArrayList;
import com.catolica.magicbonbon.Interfaces.I_GetAndCheck;
import com.catolica.magicbonbon.Utils.Utils;
import com.raylib.java.textures.Texture2D;
import java.util.Random;

public class Generator implements I_GetAndCheck{
    // Constructor values
    Tier4 tier;
    DoceStarter doceStarter;
    ArrayList<LojaDoce> boughtDoces;
    String inCardLabel;
    float inCardPrice;
    float xPosOffset;
    //TODO: Add drops per interval atribute and get method
    
    // Default values
    String currentState = "LOCKED";
    boolean isVisible = false;
    boolean isOwning = false;
    Random random;
    public int frameCounter = 0;
    public int dropInterval = 120;

    public Generator(
        Tier4 tier,
        DoceStarter doceStarter,
        ArrayList<LojaDoce> boughtDoces,
        String inCardLabel,
        float inCardPrice,
        float xPosOffset
    ){
        this.tier = tier;
        this.doceStarter = doceStarter;
        this.boughtDoces = boughtDoces;
        this.inCardLabel = inCardLabel;
        this.inCardPrice = inCardPrice;
        this.xPosOffset = xPosOffset;
        this.random = new Random();
    }

    public boolean run(){
        frameCounter++;
        if (frameCounter >= dropInterval * this.tier.getTierSpeed()){
            frameCounter = 0;
            return true;
        }
        return false;
    }

    public void upgradeTier(){
        this.tier.nextTier();
    }

    public void addDoce(LojaDoce doce){
        this.boughtDoces.add(doce);
    }

    public Texture2D getRandDoceTexture(){
        //TODO: fix the starter Doce texture not geting picked when list is over 0
        if (this.boughtDoces.size() > 0){
            int randomIndex = Utils.RandIndex(this.boughtDoces.size());
            return this.boughtDoces.get(randomIndex).getTexture2d();
        }
        return this.doceStarter.getTexture2d();
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