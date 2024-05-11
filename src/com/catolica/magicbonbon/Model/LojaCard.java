package com.catolica.magicbonbon.Model;

import com.catolica.magicbonbon.Interfaces.I_GetAndCheck;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;

public class LojaCard extends Card implements I_GetAndCheck{
    private Texture2D[] lojaCardDatapack;
    public LojaDoce doce;
    public int genOwnerIndex;

    public LojaCard(
        Texture2D[] lojaCardDatapack,
        LojaDoce doce,
        int genOwnerIndex,
        
        Raylib r,
        Texture2D texture,
        Vector2 position,
        Color tintColor,
        float rotation,
        float scale,
        Button button
    ){
        super(r, texture, position, tintColor, rotation, scale);
        this.lojaCardDatapack = lojaCardDatapack;
        this.doce = doce;
        this.genOwnerIndex = genOwnerIndex;
        this.button = button;
        this.cardState = "LOCKED";
    }

    public String checkCardState(){
        return this.cardState;
    }

    @Override
    public void own(){
        this.cardState = "OWNING";
    }
    
    @Override
    public void letVisible(){
        this.cardState = "AVAILABLE";
    }

    @Override
    public boolean checkIsVisible(){
        if (this.cardState.equals("LOCKED")){
            return false;
        }
        return true;
    }

    @Override
    public boolean checkIsOwning(){
        if (this.cardState.equals("OWNING")){
            return true;
        }
        return false;
    }

    @Override
    public void draw(){
        switch (cardState) {
            case "LOCKED":
            r.textures.DrawTextureEx(
                this.lojaCardDatapack[1],
                this.position,
                this.rotation,
                this.scale,
                this.tintColor
                );                
                break;
            
            case "AVAILABLE":
            this.button.clickBox.x = this.button.position.x;
            this.button.clickBox.y = this.button.position.y;
            this.button.label = "R$"+this.doce.inLojaPrice;
            this.button.position.x = this.position.x + 55.0f;
            this.button.position.y = this.position.y + 270.0f;
            this.button.labelPos.x = 10.0f;
            this.button.labelPos.y = 15.0f;

            r.textures.DrawTextureEx(
                this.lojaCardDatapack[0],
                this.position,
                this.rotation,
                this.scale,
                this.tintColor
                );

            r.textures.DrawTextureEx(
                this.doce.texture,
                new Vector2(this.position.x + 50, this.position.y + 40.0f),
                0.0f,
                5.0f,
                Color.WHITE
                );
            
            r.text.DrawText(
                this.doce.inLojaName,
                (int) this.position.x + 20, 
                (int) this.position.y + 20, 
                18,
                Color.WHITE);

            r.text.DrawText(
                this.doce.inLojaDesc,
                (int) this.position.x + 20, 
                (int) this.position.y + 190, 
                20,
                Color.BLACK);
            
            this.button.draw();
            
                break;
            
            case "OWNING":
            this.button.clickBox.x = -1000;   // this is so funny lol...
            this.button.clickBox.y = -1000;   
            this.button.label = "Adquirido";
            this.button.position.x = this.position.x + 55.0f;
            this.button.position.y = this.position.y + 270.0f;
            this.button.labelPos.x = 10.0f;
            this.button.labelPos.y = 15.0f;

            r.textures.DrawTextureEx(
                this.lojaCardDatapack[0],
                this.position,
                this.rotation,
                this.scale,
                this.tintColor
                );

            r.textures.DrawTextureEx(
                this.doce.texture,
                new Vector2(this.position.x + 50, this.position.y + 40.0f),
                0.0f,
                5.0f,
                Color.WHITE
                );
            
            r.text.DrawText(
                this.doce.inLojaName,
                (int) this.position.x + 20, 
                (int) this.position.y + 20, 
                18,
                Color.WHITE);

            r.text.DrawText(
                this.doce.inLojaDesc,
                (int) this.position.x + 20, 
                (int) this.position.y + 190, 
                20,
                Color.BLACK);
            
            this.button.draw();

            default:
                break;
        }

    }
}