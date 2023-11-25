package ru.example.lection_2.version_1;
import java.awt.*;

public class Background extends Sprite {
    private float time;
    private static final float AMPLITUDE = 255f / 2f;
    private Color color;
    
    

    @Override
    protected void update(MainCanvas canvas, float deltaTime) {
        time += deltaTime;

        int red = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 2f));
        int green = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 3f));
        int blue = Math.round(AMPLITUDE + AMPLITUDE * (float) Math.sin(time * 1f));
        color = new Color(red, green, blue);
    }

    @Override
    protected void render(MainCanvas canvas, Graphics g) {
        canvas.setBackground(color);
    }
    
}
