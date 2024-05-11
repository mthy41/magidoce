package com.catolica.magicbonbon.Model;
import com.catolica.magicbonbon.Interfaces.I_DoceRelated;
import com.raylib.java.textures.Texture2D;

public abstract class Doce implements I_DoceRelated{
    public Texture2D texture;
    public float dropValue;

    public Doce(Texture2D texture, float dropValue){
        this.texture = texture;
        this.dropValue = dropValue;
    }

    @Override
    public Texture2D getTexture2d(){
        return this.texture;
    }

    @Override
    public float getDropValue(){
        return this.dropValue;
    }
}
