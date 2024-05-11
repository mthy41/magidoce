package com.catolica.magicbonbon.Model;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import com.catolica.magicbonbon.Enums.Common;
import com.catolica.magicbonbon.Utils.Utils;

public class FallingEntity extends Entity{
    private int rotationDir;
    private float fallingSpeed;

    public FallingEntity(
        Raylib r,
        Texture2D texture,
        Vector2 position,
        Color tintColor,
        float rotation,
        float scale,
        float fallingSpeed
    ){
        super(r, texture, position, tintColor, rotation, scale);
        this.fallingSpeed = fallingSpeed;
        this.rotationDir = Utils.randSign();
    }

    public void fall(){
        this.position.y += this.fallingSpeed;
    }

    public void spin(){
        this.rotation += 2.0f * this.rotationDir;
    }

    public boolean checkOutOfBound(){
        if (this.position.y > Common.SCREEN_HEIGHT.getValue()){
            return true;
        }
        return false;
    }
}