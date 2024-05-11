package com.catolica.magicbonbon.Model;
import java.util.ArrayList;
import com.catolica.magicbonbon.Interfaces.I_GetAndCheck;
import com.catolica.magicbonbon.Interfaces.I_Positions;
import com.catolica.magicbonbon.Utils.Utils;
import com.raylib.java.Raylib;
import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;
import com.raylib.java.textures.Texture2D;
import java.util.Random;

public class Gen extends Entity implements I_GetAndCheck{
    // UI
    int inCardLabelSize = 24;
    int inCardLabelXpos = 20;
    int inCardLabelYPos = 10;
    Color inCardLabelColor = Color.BLACK;

    float tierXPos = 20.0f;
    float tierYpos = 65.0f;
    int tierScale = 1;
    Color tierColor = Color.WHITE;


    float inCardPrice;
    String inCardLabel;
    public Tier4 tier;
    float tierUpgradePrice;
    public Button button;
    Texture2D[] tierDatapack;
    Texture2D[] cardDatapack;
    Texture2D[] buttonDatapack;
    float yPosOffset;

    public DoceStarter doceStarter;
    public ArrayList<LojaDoce> boughtDoces;
    //TODO: Add drops per interval atribute and get method
    
    // Default values
    String currentState = "LOCKED";
    boolean isVisible = false;
    boolean isOwning = false;
    Random random;
    public int frameCounter = 0;
    public int dropInterval = 120;

    public Gen(
        Raylib r,
        Texture2D texture,
        Vector2 position,
        Color tintColor,
        float rotation,
        float scale,

        float inCardPrice,
        String inCardLabel,
        Tier4 tier,
        Button button,
        Texture2D[] tierDatapack,
        Texture2D[] cardDatapack,
        Texture2D[] buttonDatapack,
        float yPosOffset,
        DoceStarter doceStarter
    ){
        super(r, texture, position, tintColor, rotation, scale);
        this.inCardPrice = inCardPrice;
        this.inCardLabel = inCardLabel;
        this.tier = tier;
        this.button = button;
        this.tierDatapack = tierDatapack;
        this.cardDatapack = cardDatapack;
        this.buttonDatapack = buttonDatapack;
        this.yPosOffset = yPosOffset;
        this.doceStarter = doceStarter;
        
        this.boughtDoces = new ArrayList<>();
        }

    public boolean run(){
        frameCounter++;
        if (frameCounter >= dropInterval * this.tier.getTierSpeed()){
            frameCounter = 0;
            return true;
        }
        return false;
    }

    public float getPrice(){
        return this.inCardPrice;
    }

    public void upgradeTier(){
        this.tier.nextTier();
    }

    public void addDoce(LojaDoce doce){
        this.boughtDoces.add(doce);
    }

    public Texture2D getRandDoceTexture(){
        if (this.boughtDoces.size() > 0){
            int randomIndex = Utils.RandIndex(this.boughtDoces.size()+1);
            System.out.println(randomIndex);
            if (randomIndex == this.boughtDoces.size()){
                return this.doceStarter.getTexture2d();
            } else {
                return this.boughtDoces.get(randomIndex).getTexture2d();
            }
        }
        return this.doceStarter.getTexture2d();
    }

    public String checkState(){
        return this.currentState;
    }

    @Override
    public void letVisible(){
        this.currentState = "AVAILABLE";
        this.isVisible = true;
    }

    @Override
    public void own(){
        this.currentState = "OWNING";
        this.isOwning = true;

        this.button.texture = this.buttonDatapack[1];
        this.button.position.x += 30;
        this.button.label = "";
        this.button.labelPos.y -= 30;
        this.button.updateClickBox();
    }

    @Override
    public boolean checkIsVisible(){
        return this.isVisible;
    }

    @Override
    public boolean checkIsOwning(){
        return this.isOwning;
    }

    @Override
    public void draw(){
        switch (this.currentState) {
            case "LOCKED":
            r.textures.DrawTextureEx(
                this.cardDatapack[0],
                this.position,
                this.rotation,
                this.scale,
                Color.WHITE
            );
                break;
            
            case "AVAILABLE":
            // this.position.x = 15.0f;
            // this.position.y = 15.0f;

            this.button.position.x = this.position.x + 155.0f;
            this.button.position.y = this.position.y + 45.0f;
            this.button.clickBox.x = this.button.position.x;
            this.button.clickBox.y = this.button.position.y;
            this.button.label = "comprar";
            this.button.labelPos.x = 5.0f;
            this.button.labelPos.y = 5.0f;

            r.textures.DrawTextureEx(
                this.cardDatapack[1],
                this.position,
                this.rotation,
                this.scale,
                Color.WHITE
                );  
            this.button.draw();
            // this vvvvv is removable
            // if (this.button.checkClick()){
            //     System.out.println("deu certo carai");
            //     own();
            //     break;
            // }
            // this ^^^^^ is removable
            
            r.text.DrawText(this.inCardPrice+"$", 
                (int)this.position.x + 100, 
                (int)this.position.y + 15, 
                30, 
                Color.DARKGRAY
                );
            
                break;

            case "OWNING":
            r.textures.DrawTextureEx(
                this.cardDatapack[2],
                this.position,
                this.rotation,
                this.scale,
                Color.WHITE
                );
            
            this.button.draw();
            if (this.tier.getCurrentTier() == 4){
                this.button.texture = this.buttonDatapack[2];
                this.button.label = "MAX";
            }
            if (this.tier.getCurrentTier() < 4){
                this.button.label = this.tier.getTierPrice()+"$";
            }

            // Tier texture
            r.textures.DrawTextureEx(
                this.tierDatapack[this.tier.getCurrentTier()],
                new Vector2(this.getX() + this.tierXPos, this.getY() + this.tierYpos),
                this.rotation,
                this.tierScale,
                this.tierColor
                );
            
            // Card label
            r.text.DrawText(
                this.inCardLabel, 
                ((int)this.position.getX()) + this.inCardLabelXpos, 
                ((int)this.position.getY()) + this.inCardLabelYPos, 
                this.inCardLabelSize, 
                this.inCardLabelColor
                );

            // this vvvvv is removable
            // if(!(this.tier.getCurrentTier() == 4)){
            //     if (this.button.checkClick()){
            //         this.tier.nextTier();
            //         System.out.println("deu certo carai");
            //         break;
            //     }
            // }
            // this ^^^^^ is removable

            default:
                break;
        }
    }
}