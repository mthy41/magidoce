package com.catolica.magicbonbon.Model;

import com.catolica.magicbonbon.Interfaces.I_Positions;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;

public abstract class Card extends Entity{

    public Button button;
    public String cardState;

    public Card(
    Raylib r,
    Texture2D texture,
    Vector2 position,
    Color tintColor,
    float rotation,
    float scale

    ){
        super(r, texture, position, tintColor, rotation, scale);
    }

    public String checkCardState(){
        return this.cardState;
    }
}
