package ru.example.seminar_1_task_1_3;

import javax.swing.*;
import java.awt.*;


public class GameMap extends JPanel {
    public int panelWidth;
    public int panelHeight;
    public int cellHeight;
    public int cellWidth;
    public int sizeX;
    public int sizeY;

    GameMap(int sizeX, int sizeY){
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        render(g);
    }

    private void render(Graphics g) {
        panelWidth = this.getWidth();
        panelHeight = this.getHeight();
        cellHeight = panelHeight / this.sizeX;
        cellWidth = panelWidth / this.sizeY;

        g.setColor(Color.BLACK);
        for (int h = 0; h < this.sizeX; h++) {
            int y = h * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int h = 0; h < this.sizeY; h++) {
            int x = h * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }
        repaint();
    }

    void startNewGame(boolean mode, int size_x, int size_y, int win_len){
        sizeX = size_x;
        sizeY = size_y;
        repaint();
    }
}
