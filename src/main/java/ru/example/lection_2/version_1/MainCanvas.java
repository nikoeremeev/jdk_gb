package ru.example.lection_2.version_1;
import java.awt.*;
import javax.swing.*;

public class MainCanvas extends JPanel {
    private MainWindow controller;
    private long lastTime, currentTime;

    MainCanvas(MainWindow controller)
    {   
        this.controller = controller;
        lastTime = System.nanoTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        currentTime = System.nanoTime();;
        super.paintComponent(g);
        try {
            Thread.sleep(16);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

      
        controller.onDrawFrame(this, g, (currentTime - lastTime) * 0.000000001f );
        lastTime = System.nanoTime();
        repaint();
  
    }


    public int getLeft() {return 0;}
    public int getTop() {return 0;}
    public int getRight() {return getWidth() - 1;}
    public int getBottom() {return getHeight() -1;}
}
