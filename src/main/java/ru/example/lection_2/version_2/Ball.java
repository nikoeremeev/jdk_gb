package ru.example.lection_2.version_2;
import java.util.Random;
import java.awt.*;

public class Ball extends Sprite {
    private static Random rand = new Random();
    private final Color color;
    private float vX;
    private float vY;

    Ball(){
        halfHeight = 20 + (float)(rand.nextFloat() * 50f);
        halfWidht = halfHeight;
        color = new Color(rand.nextInt());
        vX = 100f + (float)(rand.nextFloat() * 200f);
        vY = 100f + (float)(rand.nextFloat() * 200f);
    }

    @Override
    protected void render(MainCanvas canvas, Graphics g) {
        g.setColor(color);
        g.fillOval((int)getLeft(), (int)getTop(), (int)getWidth(), (int)getHeight());
    }

    @Override
    protected void update(MainCanvas canvas, float deltaTime) {
        x = vX + deltaTime;
        y = vY + deltaTime;

        if (getLeft() < canvas.getLeft()) {
            setLeft(canvas.getLeft());
            vX = - vX;
        }

        if (getRight() > canvas.getRight()) {
            setRight(canvas.getRight());
            vX = - vX;
        }

        if (getTop() < canvas.getTop()) {
            setTop(canvas.getTop());
            vY = - vY;
        }

        if (getBottom() > canvas.getBottom()) {
            setBottom(canvas.getBottom());
            vY = - vY;
        }
    }
    
}
