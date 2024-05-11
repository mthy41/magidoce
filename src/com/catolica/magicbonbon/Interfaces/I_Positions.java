package com.catolica.magicbonbon.Interfaces;

import com.raylib.java.raymath.Vector2;

public interface I_Positions {
    public float getX();
    public float getY();
    public void setNewPosition(float x, float y);
    public void setNewPostionVector(Vector2 vector2);
}
