package com.catolica.magicbonbon.Model;
import com.catolica.magicbonbon.Enums.TierSpeed;

public class Tier4 {
    private int currentTier = 0;

    float t1Price;
    float t2Price;
    float t3Price;
    float t4Price;

    public Tier4(
        float t1Price,
        float t2Price,
        float t3Price,
        float t4Price
    ){
        this.t1Price = t1Price;
        this.t2Price = t2Price;
        this.t3Price = t3Price;
        this.t4Price = t4Price;
    }

    public int getCurrentTier(){
        return currentTier;
    }

    public float getTierSpeed(){
        switch (this.currentTier) {
            case 0:
                return TierSpeed.T0SPEED.getValue();
            
            case 1:
                return TierSpeed.T1SPEED.getValue();
            
            case 2:
                return TierSpeed.T2SPEED.getValue();

            case 3:
                return TierSpeed.T3SPEED.getValue();

            case 4:
                return TierSpeed.T4SPEED.getValue();

            default:
                return 0.0f;
        }
    }

    public float getTierPrice(){
        switch (this.currentTier) {
            case 0:
                return this.t1Price;
            
            case 1:
                return this.t2Price;

            case 2:
                return this.t3Price;

            case 3:
                return this.t4Price;

            default:
                return this.t1Price;
        }
    }

    public void nextTier(){
        if(currentTier < 4){
            currentTier++;
        }
    }
}