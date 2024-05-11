package com.catolica.magicbonbon.Model;
import com.catolica.magicbonbon.Model.Generator;

import java.util.ArrayList;

// import com.raylib.java.Raylib;
// import com.raylib.java.textures.Texture2D;
public class Player {
    
    private float currency = 100;

    public Float getCurrency(){
        return this.currency;
    }

    public String getCurrencyString(){
        return "R$"+String.valueOf(this.currency) + "0";

    }
    
    public void addCurrency(float valueIn){
        if (valueIn <= 0){ //Check if value is negative
            valueIn *= -1;
        }
        this.currency += valueIn;
    }

    public boolean buyGenerator(Gen gen){
        if (gen.inCardPrice > this.currency){
            System.out.println("Você não tem dinheiro suficiente para essa compra.");
            return false;
        }

        this.currency -= gen.inCardPrice;
        return true;
    }

    public boolean buyGenUpgrade(Tier4 tier){
        if (tier.getTierPrice() > this.currency){
            System.out.println("Você não tem dinheiro suficiente para essa compra.");
            return false;
        }

        this.currency -= tier.getTierPrice();
        return true;
    }

    public boolean buyDoce(LojaDoce doce){
        if (doce.inLojaPrice > this.currency){
            System.out.println("Você não tem dinheiro suficiente para essa compra.");
            return false;
        }
         
        this.currency -= doce.inLojaPrice;
        return true;
    }

}
