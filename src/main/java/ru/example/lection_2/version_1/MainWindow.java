package ru.example.lection_2.version_1;
import java.awt.Graphics;

import javax.swing.*;

public class MainWindow extends JFrame {
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Простой игровой движок";
    private final MainCanvas gameCanvas = new MainCanvas(this);
    private final Sprite[] sprites = new Sprite[10];
    private final Sprite background = new Sprite() {
        ;
    };

    MainWindow(){
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        for (int i = 0; i < sprites.length; i++) {
            sprites[i] = new Ball();
        }

        add(gameCanvas);
        setVisible(true);
    }

    public void onDrawFrame(MainCanvas canvas, Graphics g, float deltaTime){
        update(canvas, deltaTime);
        render(canvas, g);
    }
    
    private void update(MainCanvas canvas, float deltaTime){
        for (Sprite sprite : sprites) {
            sprite.update(canvas, deltaTime);
        }
    };
    private void render(MainCanvas canvas, Graphics g){
        for (Sprite sprite : sprites) {
            sprite.render(canvas, g);
        }
    };
}
