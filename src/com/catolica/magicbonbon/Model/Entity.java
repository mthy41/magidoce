package com.catolica.magicbonbon.Model;
import com.catolica.magicbonbon.Interfaces.I_Positions;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;

public abstract class Entity implements I_Positions{
    Raylib r;
    Texture2D texture;
    public Vector2 position;
    Color tintColor;
    float rotation;
    float scale;

    public Entity(
        Raylib r,
        Texture2D texture,
        Vector2 position,
        Color tintColor,
        float rotation,
        float scale
    ){
        this.r = r;
        this.texture = texture;
        this.position = position;
        this.tintColor = tintColor;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void draw(){
        r.textures.DrawTextureEx(
            this.texture, 
            this.position, 
            this.rotation, 
            this.scale, 
            this.tintColor
            );
    }

    @Override
    public float getX(){
        return this.position.x;
    }

    @Override
    public float getY(){
        return this.position.y;
    }

    @Override
    public void setNewPosition(float x, float y){
        this.position.x += x;
        this.position.y += y;
    }

    @Override
    public void setNewPostionVector(Vector2 vector2){
        this.position = vector2;
    }
}
