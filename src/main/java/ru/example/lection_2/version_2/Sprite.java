package ru.example.lection_2.version_2;
import java.awt.*;

public abstract class Sprite implements Interacable {
    protected float x;
    protected float y;
    protected float halfWidht;
    protected float halfHeight;

    protected float getLeft(){return x - halfWidht;}
    protected void setLeft(float left) {x = left + halfWidht;}
    protected float getRight(){return x + halfWidht;}
    protected void setRight(float right) {x = right - halfWidht;}
    protected float getTop(){return y - halfHeight;}
    protected void setTop(float top) {y = top + halfHeight;}
    protected float getBottom(){return y + halfHeight;}
    protected void setBottom(float bottom) {y = bottom - halfHeight;}

    protected float getWidth() {return 2*halfWidht;}
    protected float getHeight() {return 2*halfHeight;}

}
