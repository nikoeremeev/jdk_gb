package ru.example.lessons.lesson_01;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel{
    Map() {
        setBackground(Color.WHITE);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d\n",
                mode, fSzX, fSzY, wLen);
    }

    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g){
        g.setColor(Color.BLACK);
        g.drawLine(0, 0, 100, 100);
        int panelWidth = getWidth();
        int panelHeight = getHeight();
    }
}

