package ru.example.lection_2.version_2;

import java.awt.*;

public interface Interacable {
    public void update(MainCanvas canvas, float deltaTime);
    public void render(MainCanvas canvas, Graphics g);
}