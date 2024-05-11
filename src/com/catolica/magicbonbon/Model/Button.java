package com.catolica.magicbonbon.Model;

import com.catolica.magicbonbon.Interfaces.I_DoceRelated;
import com.catolica.magicbonbon.Interfaces.I_Labels;
import com.raylib.java.Raylib;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.shapes.Rectangle;
import com.raylib.java.core.Color;
import com.raylib.java.core.rCore;
import com.raylib.java.textures.Texture2D;

public class Button extends Entity implements I_Labels{
    String label;
    Color labelColor;
    int labelSize;
    Vector2 labelPos;
    Rectangle clickBox;

    public Button(
        Raylib r,
        Texture2D texture,
        Vector2 position,
        Color tintColor,
        float rotation,
        float scale,
        String label,
        Color labelColor,
        int labelSize,
        Vector2 labelPos
        ){
        super(r, texture, position, tintColor, rotation, scale);
        this.label = label;
        this.labelColor = labelColor;
        this.labelSize = labelSize;
        this.labelPos = labelPos;
        this.clickBox = new Rectangle(
            this.position, 
            this.texture.getWidth()*this.scale, 
            this.texture.getHeight()*this.scale
            );
    }

    public boolean checkClick(){
        Vector2 mousePos = rCore.GetMousePosition();
        if (r.shapes.CheckCollisionPointRec(mousePos, clickBox)){
            this.tintColor = Color.LIGHTGRAY;
            this.labelColor = Color.LIGHTGRAY;
            if (r.core.IsMouseButtonReleased(0)){
                return true;
            }
        } else {
            this.tintColor = Color.WHITE;
            this.labelColor = Color.WHITE;
        }
        return false;
    }

    @Override
    public void setNewLabelPos(float x, float y){
        this.labelPos.x += x;
        this.labelPos.y += y;
    }

    @Override
    public void setNewLabelPosVector(Vector2 vectorPos){
        this.labelPos = vectorPos;
    }

    @Override
    public void setNewLabelColor(Color color) {
        this.labelColor = color;
    }

    @Override
    public void setNewLabelText(String text){
        this.label = text;
    }

    public void updateClickBox(){
        this.clickBox.x = this.position.x;
        this.clickBox.y = this.position.y;
        this.clickBox.height = this.texture.getHeight() * this.scale;
        this.clickBox.width = this.texture.getWidth() * this.scale;
    }

    public void grayfyButton(){
        this.tintColor = Color.LIGHTGRAY;
    }


    @Override
    public void draw(){
        r.textures.DrawTextureEx(
            this.texture, 
            this.position, 
            this.rotation, 
            this.scale, 
            this.tintColor
            );
        r.text.DrawText(
            this.label, 
            (int)(this.position.x + this.labelPos.x), 
            (int)(this.position.y + this.labelPos.y), 
            this.labelSize, 
            this.labelColor);
    }
}