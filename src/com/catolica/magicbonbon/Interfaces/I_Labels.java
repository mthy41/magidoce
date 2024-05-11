package com.catolica.magicbonbon.Interfaces;

import com.raylib.java.core.Color;
import com.raylib.java.raymath.Vector2;

public interface I_Labels {
    public void setNewLabelColor(Color color);
    public void setNewLabelText(String text);
    public void setNewLabelPos(float x, float y);
    public void setNewLabelPosVector(Vector2 vectorPos);
}
