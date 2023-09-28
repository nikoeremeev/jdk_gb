package ru.example.lessons.lesson_01;

import javax.swing.*;
import java.awt.*;

public class Map extends JPanel{
    Map() {
        setBackground(Color.BLACK);
    }

    void startNewGame(int mode, int fSzX, int fSzY, int wLen) {
        System.out.printf("Mode: %d;\nSize: x=%d, y=%d;\nWin Length: %d",
                mode, fSzX, fSzY, wLen);
    }
}
